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
        $display("--------------------");
        $display("<CodeLogger>:", "State: pc   =  ", fshow(pc));
        $display("<CodeLogger>:", "State: f2d   =  ", fshow(f2d));
        $display("<CodeLogger>:", "State: d2e   =  ", fshow(d2e));
        $display("<CodeLogger>:", "State: e2w   =  ", fshow(e2w));
        $display("<CodeLogger>:", "State: cycle   =  ", fshow(cycle));
        $display("<CodeLogger>:", "State: instrs   =  ", fshow(instrs));
        
        //////////////////////
        // Debugging Helper //
        //////////////////////
        $display("<CodeLogger>:", "Write: ", "cycle", " =  ", fshow(begin let temp = cycle; temp = cycle+1;  temp; end) , " @ 89,9"); cycle <= cycle + 1;
        // Uncomment the code below to print pipeline state each cycle
        begin
$display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 91,14");
            $display("[%d] ", cycle);
            // if(cycle>=1000) 
            // begin
            //     $display("Terminated by Boomza");
            //     $finish;
            // end
            //$display("%x|", pc);
            $display("<CodeLogger>:", "If   :   isValid(f2d) =  ", fshow(isValid(f2d)) , " @ 99,13");
if (isValid(f2d)) begin
$display("f2d:", fshow(fromMaybe(?,f2d)));
end else begin
$display("f2d:", "NOP");
            end  
            $display("<CodeLogger>:", "If   :   isValid(d2e) =  ", fshow(isValid(d2e)) , " @ 100,13");
            if (isValid(d2e)) begin
            $display("d2e:", fshow(fromMaybe(?,d2e)));
            end else begin
            $display("d2e:", "NOP");
            end  
            $display("<CodeLogger>:", "If   :   isValid(e2w) =  ", fshow(isValid(e2w)) , " @ 101,13");
            if (isValid(e2w)) begin
            $display("e2w:", fshow(fromMaybe(?,e2w)));
            end else begin
            $display("e2w:", "NOP");
            end  
            $display("instrs: %d", instrs);
            
            $display("------------------------------------------");
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 105,9");
        end


        Maybe#(E2W) cur_e2w=e2w;$display("<CodeLogger>:", "Init : cur_e2w    =  ", fshow(cur_e2w) , " @ 108,21"); 
        Maybe#(D2E) cur_d2e=d2e;$display("<CodeLogger>:", "Init : cur_d2e    =  ", fshow(cur_d2e) , " @ 109,21"); 
        Maybe#(F2D) cur_f2d=f2d;$display("<CodeLogger>:", "Init : cur_f2d    =  ", fshow(cur_f2d) , " @ 110,21"); 
        Word cur_pc =pc;$display("<CodeLogger>:", "Init : cur_pc =  ", fshow(cur_pc) , " @ 111,14"); 
        /////////////////////
        // Writeback Stage //
        /////////////////////
        // Signals used by decode to handle data hazards
        RIndx dstW = 0;$display("<CodeLogger>:", "Init : dstW   =  ", fshow(dstW) , " @ 116,15"); 
        Maybe#(Word) dataW = Invalid;$display("<CodeLogger>:", "Init : dataW  =  ", fshow(dataW) , " @ 117,22"); 
        RIndx dstE = 0;$display("<CodeLogger>:", "Init : dstE   =  ", fshow(dstE) , " @ 118,15"); 
        Maybe#(Word) dataE = Invalid;$display("<CodeLogger>:", "Init : dataE  =  ", fshow(dataE) , " @ 119,22"); 
        
        

        // Milestone 2:
        // Signal for dCache-induced stalls
        //if dStall is true then stall the entire pipeline
        Bool wStall = False;$display("<CodeLogger>:", "Init : wStall =  ", fshow(wStall) , " @ 126,14"); 

        $display("<CodeLogger>:", "If   : isValid(e2w)   =  ", fshow(isValid(e2w)) , " @ 128,9");
        if (isValid(e2w)) begin
        $display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 128,32");
            let e2w_v = fromMaybe(?, e2w);$display("<CodeLogger>:", "Init :   e2w_v    =  ", fshow(e2w_v) , " @ 129,13"); 
            $display("WriteBack:", fshow(e2w_v));
            Word data = e2w_v.data;$display("<CodeLogger>:", "Init :   data =  ", fshow(data) , " @ 131,18"); 
            $display("<CodeLogger>:", "Set  :   ", "dataW", " =  ", fshow(begin let temp = dataW; temp = Valid(data);  temp; end) , " @ 132,13"); dataW=Valid(data); // LOAD is the only thing that has dst but has invlid data
            $display("<CodeLogger>:", "If   :   e2w_v.iType==LOAD    =  ", fshow(e2w_v.iType==LOAD) , " @ 133,13");
