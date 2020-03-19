package bmsc;


/**
 * 
 * 
 * Represents Minispec Parameter's
 * @author boomza654
 * Immutable
 * Should represnet either Integer or a Minspec TYpe
 *
 */
public class Parameter {
    private Integer number;
    private Type type;
    
    /**
     * Check Representation
     * @throws AssertionError If type and number are both null or both not null
     */
    private void checkRep() {
        assert (number==null)!= (type==null) : "Invalid Parameter num="+number+" type="+type ;
    }
    private Parameter(Integer number, Type type) {
        this.number=number;
        this.type=type;
        checkRep();
    }
    
    /**
     * Create Int Param
     * @param x
     * @return Integer parameter
     */
    public static Parameter intParam(int x) { return new Parameter(x,null); }
    /**
     * Create Type Param
     * @param t
     * @return Type Parameter
     */
    public static Parameter typeParam(Type t) { return new Parameter(null,t);}
    
    public int getNum() {if(number==null) throw new RuntimeException("THis parameter is not a intParam"); return number; } 
    public Type getType() {if(type==null) throw new RuntimeException("THis parameter is not a typeParam"); return type;}
    public boolean isNum() {return number!=null;}
    public boolean isType() {return type!=null;}
    
    @Override public String toString() {
        if(number!=null)
            return number.toString();
        else
            return type.toString();
    }
    @Override public int hashCode() {
        if(isNum()) return number;
        if(isType()) return type.hashCode();
        throw new RuntimeException("Not num and not type?!?!?!?!");
    }
    @Override public boolean equals(Object obj) {
        if(!(obj instanceof Parameter)) return false;
        Parameter that = (Parameter) obj;
        
        if(this.isNum() && that.isNum()) {
            return this.number.equals(that.number);
        }
        else if (this.isType() && that.isType()) {
            return this.type.equals(that.type);
        } else {
            return false;
        }
    }
    
    
}
