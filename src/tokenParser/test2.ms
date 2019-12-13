import CacheTypes;
import CacheHelpers;
import MainMemory;

// ReqStatus (defined in CacheTypes.ms) is used to keep track of the state of the current request
//typedef enum {
//    Ready,         // The cache is ready for a new request
//    Lookup,        // Issued a lookup to tag/data arrays
//    Writeback,     // In main memory access for dirty writeback
//    Fill           // In main memory access for requested data
//} ReqStatus;
//
// Possible flows:
//   HIT: Ready -> Lookup -> Ready
//   MISS, line is clean: Ready -> Lookup -> Fill
//   MISS, line is dirty: Ready -> Lookup -> Writeback -> Fill



// TODO: Complete the implementation of TwoWayCache
// NOTE: Implementing this module requires about 20 new lines of code from DirectMappedCache
module TwoWayCache(MainMemory mainMem);
    // SRAM arrays, one element per way (e.g., dataArrays[0] is the data array of way 0).
    Vector#(2, SRAM#(logCacheSets, Line)) dataArrays;
    Vector#(2, SRAM#(logCacheSets, CacheTag)) tagArrays;
    Vector#(2, SRAM#(logCacheSets, CacheStatus)) statusArrays;

    // LRU bits, one per set. We implement it as registers instead of an SRAM
    // array, because with small caches this is just a few bits (e.g., 64), and
    // SRAMs make sense only for larger memories. This also makes the code
    // simpler, and will make the cache easier to pipeline in the future.
    Vector#(cacheSets, RegU#(Bit#(1))) lru;

    // Registers for holding the current state of the cache and how far along
    // it is in processing a request.
    RegU#(MemReq) curReq;
    Reg#(ReqStatus) state(Ready);
 
    // Hit/miss counters
    Reg#(Word) hits(0);
    Reg#(Word) misses(0);
    // out registers
    Reg#(Maybe#(Word)) data_out(Invalid);

    input Maybe#(MemReq) req default = Invalid;
    
    //TODO return True if the cache can accept a new request
    method Bool reqEnabled;
        Bool isReady=False;
        case(state)
            Ready: begin
                isReady=True;
            end
            Lookup: begin
                if (isValid(tagArrays[0].data)) begin
                    let resultTag0 = fromMaybe(unpack(0), tagArrays[0].data);
                    let resultStatus0 = fromMaybe(unpack(0), statusArrays[0].data);
                    let resultData0 = fromMaybe(unpack(0), dataArrays[0].data);
                    let resultTag1 = fromMaybe(unpack(0), tagArrays[1].data);
                    let resultStatus1 = fromMaybe(unpack(0), statusArrays[1].data);
                    let resultData1 = fromMaybe(unpack(0), dataArrays[1].data);
                    

                    let wOffset = getWordOffset(curReq.addr);
                    let tag = getTag(curReq.addr);
                    let idx = getIndex(curReq.addr);

                    let hit0 = (resultTag0 == tag) && (resultStatus0 != Invalid);
                    let hit1 = (resultTag1 == tag) && (resultStatus1 != Invalid);
                    // Accessed way
                    Bit#(1) way = ((hit0)?0:1 );
                    if(!hit0 && !hit1) begin 
                        way = lru[idx];
                    end
                    let resultData = (way==0)?resultData0:resultData1;
                    let resultStatus = (way==0)?resultStatus0:resultStatus1;
                    let resultTag = (way==0)?resultTag0:resultTag1;

                    if((hit0 || hit1) && curReq.op == Ld) begin
                        isReady=True;                        
                    end
                end
            end           
        endcase
        return isReady;
    endmethod

    rule tick;
        Maybe#(Word) out = Invalid;
        case(state)
            Ready: begin
                if ( isValid(req)) begin
                    MemReq request =fromMaybe(unpack(0), req);
                    curReq <= request;
                    state <= Lookup;
                    CacheIndex idx = getIndex(request.addr); 
                    /*
                    let tag = getTag(request.addr);
                    let wOffset = getWordOffset(request.addr);
                    $display("Request :", fshow(request));
                    $display("Access: Tag:",fshow(tag)," Index: ",fshow(idx), " Offset: ",fshow(wOffset));
                    */
                    TagReq tag_request = TagReq{ addr: idx, write: False, data: unpack(0) };
                    DataReq data_request = DataReq{ addr: idx, write: False, data: unpack(0) };
                    StatusReq status_request = StatusReq{ addr: idx, write: False, data: unpack(0) };

                    dataArrays[0].req = Valid(data_request);
                    tagArrays[0].req = Valid(tag_request);
                    statusArrays[0].req = Valid(status_request);
                    dataArrays[1].req = Valid(data_request);
                    tagArrays[1].req = Valid(tag_request);
                    statusArrays[1].req = Valid(status_request);
                end
            end
            Lookup: begin
                if (isValid(tagArrays[0].data)) begin
                    let resultTag0 = fromMaybe(unpack(0), tagArrays[0].data);
                    let resultStatus0 = fromMaybe(unpack(0), statusArrays[0].data);
                    let resultData0 = fromMaybe(unpack(0), dataArrays[0].data);
                    let resultTag1 = fromMaybe(unpack(0), tagArrays[1].data);
                    let resultStatus1 = fromMaybe(unpack(0), statusArrays[1].data);
                    let resultData1 = fromMaybe(unpack(0), dataArrays[1].data);
                    

                    let wOffset = getWordOffset(curReq.addr);
                    let tag = getTag(curReq.addr);
                    let idx = getIndex(curReq.addr);

                    let hit0 = (resultTag0 == tag) && (resultStatus0 != Invalid);
                    let hit1 = (resultTag1 == tag) && (resultStatus1 != Invalid);
                    // Accessed way
                    Bit#(1) way = ((hit0)?0:1 );
                    if(!hit0 && !hit1) begin 
                        way = lru[idx];
                    end
                    let resultData = (way==0)?resultData0:resultData1;
                    let resultStatus = (way==0)?resultStatus0:resultStatus1;
                    let resultTag = (way==0)?resultTag0:resultTag1;

                    if(hit0 || hit1) begin
                        hits<=hits+1;
                        
                        if (curReq.op == St) begin
                            state<=Ready;
                            resultData[wOffset] = curReq.data;

                            TagReq tag_request = TagReq{ addr: idx, write: True, data: tag };
                            DataReq data_request = DataReq{ addr: idx, write: True, data: resultData};
                            StatusReq status_request = StatusReq{ addr: idx, write: True, data: Dirty };

                            dataArrays[way].req = Valid(data_request);
                            tagArrays[way].req = Valid(tag_request);
                            statusArrays[way].req = Valid(status_request);
                        end
                        else begin
                            out=Valid(resultData[wOffset]);
                            if(isValid(req)) begin
                                MemReq request =fromMaybe(unpack(0), req);
                                curReq <= request;
                                state <= Lookup;
                                CacheIndex newidx = getIndex(request.addr); 
                                TagReq tag_request = TagReq{ addr: newidx, write: False, data: unpack(0) };
                                DataReq data_request = DataReq{ addr: newidx, write: False, data: unpack(0) };
                                StatusReq status_request = StatusReq{ addr: newidx, write: False, data: unpack(0) };
                                dataArrays[0].req = Valid(data_request);
                                tagArrays[0].req = Valid(tag_request);
                                statusArrays[0].req = Valid(status_request);
                                dataArrays[1].req = Valid(data_request);
                                tagArrays[1].req = Valid(tag_request);
                                statusArrays[1].req = Valid(status_request);
                            end else begin
                                state<=Ready;
                            end
                        end
                    end
                    else begin
                        misses <= misses + 1;
                        
                        if (resultStatus != Dirty) begin
                            mainMem.req = Valid(LineReq{op: Ld, lineAddr: getLineAddr(curReq.addr), data: unpack(0)});
                            state <= Fill;
                        end else begin
                            mainMem.req = Valid(LineReq{op: St, lineAddr: {resultTag, idx}, data: resultData});
                            state <= Writeback;
                        end
                    end
                    lru[idx]<=~way;
                end
            end
            Writeback: begin
                if (mainMem.reqEnabled) begin
                    mainMem.req = Valid(LineReq{op: Ld, lineAddr: getLineAddr(curReq.addr), data: unpack(0)});
                    state <= Fill;
                end
            end
            Fill: begin
                if ( isValid(mainMem.data)) begin
                    let resultData = fromMaybe(unpack(0), mainMem.data);
                    let wOffset = getWordOffset(curReq.addr);
                    let tag = getTag(curReq.addr);
                    let idx = getIndex(curReq.addr);
                    CacheStatus status = Clean;
                    state <= Ready;
                    if (curReq.op == St) begin
                        resultData[wOffset] = curReq.data;
                        status=Dirty;
                    end
                    else begin
                        out = Valid(resultData[wOffset]);
                    end
                    Bit#(1) way = (~lru[idx]); // lru is set before hand
                    TagReq tag_request = TagReq{ addr: idx, write: True, data: tag };
                    DataReq data_request = DataReq{ addr: idx, write: True, data: resultData };
                    StatusReq status_request = StatusReq{ addr: idx, write: True, data: status };
                    
                    dataArrays[way].req = Valid(data_request);
                    tagArrays[way].req = Valid(tag_request);
                    statusArrays[way].req = Valid(status_request);
                end
            end
        endcase
        // Old Version: out is then write to data out but now we just skip return out from method directly
        // leave out like dat to ensure the process before
        //data_out<=out;
    endrule

    method Maybe#(Word) data;
        // This method should return a Valid output in only two cases:
        // 1. On a load hit (we're in the Lookup state, there's a hit, and
        //    curReq.op == Ld).
        // 2. On a fill for a load request (we're in the Fill state,
        //    mainMem.data is valid, and curReq.op == Ld).
        // In all other cases, the output should be Invalid
        //
 
        //TODO Your code here
        
        Maybe#(Word) out = Invalid;
        case(state)
            Lookup: begin
                if (isValid(tagArrays[0].data)) begin
                    let resultTag0 = fromMaybe(unpack(0), tagArrays[0].data);
                    let resultStatus0 = fromMaybe(unpack(0), statusArrays[0].data);
                    let resultData0 = fromMaybe(unpack(0), dataArrays[0].data);
                    let resultTag1 = fromMaybe(unpack(0), tagArrays[1].data);
                    let resultStatus1 = fromMaybe(unpack(0), statusArrays[1].data);
                    let resultData1 = fromMaybe(unpack(0), dataArrays[1].data);
                    

                    let wOffset = getWordOffset(curReq.addr);
                    let tag = getTag(curReq.addr);
                    let idx = getIndex(curReq.addr);

                    let hit0 = (resultTag0 == tag) && (resultStatus0 != Invalid);
                    let hit1 = (resultTag1 == tag) && (resultStatus1 != Invalid);
                    // Accessed way
                    Bit#(1) way = ((hit0)?0:1 );
                    if(!hit0 && !hit1) begin 
                        way = lru[idx];
                    end
                    let resultData = (way==0)?resultData0:resultData1;
                    let resultStatus = (way==0)?resultStatus0:resultStatus1;
                    let resultTag = (way==0)?resultTag0:resultTag1;

                    if(hit0 || hit1) begin
                        
                        if (curReq.op == Ld) begin
                            out=Valid(resultData[wOffset]);
                        end
                    end
                end
            end
            Fill: begin
                if ( isValid(mainMem.data)) begin
                    let resultData = fromMaybe(unpack(0), mainMem.data);
                    let wOffset = getWordOffset(curReq.addr);
                    let tag = getTag(curReq.addr);
                    let idx = getIndex(curReq.addr);
                    CacheStatus status = Clean;
                    if (curReq.op == Ld) begin
                        out = Valid(resultData[wOffset]);
                    end
                end
            end
        endcase
    return out;
    endmethod

    method Bit#(32) getHits = hits;
    method Bit#(32) getMisses = misses;
endmodule