if(e2w_v.iType == LOAD ) begin
$display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 133,43");
                $display("<CodeLogger>:", "Set  :     ", "data", " =  ", fshow(begin let temp = data; temp = fromMaybe(0,dCache.data);  temp; end) , " @ 134,17"); data= fromMaybe(0,dCache.data);
                $display("<CodeLogger>:", "Set  :     ", "wStall", " =  ", fshow(begin let temp = wStall; temp = !isValid(dCache.data);  temp; end) , " @ 135,17"); wStall= !isValid(dCache.data) ;
                $display("<CodeLogger>:", "Set  :     ", "dataW", " =  ", fshow(begin let temp = dataW; temp = dCache.data;  temp; end) , " @ 136,17"); dataW=dCache.data;
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 137,13");
            end

            $display("<CodeLogger>:", "If   :   isValid(e2w_v.dst)   =  ", fshow(isValid(e2w_v.dst)) , " @ 139,13");
            if(isValid(e2w_v.dst)) begin
            $display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 139,41");
                $display("RF Write: ", fshow(RegWriteArgs{index: fromMaybe(?,e2w_v.dst), data:data}));
                $display("<CodeLogger>:", "Set  :     ", "rf.wr", " =  ", fshow(begin let temp = rf.wr; temp = Valid(RegWriteArgs{index:fromMaybe(?,e2w_v.dst),data:data});  temp; end) , " @ 141,17"); rf.wr=Valid(RegWriteArgs{index: fromMaybe(?,e2w_v.dst), data:data});
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 142,13");
            end
            // By pass signal
            $display("<CodeLogger>:", "Set  :   ", "dstW", " =  ", fshow(begin let temp = dstW; temp = fromMaybe(0,e2w_v.dst);  temp; end) , " @ 144,13"); dstW=fromMaybe(0,e2w_v.dst);
            //dataW=data;
            // Count every instruction that leaves the writeback stage
            // (for debugging help)
            $display("<CodeLogger>:", "Write:   ", "instrs", " =  ", fshow(begin let temp = instrs; temp = instrs+1;  temp; end) , " @ 148,13"); instrs <= instrs + 1;

            // BEGIN: DO NOT REMOVE OR MODIFY THIS CODE
            // If unsupported instruction, stops simulation and print the state of the processor
            $display("<CodeLogger>:", "If   :   e2w_v.iType==Unsupported =  ", fshow(e2w_v.iType==Unsupported) , " @ 152,13");
