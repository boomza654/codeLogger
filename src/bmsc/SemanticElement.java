package bmsc;

import java.util.*;
import java.util.stream.Collectors;


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
    public static Variable IntegerVar(String name) {return new Variable(INTEGER_TYPE, name);}
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
    public final Object definition; // most likely going to be the ParseTree node that define the whole thing
    
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
        return "<Type "+this.typeId.toString()+">";
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
 * 
 * Immutable Represent a type of a solid function/ method in minispec
 * - map from value Type to value Type
 * @author boomza654
 *
 */
class FuncType {
    public final Type returnType;
    public final List<Type> argTypes;
    public FuncType(Type returnType, List<Type> argTypes) {
        this.returnType=returnType;
        this.argTypes=List.copyOf(argTypes);
    }
    public FuncType(FuncType other) {
        this.returnType=other.returnType;
        this.argTypes=List.copyOf(other.argTypes);
    }
    @Override 
    public String toString() {
        List<String> argTypeStrings= argTypes.stream().map((s)->s.toString()).collect(Collectors.toList());
        return returnType.toString()+"("+String.join(",", argTypeStrings)+")";
    }
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof FuncType)) return false;
        FuncType that = (FuncType) other;
        return this.returnType.equals(that.returnType) && this.argTypes.equals(that.argTypes);
    }
    @Override
    public int hashCode() {
        return this.returnType.hashCode()+this.argTypes.hashCode();
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
    public final Type type;
    public final String name;
    public Object value = null;
    public Variable(Type type, String name) {
        this.type=type;
        this.name=name;
    }
    public Variable(Variable other) {
        this.type=other.type;
        this.name=other.name;
    }
    @Override 
    public String toString() {
        return "<Var "+type.toString()+" "+name+" = "+value.toString()+" >";
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
    public final FuncType type;
    public final GeneralizedIdentifier funcId;
    public final Object code;
    public Func(FuncType type, GeneralizedIdentifier funcId, Object code) {
        this.type=type;
        this.funcId=funcId;
        this.code=code;
    }
    public Func(Func other) {
        this.type=other.type;
        this.funcId=other.funcId;
        this.code=other.code;
    }
    @Override 
    public String toString() {
        return "<Func "+(type!=null?type.toString():"null")+" "+funcId.toString()+" >";
    }
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Func)) return false;
        Func that = (Func) other;
        return this.type.equals(that.type) && this.funcId.equals(that.funcId);
    }
    @Override
    public int hashCode() {
        return this.type.hashCode()+this.funcId.hashCode();
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
    public final Object definition;
    
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
        return "<Parametric "+name.toString()+">";
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
