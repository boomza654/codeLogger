package bmsc;

import java.util.*;


/**
 * Represneting the type of Semantic element (The information about each generalized identfier)<br/>
 * Mostly will represent the fact of (identifier represents x)<br/>
 * - Minspec Solid Type (Type) [store that this is a minispec type (Primitive, typedef, struct, enum, interface)] <br/>
 * - Minispec Functor (Func) [store that this is a minispec function / method]<br/>
 * - Minispec Variable [store that this is a minispec variable] <br/>
 * - Parametric  [ store that this is convertible to other Semantic Elements Later on]<br/>
 * @author boomza654
 *
 */
public interface SemanticElement {
   

    public static final Type INTEGER_TYPE = new Type(GeneralizedIdentifier.identifier("Integer"),null);
    public static Variable IntegerVar(String name) {return new Variable(GeneralizedIdentifier.identifier("Integer"), name);}
}

/**
 * Immutable
 * 
 * Representing Minispec Solid Type with its definition<br/>
 * 
 * Meaning: (The typeId identifier represents a type (i.e. format of interaction) whose definition is deducible from definition)
 * 
 * Similar to ParametricUse class in original translate.cpp
 * @author boomza654
 *
 */
class Type implements SemanticElement{
    public final GeneralizedIdentifier typeId;
    public Object definition; // most likely going to be the ParseTree node that define the whole thing
    public boolean toSynth=true; // this is for module Declaration  that it shoud synth by default
    public Type(GeneralizedIdentifier typeId, Object definition) {
        this.typeId=typeId;
        this.definition=definition;
    }
    public Type(Type other) {
        this.typeId=other.typeId;
        this.definition=other.definition;
    }
    @Override 
    public String toString() {
        return "<Type "+this.typeId.toString()+(toSynth?"":" no Synth")+">";
    }
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Type)) return false;
        Type that = (Type) other;
        return this.typeId.equals(that.typeId) ;
    }
    @Override
    public int hashCode() {
        return this.typeId.hashCode();
    }
    public String toStringEscapeParametric() {
        return this.typeId.toStringEscapeParametric();
    }

    
}





/**
 * Mutable
 * Represents a value/Varaible in Minispec <br/>
 * Meaning: The name identifier represents the variable whose types is type and has value of value.
 * @author boomza654
 * 
 *
 */
class Variable implements SemanticElement{
    public final GeneralizedIdentifier typeId;
    public final String name;
    public Object value = null;
    public boolean isModule = false; // set this to true to idicate declaring a varaible that is supposed to be sub module
    // useful when declared inside a module
    public Variable(GeneralizedIdentifier typeId, String name) {
        this.typeId=typeId;
        this.name=name;
    }
    public Variable(Variable other) {
        this.typeId=other.typeId;
        this.name=other.name;
    }
    @Override 
    public String toString() {
        return "<Var "+typeId.toString()+" "+name+" = "+(value!=null?value.toString():"null")+" >";
    }

    // no override of equals/ hashCode since the varaible need absolute equality
    
}

/**
 * Immutalbe
 * Represnets a whole function (argument + body) in Minispec<br/>
 * Meaning: The funcId identifier represents a function whose type is type and execution is decudible from code
 * @author boomza654
 *
 */
class Func implements SemanticElement {
    public final GeneralizedIdentifier funcId;
    public Object definition;
    public boolean toSynth=true; // this is for module Declaration  that it should no inline
    public Func( GeneralizedIdentifier funcId, Object definition) {
        this.funcId=funcId;
        this.definition=definition;
    }
    public Func(Func other) {
        this.funcId=other.funcId;
        this.definition=other.definition;
    }
    @Override 
    public String toString() {
        return "<Func "+funcId.toString()+(toSynth?"":" no Synth")+">";
    }
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Func)) return false;
        Func that = (Func) other;
        return  this.funcId.equals(that.funcId);
    }
    @Override
    public int hashCode() {
        return this.funcId.hashCode();
    }
}
/**
 * ImMutalbe
 * Represnets a whole function (argument + body) in Minispec<br/>
 * Meaning: The funcId identifier represents a function whose type is type and execution is decudible from code
 * @author boomza654
 *
 */
class Parametric implements SemanticElement {
    public final String name;
    public Object definition;
    public boolean toSynth=true; // this is for module Declaration  that it shoud synth by default
    // to synth has no effect if the type is actually type defs only
    public Parametric(String name, Object definition) {
        this.name =name;
        this.definition=definition;
    }
    public Parametric(Parametric other) {
        this.name=other.name;
        this.definition= other.definition;
    }
    @Override 
    public String toString() {
        return "<Parametric "+name.toString()+(toSynth?"":" no Synth")+">";
    }
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Parametric)) return false;
        Parametric that = (Parametric) other;
        return this.name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