if (e2w_v.iType == Unsupported) begin
$display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 152,50");
                $display("Reached unsupported instruction");
                $display("Dumping the state of the processor");
                $display("pc = 0x%x", e2w_v.pc);
                $display(rf.fshow);
                $display("Quitting simulation.");
                $finish;
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 159,13");
            end
            // END: DO NOT REMOVE OR MODIFY THIS CODE
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 161,9");
end
        $display("wStall: ",fshow(wStall));

        $display("<CodeLogger>:", "If   : !wStall    =  ", fshow(!wStall) , " @ 164,9");
        if(!wStall) begin
        $display("<CodeLogger>:", "Set  : ", "cur_e2w", " =  ", fshow(begin let temp = cur_e2w; temp = Invalid;  temp; end) , " @ 164,21"); cur_e2w=Invalid;
        end
        ///////////////////
        // Execute Stage //
        ///////////////////
        
        let d2e_v = fromMaybe(?, d2e);$display("<CodeLogger>:", "Init : d2e_v  =  ", fshow(d2e_v) , " @ 169,9"); 
        DecodedInst edInst = d2e_v.dInst;$display("<CodeLogger>:", "Init : edInst =  ", fshow(edInst) , " @ 170,21"); 
        Word erVal1 = d2e_v.rVal1;$display("<CodeLogger>:", "Init : erVal1 =  ", fshow(erVal1) , " @ 171,14"); 
        Word erVal2 = d2e_v.rVal2;$display("<CodeLogger>:", "Init : erVal2 =  ", fshow(erVal2) , " @ 172,14"); 
        Word pcE=d2e_v.pc;$display("<CodeLogger>:", "Init : pcE    =  ", fshow(pcE) , " @ 173,14"); 
        ExecInst eInst = execute(edInst,erVal1,erVal2,pcE);$display("<CodeLogger>:", "Init : eInst  =  ", fshow(eInst) , " @ 174,18");  
        Bool access_dCache = edInst.iType==LOAD || edInst.iType==STORE || edInst.iType==SORT;$display("<CodeLogger>:", "Init : access_dCache  =  ", fshow(access_dCache) , " @ 175,14"); 
        Bool is_branch = edInst.iType==BRANCH || edInst.iType==JAL || edInst.iType==JALR;$display("<CodeLogger>:", "Init : is_branch  =  ", fshow(is_branch) , " @ 176,14"); 
        Bool fire_execute = isValid(d2e) && !isValid(cur_e2w) && (!access_dCache || dCache.reqEnabled);$display("<CodeLogger>:", "Init : fire_execute   =  ", fshow(fire_execute) , " @ 177,14"); 
        
        // Signals used to handle mispredictions
        Bool annul = False;$display("<CodeLogger>:", "Init : annul  =  ", fshow(annul) , " @ 180,14"); 
        Word redirectPC = ?;$display("<CodeLogger>:", "Init : redirectPC =  ", fshow(redirectPC) , " @ 181,14"); 

        // Signals used by decode to handle data hazards
        $display("<CodeLogger>:", "If   : isValid(d2e)   =  ", fshow(isValid(d2e)) , " @ 184,9");
if(isValid(d2e)) begin
$display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 184,31");
            $display("<CodeLogger>:", "Set  :   ", "dstE", " =  ", fshow(begin let temp = dstE; temp = fromMaybe(0,edInst.dst);  temp; end) , " @ 185,13"); dstE =fromMaybe(0,edInst.dst);
            $display("<CodeLogger>:", "Set  :   ", "dataE", " =  ", fshow(begin let temp = dataE; temp = (eInst.iType==LOAD)?Invalid:Valid(eInst.data);  temp; end) , " @ 186,13"); dataE=(eInst.iType == LOAD)?Invalid: Valid(eInst.data);
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 187,9");
        end

        $display("<CodeLogger>:", "If   : fire_execute   =  ", fshow(fire_execute) , " @ 189,9");
        if (fire_execute) begin
        $display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 189,32");
            
            $display("Execute :", fshow(d2e_v));
            

            $display("<CodeLogger>:", "If   :   access_dCache    =  ", fshow(access_dCache) , " @ 194,13");
            if(access_dCache) begin
            $display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 194,36");
                MemOp op = case(edInst.iType)
                    LOAD: Ld;
                    STORE: St;
                    SORT: Sort;
                endcase;$display("<CodeLogger>:", "Init :     op =  ", fshow(op) , " @ 195,23"); 
                $display("dCache Write: ", fshow(MemReq{op: op, addr: eInst.addr, data:eInst.data}));
                $display("<CodeLogger>:", "Set  :     ", "dCache.req", " =  ", fshow(begin let temp = dCache.req; temp = Valid(MemReq{op:op,addr:eInst.addr,data:eInst.data});  temp; end) , " @ 201,17"); dCache.req = Valid(MemReq{op: op, addr: eInst.addr, data:eInst.data});
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 202,13");
            end

            $display("<CodeLogger>:", "Set  :   ", "cur_e2w", " =  ", fshow(begin let temp = cur_e2w; temp = Valid(E2W{pc:pcE,iType:eInst.iType,dst:eInst.dst,data:eInst.data});  temp; end) , " @ 204,13"); cur_e2w= Valid(E2W{pc: pcE, iType: eInst.iType, dst: eInst.dst, data:eInst.data});
            // bypass signal
            // redirection signal
            $display("<CodeLogger>:", "Set  :   ", "annul", " =  ", fshow(begin let temp = annul; temp = (eInst.nextPc!=d2e_v.guessed_pc);  temp; end) , " @ 207,13"); annul=  (eInst.nextPc!=d2e_v.guessed_pc);
            $display("<CodeLogger>:", "Set  :   ", "redirectPC", " =  ", fshow(begin let temp = redirectPC; temp = eInst.nextPc;  temp; end) , " @ 208,13"); redirectPC=eInst.nextPc;
            
            $display("<CodeLogger>:", "If   :   is_branch    =  ", fshow(is_branch) , " @ 210,13");
            if(is_branch) begin
            $display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 210,32");
                $display("<CodeLogger>:", "Set  :     ", "btb.req", " =  ", fshow(begin let temp = btb.req; temp = Valid(BTBArgs{pc:pcE[11:0],next_pc:eInst.nextPc[11:0]});  temp; end) , " @ 211,17"); btb.req= Valid(BTBArgs{pc:pcE[11:0], next_pc: eInst.nextPc[11:0]});
                // update BTB
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 213,13");
end
            //
            $display("<CodeLogger>:", "Set  :   ", "cur_d2e", " =  ", fshow(begin let temp = cur_d2e; temp = Invalid;  temp; end) , " @ 215,13"); cur_d2e=Invalid;
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 216,9");
        end 
        // no more mutation of cur e2w more at this point

        $display("<CodeLogger>:", "Write: ", "e2w", " =  ", fshow(begin let temp = e2w; temp = cur_e2w;  temp; end) , " @ 219,9"); e2w<= cur_e2w;

        /////////////////////
        //Redirection logic//
        /////////////////////
        $display("<CodeLogger>:", "If   : annul  =  ", fshow(annul) , " @ 224,9");
