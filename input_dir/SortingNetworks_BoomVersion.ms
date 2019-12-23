import Common;

///////////////////////////////

// Description: Unsigned 2-Word comparator
// Arguments: a b (Word (32-bit) values)
// Parameterization:
// 	sortUp: 1 = larger number returned in upper, 0 = larger number returned in lower
// Return: comparison result
function CmpRes compare#(0)(Word a, Word b);
	CmpRes ans= CmpRes{upper: (a<b)?a:b, lower: (a<b)?b:a};
	return ans;
endfunction
// Return: comparison result
function CmpRes compare#(1)(Word a, Word b);
	CmpRes ans= CmpRes{upper: (a<b)?b:a, lower: (a<b)?a:b};
	return ans;
endfunction

/**
* BOOMZA MODULE
*/
module BoomTester;
	// Test module
	BitonicSorter#(8) testmodule;
	Reg#(Bool) sent(False);
	rule test;
                Vector#(8,Word) dataIn;
		dataIn[0]=4;
		dataIn[1]=8;
		dataIn[2]=2;
		dataIn[3]=1;
		dataIn[4]=3;
		dataIn[5]=5;
		dataIn[6]=9;
		dataIn[7]=7;
		if(!sent)begin
			testmodule.dataIn=Valid(dataIn);
			sent<=True;
		end
		if(isValid(testmodule.getOutput)) begin
			$display("%x",fromMaybe(?,testmodule.getOutput));
			$finish();
		end
		$display("Cycle eiei");
	endrule
endmodule


module BitonicSorter#(2);
	input Maybe#(Vector#(2,Word)) dataIn default = Invalid;
	rule tick;
		if(isValid(dataIn))begin
			$display("Sort%d: %x",2,fromMaybe(?,dataIn));
			Vector#(2,Word) data=fromMaybe(?,dataIn);
			CmpRes ans = compare#(0)(data[0],data[1]);
			Vector#(2,Word) towrite;
			towrite[0]=ans.upper;
			towrite[1]=ans.lower;
			pipelineData<=Valid(towrite);
		end
		else begin
			pipelineData<=Invalid;
		end
		
	endrule
	Reg#(Maybe#(Vector#(2,Word))) pipelineData(Invalid);
	method Maybe#(Vector#(2,Word)) getOutput;
		return pipelineData;
	endmethod
endmodule

// Merger for 2^n
module BitonicMerger#(2);
	input Maybe#(Vector#(2,Word)) dataIn default = Invalid;
	rule tick;
		if(isValid(dataIn))begin
		
			$display("merge%d: %x",2,fromMaybe(?,dataIn));
			Vector#(2,Word) data=fromMaybe(?,dataIn);
			CmpRes ans = compare#(0)(data[0],data[1]);
			Vector#(2,Word) towrite;
			towrite[0]=ans.upper;
			towrite[1]=ans.lower;
			pipelineData<=Valid(towrite);
		end
		else begin
			pipelineData<=Invalid;
		end
		
	endrule
	Reg#(Maybe#(Vector#(2,Word))) pipelineData(Invalid);
	method Maybe#(Vector#(2,Word)) getOutput;
		return pipelineData;
	endmethod
endmodule

// Merger for n =2^k only
module BitonicMerger#(Integer n);
	input Maybe#(Vector#(n,Word)) dataIn default = Invalid;
	rule tick;
		// Stage to register
		if(isValid(dataIn))begin
		
			$display("merge%d: %x",n,fromMaybe(?,dataIn));
			Vector#(n,Word) data=fromMaybe(?,dataIn);
			Vector#(n,Word) partitioned;
			for(Integer i=0;i<n/2;i=i+1) begin

				CmpRes ans = compare#(0)(data[i],data[i+n/2]);
				partitioned[i]=ans.upper;
				partitioned[i+n/2]= ans.lower;
			end
			
			pipelineData<=Valid(partitioned);
		end
		else begin
			pipelineData<=Invalid;
		end
		// From register to mini
		if(isValid(pipelineData)) begin
			Vector#(n,Word) plugin = fromMaybe(?,pipelineData);
			merger1.dataIn=Valid(take(plugin));
			merger2.dataIn=Valid(takeTail(plugin));
		end
		else begin
			merger1.dataIn=Invalid;
			merger2.dataIn=Invalid;
		end
		
	endrule
	BitonicMerger#(n/2) merger1;
	BitonicMerger#(n/2) merger2; // assert even number here
	
	Reg#(Maybe#(Vector#(n,Word))) pipelineData(Invalid);

	method Maybe#(Vector#(n,Word)) getOutput;
		Maybe#(Vector#(n,Word)) res=Invalid;
		if(isValid(merger1.getOutput)) begin
			Vector#(n/2,Word) o1= fromMaybe(?,merger1.getOutput);
			Vector#(n/2,Word) o2= fromMaybe(?,merger2.getOutput);
			res = Valid(append(o1,o2));
		end
		return res;
	endmethod
