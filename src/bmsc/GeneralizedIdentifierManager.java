package bmsc;

import java.util.*;
import static bmsc.GeneralizedIdentifier.identifier;

/**
 * Class for managing Scope/context/value/state of each  variable & its immutability & stuff
 * 
 * This is similar to IntegerContext Class in the original translate.cpp
 * @author boomza654
 *
 */
public class GeneralizedIdentifierManager {
    
    /**
     * 
     * State of modifiability of Integer
     * @author boomza654
     *
     */
    public static enum IntegerState{
      // Uninitialized is handled by null
      VALID, // Initilized
      POSIONED, // Get reassigned from the scope inside (in a flow sensitive context)
    }
    
    /**
     * 
     * Data and State of Integer variable
     * @author boomza654
     *
     */
    public static class IntegerData{
        public int value;
        public IntegerState state;
        public IntegerData(int value,IntegerState state) {
            this.value=value;
            this.state=state;
        }
    }
    
    /**
     * Define the Context (Set of Integers/Variable defined in each scope) + what th scope is
     * @author boomza654
     *
     */
    public static class GeneralizedIdentifierContext{
        public final Map<GeneralizedIdentifier,SemanticElement> identifierMap;
        public final boolean isMutableFromChildren;
        public final boolean isFlowSensitive;
        public final boolean isMethod;
        
        public GeneralizedIdentifierContext(boolean isMutableFromChildren, boolean isFlowSensitive, boolean isMethod) {
            this.isMutableFromChildren=isMutableFromChildren;
            this.isFlowSensitive=isFlowSensitive;
            this.isMethod=isMethod;
            this.identifierMap=new HashMap<>();
        }
        
        @Override 
        public String toString() {
            return "< VariableContext\n"
                    + " isMutableFromChildren:"+isMutableFromChildren+" \n"
                    + " isFlowSensitive      :"+isFlowSensitive+" \n"
                    + " isMethod             :"+isMethod+" \n"
                    + " identifierMap         :"+ identifierMap.toString()+">\n";
        }
        
    }
    
    /**
     * Level of context
     */
    public final List<GeneralizedIdentifierContext> contextLevels;
    public GeneralizedIdentifierManager() {
        this.contextLevels=new ArrayList<>();
        this.enterImmutableLevel();
    }
    
    /*
     * Scope Entering and Scope exiting  
     */
    
    public void enterImmutableLevel() {this.contextLevels.add(new GeneralizedIdentifierContext(false, false,false));}
    public void enterMutableLevel() {this.contextLevels.add(new GeneralizedIdentifierContext(true, false,false));}
    public void enterMethodLevel() {this.contextLevels.add(new GeneralizedIdentifierContext(true, false, true));}
    /**
     * If/Case scope that is sensitive to flow
     */
    public void enterFlowSensitiveLevel() {this.contextLevels.add(new GeneralizedIdentifierContext(true, true, false));}
    public void exitLevel() {
        assert (this.contextLevels.size()>1):"Gone out of outer most scope";
        this.contextLevels.remove(this.contextLevels.size()-1);
    }
    
    /**
     * Get the variable startgin from the innermost context
     * @param name of the variable to find
     * @return the Variable object (or null if nothing is found)
     */
    public Variable getVar(String name) {
        for(int i=this.contextLevels.size()-1;i>=0;i--) {
            Map<GeneralizedIdentifier,SemanticElement> curIdMap = this.contextLevels.get(i).identifierMap;
            if(curIdMap.containsKey(identifier(name)))
                return (Variable)curIdMap.get(identifier(name));
        }
        return null;
    }
    /**
     * Define variable in the innermost scope
     * @param name the variable name
     * @param type the type of varaibe
     * @return if the definition is succeful (Can involve shadowing)
     */
    public boolean defineVar(Type type,String name) {
        Map<GeneralizedIdentifier,SemanticElement> curIdMap = this.contextLevels.get(this.contextLevels.size()-1).identifierMap;
        if(curIdMap.containsKey(identifier(name))) return false;
        curIdMap.put(identifier(name), new Variable(type,name));
        return true;
    }
    
    
    
    /**
     * Set Varaible(non Integer)'s value from within the inner most scope
     * @param name varaible name to set
     * @param value Value to set
     * @return whether the setting is successful or not
     */
    public boolean setVar(String name, Object value) {
        Variable var = getVar(name);
        if(var==null || var.type.equals(SemanticElement.INTEGER_TYPE))
            return false;
        var.value=value;
        return true;
    }
    /**
     * Set Varaible(Integer)'s value from within the inner most scope
     * @param name varaible name to set
     * @param value Value to set
     * @return whether the setting is successful or not
     */
    public boolean setVarInteger(String name, int value) {
        //Search in current Scope first : Always mutable
        int i=this.contextLevels.size()-1;
        GeneralizedIdentifierContext curIdContext = this.contextLevels.get(i);
        Map<GeneralizedIdentifier,SemanticElement> curIdMap = curIdContext.identifierMap;
        if(curIdMap.containsKey(identifier(name))) {
            ((Variable)curIdMap.get(identifier(name))).value=value;
            return true;
        }
        // Search Int Var from current scope Up
        Variable toMutate=null;
        GeneralizedIdentifierContext toStorePoisonedVar = null; 
        /*
         * When Integer from outside is mutated in the flow sensitive scope
         * - the value outside gets "Posioned"
         * - the value is still valid inside the innermost poisoning scope 
         */
        for(i=this.contextLevels.size()-2;i>=0;i--) {
            curIdContext = this.contextLevels.get(i);
            // check mutability
            if(!curIdContext.isMutableFromChildren) break;
            // Find variable that is integer
            curIdMap = curIdContext.identifierMap;
            if(curIdMap.containsKey(identifier(name))) {
                if(((Variable)curIdMap.get(identifier(name))).type.equals(SemanticElement.INTEGER_TYPE))
                    toMutate=(Variable)curIdMap.get(identifier(name));
                break;
            }
            // Set poisoning if go out of the flow sensitive scope
            if(curIdContext.isFlowSensitive && toStorePoisonedVar==null) {
                toStorePoisonedVar=curIdContext;
            }
        }
        // Mutation
        if(toMutate==null) return false;
        if(toStorePoisonedVar!=null) {
            assert !toStorePoisonedVar.identifierMap.containsKey(identifier(name)): "poisoned leel also have the Integer ?A?A?";
            toMutate.value=new IntegerData(0, IntegerState.POSIONED);
            toMutate= new Variable(SemanticElement.INTEGER_TYPE, toMutate.name);
            toStorePoisonedVar.identifierMap.put(identifier(name), toMutate);
        }
        toMutate.value= new IntegerData(value, IntegerState.VALID);
        return true;
        
    }
    
    @Override
    public String toString() {
        return this.contextLevels.toString();
    }
    
}