if(annul)begin
$display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 224,23");
            $display("Redirect to : ",fshow(redirectPC));
            $display("<CodeLogger>:", "If   :   isValid(cur_f2d) =  ", fshow(isValid(cur_f2d)) , " @ 226,13");
            if(isValid(cur_f2d)) begin
            $display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 226,39");
                $display("<CodeLogger>:", "Set  :     ", "cur_f2d", " =  ", fshow(begin let temp = cur_f2d; temp = Valid(F2D{poisoned:True});  temp; end) , " @ 227,17"); cur_f2d=Valid(F2D{ poisoned:True});
                $display("Poisoning f2d");
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 229,13");
            end
            //cur_pc=redirectPC;
            
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 232,9");
        end
        
        //////////////////
        // Decode Stage //
        //////////////////
       
        $display("dstW:",fshow(dstW), " dataW:", fshow(dataW));
        $display("dstE:",fshow(dstE), " dataE:", fshow(dataE));
        Word inst =fromMaybe(?,iCache.data);$display("<CodeLogger>:", "Init : inst   =  ", fshow(inst) , " @ 240,14"); 
        DecodedInst dInst = decode(inst);$display("<CodeLogger>:", "Init : dInst  =  ", fshow(dInst) , " @ 241,21"); 
        
        Maybe#(Word) rVal1 = Valid(rf.rd1(dInst.src1));$display("<CodeLogger>:", "Init : rVal1  =  ", fshow(rVal1) , " @ 243,22"); 
        $display("<CodeLogger>:", "If   : dInst.src1!=0&&dInst.src1==dstW    =  ", fshow(dInst.src1!=0&&dInst.src1==dstW) , " @ 244,9");
        if(dInst.src1 !=0 && dInst.src1 == dstW) begin
        $display("<CodeLogger>:", "Set  : ", "rVal1", " =  ", fshow(begin let temp = rVal1; temp = dataW;  temp; end) , " @ 244,50"); rVal1=dataW;
        end
        $display("<CodeLogger>:", "If   : dInst.src1!=0&&dInst.src1==dstE    =  ", fshow(dInst.src1!=0&&dInst.src1==dstE) , " @ 245,9");
        if(dInst.src1 !=0 && dInst.src1 == dstE) begin
        $display("<CodeLogger>:", "Set  : ", "rVal1", " =  ", fshow(begin let temp = rVal1; temp = dataE;  temp; end) , " @ 245,50"); rVal1=dataE;
        end
        Word val1 = fromMaybe(?,rVal1);$display("<CodeLogger>:", "Init : val1   =  ", fshow(val1) , " @ 246,14"); 
        Maybe#(Word) rVal2 = Valid(rf.rd2(dInst.src2));$display("<CodeLogger>:", "Init : rVal2  =  ", fshow(rVal2) , " @ 247,22"); 
        $display("<CodeLogger>:", "If   : dInst.src2!=0&&dInst.src2==dstW    =  ", fshow(dInst.src2!=0&&dInst.src2==dstW) , " @ 248,9");
        if(dInst.src2 !=0 && dInst.src2 == dstW) begin
        $display("<CodeLogger>:", "Set  : ", "rVal2", " =  ", fshow(begin let temp = rVal2; temp = dataW;  temp; end) , " @ 248,50"); rVal2=dataW;
        end
        $display("<CodeLogger>:", "If   : dInst.src2!=0&&dInst.src2==dstE    =  ", fshow(dInst.src2!=0&&dInst.src2==dstE) , " @ 249,9");
        if(dInst.src2 !=0 && dInst.src2 == dstE) begin
        $display("<CodeLogger>:", "Set  : ", "rVal2", " =  ", fshow(begin let temp = rVal2; temp = dataE;  temp; end) , " @ 249,50"); rVal2=dataE;
        end
        Word val2 = fromMaybe(?,rVal2);$display("<CodeLogger>:", "Init : val2   =  ", fshow(val2) , " @ 250,14"); 
        Bool hazard1 = (dInst.src1 !=0 && (!isValid(rVal1)));$display("<CodeLogger>:", "Init : hazard1    =  ", fshow(hazard1) , " @ 251,14"); 
        Bool hazard2 = (dInst.src2 !=0 && (!isValid(rVal2)));$display("<CodeLogger>:", "Init : hazard2    =  ", fshow(hazard2) , " @ 252,14"); 
        Bool data_hazard = hazard1 || hazard2;$display("<CodeLogger>:", "Init : data_hazard    =  ", fshow(data_hazard) , " @ 253,14"); 
        // Test fetching for thinkign about hazard
        
        Bool iStall = False;$display("<CodeLogger>:", "Init : iStall =  ", fshow(iStall) , " @ 256,14"); 
        // cur_f2d = f2d at this point
        Bool decode_feeder_ready = isValid(cur_f2d) && isValid(iCache.data);$display("<CodeLogger>:", "Init : decode_feeder_ready    =  ", fshow(decode_feeder_ready) , " @ 258,14"); 
        let f2d_v = fromMaybe(?, cur_f2d);$display("<CodeLogger>:", "Init : f2d_v  =  ", fshow(f2d_v) , " @ 259,9"); 
        Bool poision_decode =  f2d_v.poisoned;$display("<CodeLogger>:", "Init : poision_decode =  ", fshow(poision_decode) , " @ 260,14"); 
        Bool fire_decode = (decode_feeder_ready) &&( poision_decode || (!data_hazard && !isValid(cur_d2e)));$display("<CodeLogger>:", "Init : fire_decode    =  ", fshow(fire_decode) , " @ 261,14"); 
        

        Word guessed_pc = btb.predictedNextPC(cur_pc[11:0]);$display("<CodeLogger>:", "Init : guessed_pc =  ", fshow(guessed_pc) , " @ 264,14"); 
        
        // Real pc that decode should decode here is pc (if ! annul)
        // and it should be something else if annul
        $display("<CodeLogger>:", "If   : fire_decode    =  ", fshow(fire_decode) , " @ 268,9");
