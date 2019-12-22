import ProcTypes;

import RegisterFile;
import Decode;
import Execute;

// MainMemory includes the MainMemory port
// and the SingleCycleMemory modules
import MainMemory;

// Milestone 2: Select which cache(s) to import
import DirectMappedCache;
import TwoWayCache;
import TwoWaySortCache;

import ALU; // USE FAST ADDer
import BTB; // use btb

typedef struct {
    //Word pc;
    Bool poisoned;
    // Add anything you need
    // NOTE: If you leave F2D empty, Maybe#(F2D) f2d will just be the valid bit.
    // In this case, you can set f2d to valid by writing: f2d <= Valid(?);
} F2D;

typedef struct {
    Word pc;
    Word guessed_pc;
    DecodedInst dInst;
    Word rVal1;
    Word rVal2;
    // Add anything you need
} D2E;

typedef struct {
    Word pc;
    IType iType;
    Maybe#(RIndx) dst;
    Word data;
    // Add anything you need
} E2W;

module Processor;
    Reg#(Word) pc(0);
    RegisterFile rf;

    // Uncomment and use these in the processor
    // rule for useful debugging help tracking
    // cycles and instructions completed
    //Reg#(Bit#(64)) cycles(0);
    //Reg#(Bit#(64)) insts(0);

    // Single Cycle Memories
    // Milestone 2: comment these declarations out
    //SingleCycleMemory iMem;
    //SingleCycleMemory dMem;

    // Line-based Main Memories to use with caches
    // Milestone 2: uncomment these declarations
    MainMemory iMem;
    MainMemory dMem;

    // Milestone 2: choose which type of cache to use
    // NOTE: The CacheWrapper#() type of the dCache
    // is critical, as it is used for detecting
    // that the program has finished running
    // iCache: Direct-Mapped or Two-Way Set Associative
    DirectMappedCache iCache(iMem);
    //TwoWayCache iCache(iMem);
    // dCache: Direct-Mapped or Two-Way Set Associative
    //CacheWrapper#(DirectMappedCache) dCache(dMem);
    CacheWrapper#(TwoWaySortCache) dCache(dMem);

    // Pipeline Registers
    Reg#(Maybe#(F2D)) f2d(Invalid);
    Reg#(Maybe#(D2E)) d2e(Invalid);
    Reg#(Maybe#(E2W)) e2w(Invalid);
    // Performance counters
    // These are used for debugging, but are not needed for correctness; you
    // can modify or remove them
    Reg#(Bit#(32)) cycle(0);
    Reg#(Bit#(32)) instrs(0);
    BTB btb ;
    rule tick;
        //////////////////////
        // Debugging Helper //
        //////////////////////
        cycle <= cycle + 1;
        // Uncomment the code below to print pipeline state each cycle
        begin
            $display("[%d] ", cycle);
            // if(cycle>=1000) 
            // begin
            //     $display("Terminated by Boomza");
            //     $finish;
            // end
            //$display("%x|", pc);
            if (isValid(f2d)) $display("f2d:", fshow(fromMaybe(?,f2d))); else $display("f2d:", "NOP");  
            if (isValid(d2e)) $display("d2e:", fshow(fromMaybe(?,d2e))); else $display("d2e:", "NOP");  
            if (isValid(e2w)) $display("e2w:", fshow(fromMaybe(?,e2w))); else $display("e2w:", "NOP");  
            $display("instrs: %d", instrs);
            
            $display("------------------------------------------");
        end


        Maybe#(E2W) cur_e2w=e2w;
        Maybe#(D2E) cur_d2e=d2e;
        Maybe#(F2D) cur_f2d=f2d;
        Word cur_pc =pc;
        /////////////////////
        // Writeback Stage //
        /////////////////////
        // Signals used by decode to handle data hazards
        RIndx dstW = 0;
        Maybe#(Word) dataW = Invalid;
        RIndx dstE = 0;
        Maybe#(Word) dataE = Invalid;
        
        

        // Milestone 2:
        // Signal for dCache-induced stalls
        //if dStall is true then stall the entire pipeline
        Bool wStall = False;

        if (isValid(e2w)) begin
            let e2w_v = fromMaybe(?, e2w);
            $display("WriteBack:", fshow(e2w_v));
            Word data = e2w_v.data;
            dataW=Valid(data); // LOAD is the only thing that has dst but has invlid data
            if(e2w_v.iType == LOAD ) begin
                data= fromMaybe(0,dCache.data);
                wStall= !isValid(dCache.data) ;
                dataW=dCache.data;
            end

            if(isValid(e2w_v.dst)) begin
                $display("RF Write: ", fshow(RegWriteArgs{index: fromMaybe(?,e2w_v.dst), data:data}));
                rf.wr=Valid(RegWriteArgs{index: fromMaybe(?,e2w_v.dst), data:data});
            end
            // By pass signal
            dstW=fromMaybe(0,e2w_v.dst);
            //dataW=data;
            // Count every instruction that leaves the writeback stage
            // (for debugging help)
            instrs <= instrs + 1;

            // BEGIN: DO NOT REMOVE OR MODIFY THIS CODE
            // If unsupported instruction, stops simulation and print the state of the processor
            if (e2w_v.iType == Unsupported) begin
                $display("Reached unsupported instruction");
                $display("Dumping the state of the processor");
                $display("pc = 0x%x", e2w_v.pc);
                $display(rf.fshow);
                $display("Quitting simulation.");
                $finish;
            end
            // END: DO NOT REMOVE OR MODIFY THIS CODE
        end
        $display("wStall: ",fshow(wStall));

        if(!wStall) cur_e2w=Invalid;
        ///////////////////
        // Execute Stage //
        ///////////////////
        
        let d2e_v = fromMaybe(?, d2e);
        DecodedInst edInst = d2e_v.dInst;
        Word erVal1 = d2e_v.rVal1;
        Word erVal2 = d2e_v.rVal2;
        Word pcE=d2e_v.pc;
        ExecInst eInst = execute(edInst,erVal1,erVal2,pcE); 
        Bool access_dCache = edInst.iType==LOAD || edInst.iType==STORE || edInst.iType==SORT;
        Bool is_branch = edInst.iType==BRANCH || edInst.iType==JAL || edInst.iType==JALR;
        Bool fire_execute = isValid(d2e) && !isValid(cur_e2w) && (!access_dCache || dCache.reqEnabled);
        
        // Signals used to handle mispredictions
        Bool annul = False;
        Word redirectPC = ?;

        // Signals used by decode to handle data hazards
        if(isValid(d2e)) begin
            dstE =fromMaybe(0,edInst.dst);
            dataE=(eInst.iType == LOAD)?Invalid: Valid(eInst.data);
        end

        if (fire_execute) begin
            
            $display("Execute :", fshow(d2e_v));
            

            if(access_dCache) begin
                MemOp op = case(edInst.iType)
                    LOAD: Ld;
                    STORE: St;
                    SORT: Sort;
                endcase;
                $display("dCache Write: ", fshow(MemReq{op: op, addr: eInst.addr, data:eInst.data}));
                dCache.req = Valid(MemReq{op: op, addr: eInst.addr, data:eInst.data});
            end

            cur_e2w= Valid(E2W{pc: pcE, iType: eInst.iType, dst: eInst.dst, data:eInst.data});
            // bypass signal
            // redirection signal
            annul=  (eInst.nextPc!=d2e_v.guessed_pc);
            redirectPC=eInst.nextPc;
            
            if(is_branch) begin
                btb.req= Valid(BTBArgs{pc:pcE[11:0], next_pc: eInst.nextPc[11:0]});
                // update BTB
            end
            //
            cur_d2e=Invalid;
        end 
        // no more mutation of cur e2w more at this point

        e2w<= cur_e2w;

        /////////////////////
        //Redirection logic//
        /////////////////////
        if(annul)begin
            $display("Redirect to : ",fshow(redirectPC));
            if(isValid(cur_f2d)) begin
                cur_f2d=Valid(F2D{ poisoned:True});
                $display("Poisoning f2d");
            end
            //cur_pc=redirectPC;
            
        end
        
        //////////////////
        // Decode Stage //
        //////////////////
       
        $display("dstW:",fshow(dstW), " dataW:", fshow(dataW));
        $display("dstE:",fshow(dstE), " dataE:", fshow(dataE));
        Word inst =fromMaybe(?,iCache.data);
        DecodedInst dInst = decode(inst);
        
        Maybe#(Word) rVal1 = Valid(rf.rd1(dInst.src1));
        if(dInst.src1 !=0 && dInst.src1 == dstW) rVal1=dataW;
        if(dInst.src1 !=0 && dInst.src1 == dstE) rVal1=dataE;
        Word val1 = fromMaybe(?,rVal1);
        Maybe#(Word) rVal2 = Valid(rf.rd2(dInst.src2));
        if(dInst.src2 !=0 && dInst.src2 == dstW) rVal2=dataW;
        if(dInst.src2 !=0 && dInst.src2 == dstE) rVal2=dataE;
        Word val2 = fromMaybe(?,rVal2);
        Bool hazard1 = (dInst.src1 !=0 && (!isValid(rVal1)));
        Bool hazard2 = (dInst.src2 !=0 && (!isValid(rVal2)));
        Bool data_hazard = hazard1 || hazard2;
        // Test fetching for thinkign about hazard
        
        Bool iStall = False;
        // cur_f2d = f2d at this point
        Bool decode_feeder_ready = isValid(cur_f2d) && isValid(iCache.data);
        let f2d_v = fromMaybe(?, cur_f2d);
        Bool poision_decode =  f2d_v.poisoned;
        Bool fire_decode = (decode_feeder_ready) &&( poision_decode || (!data_hazard && !isValid(cur_d2e)));
        

        Word guessed_pc = btb.predictedNextPC(cur_pc[11:0]);
        
        // Real pc that decode should decode here is pc (if ! annul)
        // and it should be something else if annul
        if (fire_decode) begin

            $display("Decode:", fshow(D2E{pc: cur_pc, guessed_pc: guessed_pc, dInst: dInst, rVal1:val1, rVal2: val2}));
            cur_d2e=Valid(D2E{pc: cur_pc, guessed_pc: guessed_pc, dInst: dInst, rVal1:val1, rVal2: val2});
            cur_f2d=Invalid;
            
        end 
        
        if(poision_decode) begin
            cur_d2e=Invalid;
        end
        // No more mutation of d2e at this point
        d2e<=cur_d2e;
        /////////////////
        // Fetch Stage //
        /////////////////
       // Word nextPc = fetch_pc;
        // Invariant 1: There must always be ongoing instruction request in the i Cache
        // That is cur_f2d must always be valid

        // Calculate fetch pc;
        if(annul) begin
            cur_pc=redirectPC;
        end else begin
            if(fire_decode && !poision_decode) begin
                cur_pc=guessed_pc;
            end else begin
                cur_pc=cur_pc;
            end
        end
        // fetch next px
        if(iCache.reqEnabled )begin
            $display("Fetch ", fshow(MemReq{op:Ld, addr:cur_pc, data: 0}));
            iCache.req = Valid(MemReq{op:Ld, addr:cur_pc, data: 0});
            cur_f2d=Valid(F2D{ poisoned: False});
        end
        
        f2d<=cur_f2d;
        $display("Pc to :" ,fshow(cur_pc));
        pc<=cur_pc;
        // Initilization
        
    endrule

    method Word getPc = pc;
endmodule
