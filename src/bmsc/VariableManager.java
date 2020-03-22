package bmsc;

import java.util.*;

/**
 * Class for managing Scope/context/value/state of each  variable & its immutability & stuff
 * 
 * This is similar to IntegerContext Class in the original translate.cpp
 * @author boomza654
 *
 */
public class VariableManager {
    
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
    };
    
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
    public static class VariableContext{
        public final Map<String,Variable> variablesMap;
        public final boolean isMutableFromChildren;
        public final boolean isFlowSensitive;
        public final boolean isMethod;
        
        public VariableContext(boolean isMutableFromChildren, boolean isFlowSensitive, boolean isMethod) {
            this.isMutableFromChildren=isMutableFromChildren;
            this.isFlowSensitive=isFlowSensitive;
            this.isMethod=isMethod;
            this.variablesMap=new HashMap<>();
        }
        
        @Override 
        public String toString() {
            return "< VariableContext\n"
                    + " isMutableFromChildren:"+isMutableFromChildren+" \n"
                    + " isFlowSensitive      :"+isFlowSensitive+" \n"
                    + " isMethod             :"+isMethod+" \n"
                    + " variablesMap         :"+ variablesMap.toString()+">\n";
        }
        
    }
    
    /**
     * Level of context
     */
    public final List<VariableContext> contextLevels;
    public VariableManager() {
        this.contextLevels=new ArrayList<>();
        this.enterImmutableLevel();
    }
    
    /*
     * Scope Entering and Scope exiting  
     */
    
    public void enterImmutableLevel() {this.contextLevels.add(new VariableContext(false, false,false));}
    public void enterMutableLevel() {this.contextLevels.add(new VariableContext(true, false,false));}
    public void enterMethodLevel() {this.contextLevels.add(new VariableContext(true, false, true));}
    /**
     * If/Case scope that is sensitive to flow
     */
    public void enterFlowSensitiveLevel() {this.contextLevels.add(new VariableContext(true, true, false));}
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
            Map<String,Variable> currentVaraiblesMap = this.contextLevels.get(i).variablesMap;
            if(currentVaraiblesMap.containsKey(name))
                return currentVaraiblesMap.get(name);
        }
        return null;
    }
    /**
     * Define variable in the innermost scope
     * @param name the variable name
     * @param type the type of varaibe
     * @return if the definition is succeful (Can involve shadowing)
     */
    public boolean defineVar(String name, Type type) {
        Map<String,Variable> currentVaraiblesMap = this.contextLevels.get(this.contextLevels.size()-1).variablesMap;
        if(currentVaraiblesMap.containsKey(name)) return false;
        currentVaraiblesMap.put(name, new Variable(type,name));
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
        VariableContext currentVariableContext = this.contextLevels.get(i);
        Map<String,Variable> currentVariablesMap = currentVariableContext.variablesMap;
        if(currentVariablesMap.containsKey(name)) {
            currentVariablesMap.get(name).value=value;
            return true;
        }
        // Search Int Var from current scope Up
        Variable toMutate=null;
        VariableContext toStorePoisonedVar = null; 
        /*
         * When Integer from outside is mutated in the flow sensitive scope
         * - the value outside gets "Posioned"
         * - the value is still valid inside the innermost poisoning scope 
         */
        for(i=this.contextLevels.size()-2;i>=0;i--) {
            currentVariableContext = this.contextLevels.get(i);
            // check mutability
            if(!currentVariableContext.isMutableFromChildren) break;
            // Find variable that is integer
            currentVariablesMap = currentVariableContext.variablesMap;
            if(currentVariablesMap.containsKey(name)) {
                if(currentVariablesMap.get(name).type.equals(SemanticElement.INTEGER_TYPE))
                    toMutate=currentVariablesMap.get(name);
                break;
            }
            // Set poisoning if go out of the flow sensitive scope
            if(currentVariableContext.isFlowSensitive && toStorePoisonedVar==null) {
                toStorePoisonedVar=currentVariableContext;
            }
        }
        // Mutation
        if(toMutate==null) return false;
        if(toStorePoisonedVar!=null) {
            assert !toStorePoisonedVar.variablesMap.containsKey(name): "poisoned leel also have the Integer ?A?A?";
            toMutate.value=new IntegerData(0, IntegerState.POSIONED);
            toMutate= new Variable(SemanticElement.INTEGER_TYPE, toMutate.name);
            toStorePoisonedVar.variablesMap.put(name, toMutate);
        }
        toMutate.value= new IntegerData(value, IntegerState.VALID);
        return true;
        
    }
    
    @Override
    public String toString() {
        return this.contextLevels.toString();
    }
    
}