if (fire_decode) begin
$display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 268,31");

            $display("Decode:", fshow(D2E{pc: cur_pc, guessed_pc: guessed_pc, dInst: dInst, rVal1:val1, rVal2: val2}));
            $display("<CodeLogger>:", "Set  :   ", "cur_d2e", " =  ", fshow(begin let temp = cur_d2e; temp = Valid(D2E{pc:cur_pc,guessed_pc:guessed_pc,dInst:dInst,rVal1:val1,rVal2:val2});  temp; end) , " @ 271,13"); cur_d2e=Valid(D2E{pc: cur_pc, guessed_pc: guessed_pc, dInst: dInst, rVal1:val1, rVal2: val2});
            $display("<CodeLogger>:", "Set  :   ", "cur_f2d", " =  ", fshow(begin let temp = cur_f2d; temp = Invalid;  temp; end) , " @ 272,13"); cur_f2d=Invalid;
            
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 274,9");
        end 
        
        $display("<CodeLogger>:", "If   : poision_decode =  ", fshow(poision_decode) , " @ 276,9");
        if(poision_decode) begin
        $display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 276,33");
            $display("<CodeLogger>:", "Set  :   ", "cur_d2e", " =  ", fshow(begin let temp = cur_d2e; temp = Invalid;  temp; end) , " @ 277,13"); cur_d2e=Invalid;
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 278,9");
        end
        // No more mutation of d2e at this point
        $display("<CodeLogger>:", "Write: ", "d2e", " =  ", fshow(begin let temp = d2e; temp = cur_d2e;  temp; end) , " @ 280,9"); d2e<=cur_d2e;
        /////////////////
        // Fetch Stage //
        /////////////////
       // Word nextPc = fetch_pc;
        // Invariant 1: There must always be ongoing instruction request in the i Cache
        // That is cur_f2d must always be valid

        // Calculate fetch pc;
        $display("<CodeLogger>:", "If   : annul  =  ", fshow(annul) , " @ 289,9");
