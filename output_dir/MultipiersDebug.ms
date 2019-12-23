// Two inputs to an n-bit multiplier.
typedef struct {
    Bit#(n) a;
    Bit#(n) b; 
} MultiplierInput#( Integer n ); 

// Reference functions that use the '*' operator
function Bit#(2*n) multiply_unsigned#(Integer n)(Bit#(n) a, Bit#(n) b);
    return zeroExtend(a) * zeroExtend(b);
endfunction

function Bit#(2*n) multiply_signed#(Integer n)(Bit#(n) a, Bit#(n) b);
    //return signedMul();
    Int#(n) a_int = unpack(a);
    Int#(n) b_int = unpack(b);
    Int#(2*n) product_int = signExtend(a_int) * signExtend(b_int);
    return pack(product_int);
endfunction

// Multiplication by repeated addition
function Bit#(2*n) multiply_by_adding#(Integer n)(Bit#(n) a, Bit#(n) b);
    Bit#(n) ret =0;// TODO: Implement this in Exercise 1
    Bit#(n+1) window=0;
    for(Integer i=0;i<n;i=i+1) begin
        Bit#(n+1) toadd = (a[i]==0)? 0:{'b0,b};
        window = window + toadd;
        ret[i]=window[0];
        window = {'b0, window[n:1]};
    end
    return {window[n-1:0],ret};
endfunction

// Folded multiplier by repeated addition
module FoldedMultiplier#(Integer n);

    // You can use these registers or create your own if you want
    RegU#(Bit#(n)) a;
    RegU#(Bit#(n)) b;
    RegU#(Bit#(2*n)) prod;
    RegU#(Bit#(log2(n)+1)) i;
    Reg#(Bool) isFinished (True);
    Reg#(Bool) isInitialized(False);
    rule mulStep;
	$display("--------------------");
	$display("Boom Debug:", "Modl : a  =  ", fshow(a));
	$display("Boom Debug:", "Modl : b  =  ", fshow(b));
	$display("Boom Debug:", "Modl : prod   =  ", fshow(prod));
	$display("Boom Debug:", "Modl : i  =  ", fshow(i));
	$display("Boom Debug:", "Modl : isFinished =  ", fshow(isFinished));
	$display("Boom Debug:", "Modl : isInitialized  =  ", fshow(isInitialized));
	$display("Boom Debug:", "Input: in =  ", fshow(in));
	$display("Boom Debug:", "If   : Taken  =  ", fshow(isValid(in)) , " @ 44,2");
	if(isValid(in)) begin
	$display("Boom Debug:", "Enter: Scope Level    =  1 @ 44,23");
        $display("Boom Debug:", "Write: ", "a", " =  ", fshow(fromMaybe(?,in).a) , " @ 45,9"); a<= fromMaybe(?,in).a;
        $display("Boom Debug:", "Write: ", "b", " =  ", fshow(fromMaybe(?,in).b) , " @ 46,9"); b<=fromMaybe(?,in).b;
        $display("Boom Debug:", "Write: ", "prod", " =  ", fshow(0) , " @ 47,9"); prod<=0;
        $display("Boom Debug:", "Write: ", "i", " =  ", fshow(n) , " @ 48,9"); i<=n;
        $display("Boom Debug:", "Write: ", "isFinished", " =  ", fshow(False) , " @ 49,9"); isFinished<=False;	
        $display("Boom Debug:", "Write: ", "isInitialized", " =  ", fshow(True) , " @ 50,9"); isInitialized<=True;
        $display("%x times %x", fromMaybe(?,in).a,fromMaybe(?,in).b);
	$display("Boom Debug:", "Exit : Scope Level    =  1 @ 52,2");
	end
	else begin
	$display("Boom Debug:", "If   : Taken  =  ", fshow(!isFinished) , " @ 53,7");
	if(!isFinished) begin
	$display("Boom Debug:", "Enter: Scope Level    =  1 @ 53,28");
        $display("Boom Debug:", "Init : lastProd   =  ", fshow(prod<<1) , " @ 54,19"); Bit#(2*n) lastProd= prod<<1;
        $display("Boom Debug:", "Init : toadd  =  ", fshow((b[n-1]==0)?0:{0,a}) , " @ 55,19"); Bit#(2*n) toadd = (b[n-1]==0)? 0: {0,a};
        $display("b=%b",b);
        $display("Boom Debug:", "Write: ", "prod", " =  ", fshow(lastProd+toadd) , " @ 57,9"); prod<=lastProd+toadd;
        $display("Boom Debug:", "Write: ", "b", " =  ", fshow(b<<1) , " @ 58,9"); b<=b<<1;
        $display("Boom Debug:", "Write: ", "i", " =  ", fshow(i-1) , " @ 59,9"); i<=i-1;
        $display("Boom Debug:", "Write: ", "isFinished", " =  ", fshow((i==1)) , " @ 60,9"); isFinished<=(i==1);
	$display("Boom Debug:", "Exit : Scope Level    =  1 @ 61,2");
	end
        end
        // TODO: Implement this in Exercise 
    endrule

    // When there is a new pair of numbers to multiply, they
    // will be passed in as a valid MultiplierInput.
    input Maybe#(MultiplierInput#(n))
        in default = Invalid;

    // getResult should return the most recent multiplication result,
    // or Invalid if the multiplier is calculating the next result.
    // If no input has been given yet, the result should be Invalid.
    method Maybe#(Bit#(2*n)) getResult;
        return (isFinished && isInitialized)?Valid(prod):Invalid;
    endmethod
endmodule
//
//
//
//
//
//
//KOGGE STONE ADDER
//
//
//
//
//
//
//
//
//
function Bit#(w) normalAdd#(Integer w) (Bit#(w) a, Bit#(w) b);
	return a+b;
endfunction
 
function Bit#(w+1) lookAheadAdd#(Integer w) (Bit#(w) a, Bit#(w) b);

   Bit#(w) p=a^b;
   Bit#(w) g=a&b;
   for(Integer stepsize=1;stepsize<w;stepsize=stepsize*2) begin
        //g= (p&(g<<stepsize)) |g;
        //p={p[w-1:stepsize] & p[w-stepsize-1:0], p[stepsize-1:0]};
        for(Integer ind=w-1; ind>=stepsize; ind=ind-1) begin
            g[ind]=(p[ind]&g[ind-stepsize] )| g[ind];
            p[ind]=p[ind]&p[ind-stepsize];
        end
   end
   Bit#(w+1) ret=0;
   Bit#(w+1) cc=0;
   cc={g,'b0};
   ret=cc;
   ret = {'b0,(a^b)}^ret;
   return ret;
endfunction
function Bit#(2) selAdd#(1) (Bit#(1) a, Bit#(1) b, Bit#(1) c);
        return {(a&b)|(b&c)|(a&c),a^b^c};
endfunction
function Bit#(w+1) selAdd#(Integer w)(Bit#(w) a,Bit#(w) b, Bit#(1) c);
    Bit#(w+1) ret =0;
   
    Integer h=w/2;
    Bit#(w-h+1) first0 = selAdd#(w-h)(a[w-1:h],b[w-1:h],0);
    Bit#(w-h+1) first1 = selAdd#(w-h)(a[w-1:h],b[w-1:h],1);
    Bit#(h+1) last = selAdd#(h)(a[h-1:0],b[h-1:0],c);
    ret= {(last[h]==0)?first0:first1, last[h-1:0]};
   
    return ret;
endfunction

// Fast folded multiplier by repeated addition
module FastFoldedMultiplier#( Integer n );

    // You can use these registers or create your own if you want
    RegU#(Bit#(n)) a;
    RegU#(Bit#(n)) b;
    RegU#(Bit#(2*n)) prod;
    RegU#(Bit#(log2(n)+1)) i;
    Reg#(Bool) isFinished (True);
    Reg#(Bool) isInitialized(False);
    rule mulStep;
	$display("--------------------");
	$display("Boom Debug:", "Modl : a  =  ", fshow(a));
	$display("Boom Debug:", "Modl : b  =  ", fshow(b));
	$display("Boom Debug:", "Modl : prod   =  ", fshow(prod));
	$display("Boom Debug:", "Modl : i  =  ", fshow(i));
	$display("Boom Debug:", "Modl : isFinished =  ", fshow(isFinished));
	$display("Boom Debug:", "Modl : isInitialized  =  ", fshow(isInitialized));
	$display("Boom Debug:", "Input: in =  ", fshow(in));
	$display("Boom Debug:", "If   : Taken  =  ", fshow(isValid(in)) , " @ 142,2");
	if(isValid(in)) begin
	$display("Boom Debug:", "Enter: Scope Level    =  1 @ 142,23");
	 $display("Boom Debug:", "Write: ", "a", " =  ", fshow(fromMaybe(?,in).a) , " @ 143,3"); a<= fromMaybe(?,in).a;
	 $display("Boom Debug:", "Write: ", "b", " =  ", fshow(fromMaybe(?,in).b) , " @ 144,3"); b<=fromMaybe(?,in).b;
	 $display("Boom Debug:", "Write: ", "prod", " =  ", fshow(0) , " @ 145,3"); prod<=0;
	 $display("Boom Debug:", "Write: ", "i", " =  ", fshow(n) , " @ 146,3"); i<=n;
	$display("Boom Debug:", "Write: ", "isFinished", " =  ", fshow(False) , " @ 147,2"); isFinished<=False;	
	$display("Boom Debug:", "Write: ", "isInitialized", " =  ", fshow(True) , " @ 148,2"); isInitialized<=True;
	$display("%x times %x", fromMaybe(?,in).a,fromMaybe(?,in).b);
	$display("Boom Debug:", "Exit : Scope Level    =  1 @ 150,2");
	end
	else begin
	$display("Boom Debug:", "If   : Taken  =  ", fshow(!isFinished) , " @ 151,7");
	if(!isFinished)
	begin
	$display("Boom Debug:", "Enter: Scope Level    =  1 @ 152,7");
	 $display("Boom Debug:", "Init : lastProd   =  ", fshow(prod<<1) , " @ 153,13"); Bit#(2*n) lastProd= prod<<1;
	// Bit#(2*n) toadd = (b[n-1]==0)? 0: {0,a};
	$display("b=%b",b);

        // prod<=selAdd#(2*n)(lastProd,toadd,0)[2*n-1:0];
	 
	 $display("Boom Debug:", "Write: ", "prod", " =  ", fshow((b[n-1]==0)?0:(selAdd#(2*n)(lastProd,zeroExtend(a),0)[2*n-1:0])) , " @ 159,3"); prod<=(b[n-1]==0)? 0:(selAdd#(2*n)(lastProd,zeroExtend(a),0)[2*n-1:0]);
	 
	$display("Boom Debug:", "Write: ", "b", " =  ", fshow(b<<1) , " @ 161,2"); b<=b<<1;
	$display("Boom Debug:", "Write: ", "i", " =  ", fshow(i-1) , " @ 162,2"); i<=i-1;
	 $display("Boom Debug:", "Write: ", "isFinished", " =  ", fshow((i==1)) , " @ 163,3"); isFinished<=(i==1);
	$display("Boom Debug:", "Exit : Scope Level    =  1 @ 164,2");
	end
        end
        // TODO: Implement this in Exercise 
    endrule

    // When there is a new pair of numbers to multiply, they
    // will be passed in as a valid MultiplierInput.
    input Maybe#(MultiplierInput#(n))
        in default = Invalid;

    // getResult should return the most recent multiplication result,
    // or Invalid if the multiplier is calculating the next result.
    // If no input has been given yet, the result should be Invalid.
    method Maybe#(Bit#(2*n)) getResult;
        // TODO: Implement this in Exercise 2
        return (isFinished && isInitialized)?Valid(prod):Invalid;
    endmethod

endmodule
<EOF>