endmodule
// Merger for n =2^k only
module BitonicSorter#(Integer n);
	input Maybe#(Vector#(n,Word)) dataIn default = Invalid;
	rule tick;
		// input to sorter
		if(isValid(dataIn))begin
		
			$display("sort%d: %x",n,fromMaybe(?,dataIn));
			Vector#(n,Word) data=fromMaybe(?,dataIn);
			sorter1.dataIn=Valid(take(data));
			sorter2.dataIn=Valid(takeTail(data));
		end
		else begin
			sorter1.dataIn=Invalid;
			sorter2.dataIn=Invalid;
		end
		// From out sort to merger
		if(isValid(sorter1.getOutput)) begin
			Vector#(n/2,Word) res1 = fromMaybe(?,sorter1.getOutput);
			Vector#(n/2,Word) res2 = reverse(fromMaybe(?,sorter2.getOutput));
			merger.dataIn=Valid(append(res1,res2));
		end
		else begin
			merger.dataIn=Invalid;
		end
		
	endrule
	BitonicSorter#(n/2) sorter1;
	BitonicSorter#(n/2) sorter2; // assert even number here
	BitonicMerger#(n) merger;
	

	method Maybe#(Vector#(n,Word)) getOutput;
		return merger.getOutput;
	endmethod
endmodule
/*
// Description: Pipelined 4-element signed Word sorting network
//
module BitonicSorter4;
	Vector#(3, Reg#(Maybe#(DataLines4))) pipelineData(Invalid);
	
	input Maybe#(DataLines4) dataIn default = Invalid;
	
	method Maybe#(DataLines4) getOutput;
	
		// YOUR CODE HERE //
	
	endmethod
	
	rule tick;

		// YOUR CODE HERE //
		
	endrule
endmodule


module BitonicMerger4;
	Vector#(3, Reg#(Maybe#(DataLines8))) pipelineData(Invalid);
	
	input Maybe#(DataLines8) dataIn default = Invalid;
	
	method Maybe#(DataLines8) getOutput;
	
		// YOUR CODE HERE //
	
	endmethod

	rule tick;

		// YOUR CODE HERE //
		
	endrule
endmodule

*/
// Description: Pipelined 8-element signed Word sorting network
//
module BitonicSorter8;

	// Instantiate your submodules here //
	BitonicSorter#(8) sorter;
	input Maybe#(DataLines8) dataIn default = Invalid;
	
	method Maybe#(DataLines8) getOutput;
	
		// YOUR CODE HERE //
		return sorter.getOutput;
	
	endmethod
	
	// Description: Pass inputs along to submodules
	rule feedInputs;

		// YOUR CODE HERE //
		sorter.dataIn=dataIn;
		
	endrule
	
endmodule


///////////////////////////////
//// Combinational Sorter /////
///////////////////////////////

function Vector#(n, T) bitonicSort#(Integer n, type T)(Vector#(n, T) in);
    if (n <= 1) begin
        return in;
    end else begin
        let lower = bitonicSort#(n/2, T)(take(in));
        let upper = bitonicSort#(n-n/2, T)(takeTail(in));
        return bitonicMerge#(n, T)(append(lower, reverse(upper)));
    end
endfunction


function Vector#(n, T) bitonicMerge#(Integer n, type T)(Vector#(n, T) x);
    if (n <= 1) begin
        return x;
    end else begin
        let s = x;
        for (Integer i = 0; i < n/2; i = i + 1) begin
            Integer j = i + n/2;
            if (x[i] > x[j]) begin
                s[i] = x[j];
                s[j] = x[i];
            end
        end
        let lower = bitonicMerge#(n/2, T)(take(s));
        let upper = bitonicMerge#(n-n/2, T)(takeTail(s));
        return append(lower, upper);
    end
endfunction