if(annul) begin
$display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 289,24");
            $display("<CodeLogger>:", "Set  :   ", "cur_pc", " =  ", fshow(begin let temp = cur_pc; temp = redirectPC;  temp; end) , " @ 290,13"); cur_pc=redirectPC;
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 291,9");
        end else begin
        $display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 291,23");
            $display("<CodeLogger>:", "If   :   fire_decode&&!poision_decode =  ", fshow(fire_decode&&!poision_decode) , " @ 292,13");
            if(fire_decode && !poision_decode) begin
            $display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 292,53");
                $display("<CodeLogger>:", "Set  :     ", "cur_pc", " =  ", fshow(begin let temp = cur_pc; temp = guessed_pc;  temp; end) , " @ 293,17"); cur_pc=guessed_pc;
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 294,13");
            end else begin
            $display("<CodeLogger>:", "Enter: Scope Level    =  2 @ 294,27");
                $display("<CodeLogger>:", "Set  :     ", "cur_pc", " =  ", fshow(begin let temp = cur_pc; temp = cur_pc;  temp; end) , " @ 295,17"); cur_pc=cur_pc;
            $display("<CodeLogger>:", "Exit : Scope Level    =  2 @ 296,13");
            end
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 297,9");
        end
        // fetch next px
        $display("<CodeLogger>:", "If   : iCache.reqEnabled  =  ", fshow(iCache.reqEnabled) , " @ 299,9");
if(iCache.reqEnabled )begin
$display("<CodeLogger>:", "Enter: Scope Level    =  1 @ 299,36");
            $display("Fetch ", fshow(MemReq{op:Ld, addr:cur_pc, data: 0}));
            $display("<CodeLogger>:", "Set  :   ", "iCache.req", " =  ", fshow(begin let temp = iCache.req; temp = Valid(MemReq{op:Ld,addr:cur_pc,data:0});  temp; end) , " @ 301,13"); iCache.req = Valid(MemReq{op:Ld, addr:cur_pc, data: 0});
            $display("<CodeLogger>:", "Set  :   ", "cur_f2d", " =  ", fshow(begin let temp = cur_f2d; temp = Valid(F2D{poisoned:False});  temp; end) , " @ 302,13"); cur_f2d=Valid(F2D{ poisoned: False});
        $display("<CodeLogger>:", "Exit : Scope Level    =  1 @ 303,9");
        end
        
        $display("<CodeLogger>:", "Write: ", "f2d", " =  ", fshow(begin let temp = f2d; temp = cur_f2d;  temp; end) , " @ 305,9"); f2d<=cur_f2d;
        $display("Pc to :" ,fshow(cur_pc));
        $display("<CodeLogger>:", "Write: ", "pc", " =  ", fshow(begin let temp = pc; temp = cur_pc;  temp; end) , " @ 307,9"); pc<=cur_pc;
        // Initilization
        
    endrule

    method Word getPc = pc;
endmodule
