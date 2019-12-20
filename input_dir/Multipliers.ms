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
	if(isValid(in)) begin
        a<= fromMaybe(?,in).a;
        b<=fromMaybe(?,in).b;
        prod<=0;
        i<=n;
        isFinished<=False;	
        isInitialized<=True;
        $display("%x times %x", fromMaybe(?,in).a,fromMaybe(?,in).b);
	end
	else if(!isFinished) begin
        Bit#(2*n) lastProd= prod<<1;
        Bit#(2*n) toadd = (b[n-1]==0)? 0: {0,a};
        $display("b=%b",b);
        prod<=lastProd+toadd;
        b<=b<<1;
        i<=i-1;
        isFinished<=(i==1);
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
	if(isValid(in)) begin
	 a<= fromMaybe(?,in).a;
	 b<=fromMaybe(?,in).b;
	 prod<=0;
	 i<=n;
	isFinished<=False;	
	isInitialized<=True;
	$display("%x times %x", fromMaybe(?,in).a,fromMaybe(?,in).b);
	end
	else if(!isFinished)
	begin
	 Bit#(2*n) lastProd= prod<<1;
	// Bit#(2*n) toadd = (b[n-1]==0)? 0: {0,a};
	$display("b=%b",b);

        // prod<=selAdd#(2*n)(lastProd,toadd,0)[2*n-1:0];
	 
	 prod<=(b[n-1]==0)? 0:(selAdd#(2*n)(lastProd,zeroExtend(a),0)[2*n-1:0]);
	 
	b<=b<<1;
	i<=i-1;
	 isFinished<=(i==1);
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
