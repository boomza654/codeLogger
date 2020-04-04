package bmsc;


/**
 * rerpesents Bluespec Boolean
 * with a proper toString
 * @author boomza654
 *
 */
public class BBoolean {
    public final boolean value;
    public BBoolean(boolean x) {value=x;}
    public static BBoolean bbool(boolean x) {return  new BBoolean(x); }
    public static BBoolean bfalse() {return bbool(false);}
    public static BBoolean btrue() {return bbool(true);}
    @Override public boolean equals(Object other) {
        if(!(other instanceof BBoolean)) return false;
        BBoolean that = (BBoolean) other;
        return that.value==this.value;
    }
    @Override public int hashCode() {
        return this.value?1:0;
    }
    @Override public String toString() {
        return value?"True":"False";
    }
    
}