package bmsc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Immutable
 * Represent a generalized identifier (identifier along with its evaluated paramater)
 * Ex. Bit#(8) Vector#(5,Bit#(8)) testfunc#(5) boom
 * @author boomza654
 *
 */
public class GeneralizedIdentifier {
    public final String name;
    public final List<Parameter> params;
    public GeneralizedIdentifier(String name, List<Parameter> params) {
        this.name=name;
        this.params= List.copyOf(params);
    }
    
    public GeneralizedIdentifier(GeneralizedIdentifier other) {
        this.name=other.name;
        this.params= List.copyOf(other.params);
    }
 
    
    public static GeneralizedIdentifier identifier(String name) {   return new GeneralizedIdentifier(name, List.of()); }
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof GeneralizedIdentifier)) return false;
        GeneralizedIdentifier t=(GeneralizedIdentifier)other;
        return (name.equals(t.name) && params.equals(t.params));
    }
    @Override
    public String toString() {
        List<String> paramStrings= params.stream().map((s)->s.toString()).collect(Collectors.toList());
        return name+(paramStrings.isEmpty()?"":"#("+String.join(",", paramStrings)+")");
    }
    @Override public int hashCode() {
        return name.hashCode()+params.hashCode();
    }
    
    /**
     * 
     * @return the sacped identifier
     */
    public String toStringEscapeParametric() {
        List<String> paramStrings= params.stream().map((s)->s.toStringEscapeParametric()).collect(Collectors.toList());
        return name+(paramStrings.isEmpty()?"":"_"+String.join("_", paramStrings)+"_");
    }
    /**
     * return the proper Tyep string to report 
     * - if it is parametric that we define then escape
     * - else elaborate leave the outer most scope like that but mess with the PArameter instead
     * @param gidManager
     * @return
     */
    public String toProperTypeString(GeneralizedIdentifierManager gidManager) {
        if(gidManager.getParametric(name) !=null) {
            return toStringEscapeParametric(); 
        } else {
            List<String> paramStrings= params.stream().map((s)->s.toProperTypeString(gidManager)).collect(Collectors.toList());
            return name+(paramStrings.isEmpty()?"":"#("+String.join(",", paramStrings)+")");
        }
        
    }
}

/**
 * Represent parameter of 
 * @author boomza654
 *
 */
class Parameter{
    public final Integer number;
    public final GeneralizedIdentifier gid;
    
    /**
     * Check Representation
     * @throws AssertionError If type and number are both null or both not null
     */
    private void checkRep() {
        assert (number==null)!= (gid==null) : "Invalid Parameter num="+number+" Generalized Id="+gid ;
    }
    public Parameter(Integer number, GeneralizedIdentifier gid) {
        this.number=number;
        this.gid=gid;
        checkRep();
    }

    
    @Override public String toString() {
        if(number!=null)
            return number.toString();
        else
            return gid.toString();
    }
    @Override public int hashCode() {
        if(number!=null) return number;
        if(gid!=null) return gid.hashCode();
        throw new RuntimeException("Not num and not Generalized id?!?!?!?!");
    }
    @Override public boolean equals(Object other) {
        if(!(other instanceof Parameter)) return false;
        Parameter that = (Parameter) other;
        if((this.number!=null) && (that.number!=null)) {
            return this.number.equals(that.number);
        }
        else if ((this.gid!=null) && (that.gid!=null)) {
            return this.gid.equals(that.gid);
        } else {
            return false;
        }
    }
    /**
     * change parametrically solid gid to a new gid
     * @return the escaped string
     */
    public String toStringEscapeParametric() {
        if(number!=null)
            return number.toString();
        else
            return gid.toStringEscapeParametric();
    }
    
    /**
     * @see GeneralizedIdentifier#toProperTypeString(GeneralizedIdentifierManager)
     * @param gidManager
     * @return
     */
    public String toProperTypeString(GeneralizedIdentifierManager gidManager) {
        if(number!=null)
            return number.toString();
        else
            return gid.toProperTypeString(gidManager);
    }
}
