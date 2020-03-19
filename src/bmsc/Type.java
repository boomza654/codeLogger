package bmsc;

import java.util.*;
/**
 * Immutable
 * Representing Minispec Solid Type
 * 
 * Similar to ParametricUse class in original translate.cpp
 * @author boomza654
 *
 */
public class Type {
    public final String name;
    public final boolean escape;
    public final List<Parameter> params; // Object can be Integer or Type
    
    
    public Type(String name, boolean escape, List<Parameter> params) {
        this.name=name;
        this.escape=escape;
        this.params= List.copyOf(params);
    }
    
    /**
     * 
     * @return Type object that represnets Integer type
     */
    public static Type IntegerType() {return new Type("Integer",false,List.of());}
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Type)) return false;
        Type t=(Type)other;
        return (name.equals(t.name) && params.equals(t.params));
    }
    @Override
    public String toString() {
        String out= (escape?"\\":"")+name+"#(";
        for(Parameter param:params) {
            out+=param.toString();
        }
        out+=")";
        return out;
    }
    @Override public int hashCode() {
        return name.hashCode();
    }
}
