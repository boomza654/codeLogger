package bmsc;

import java.util.*;
import static bmsc.GeneralizedIdentifier.identifier;
import stacktree.*;
import static stacktree.StackTree.StackTreeNode;

/**
 * Class for managing Scope/context/value/state of each variable/ type/ identifier and stuff & its immutability & stuff
 * 
 * This is similar to IntegerContext Class in the original translate.cpp 
 * 
 * @author boomza654
 *
 */
public class GeneralizedIdentifierManager {
    

    
    /**
     * tree of context / scope
     */
    public final StackTree<Scope> scopeTree;
    // at first plan to re write Bluespec type checking system as well, but figure out it is too much to do in 2 weeks
    public final Map<GeneralizedIdentifier,Func> funcMap;
    public final Map<GeneralizedIdentifier,Type> typeMap;
    public final Map<String, Parametric> parametricMap;
    public GeneralizedIdentifierManager() {
        scopeTree=new StackTree<>();
        funcMap= new HashMap<>();
        typeMap= new HashMap<>();
        parametricMap= new HashMap<>();
        //setupPrimitives();
        enterImmutableScope();
    }
    
    /**
     * Helper for assigning bluespec/primitive Types/fuc
     */
    private void addPrimitiveType(String s) {
        //curGidMap.put(identifier(s), new Type(identifier(s), null));
    }
    private void addPrimitiveParametric( String s) {
        //curGidMap.put(identifier(s), new Parametric(s, null));
    }
    private void addPrimitiveFunc( String s) {
        //curGidMap.put(identifier(s), new Func(null, identifier(s), null));
    }

    /**
     * Set up Bluespec / Primitive Semantic Element
     */
    private void setupPrimitives() {
        /* All These Primitive Type needs different kind of treatment on its own*/
        // Raw Type
        // TODO: support real Bluespec type checking (Might actually do it with different project since this is too much for a single project)
        addPrimitiveType("Bool");// need support this
        addPrimitiveType("Integer");// need support this
        addPrimitiveType("String");// need support this
        // Parametric Type
        addPrimitiveParametric("Bit");// need support this
        addPrimitiveParametric("Maybe");// need support this
        addPrimitiveParametric("Tuple2");
        addPrimitiveParametric("Tuple3");
        addPrimitiveParametric("Reg");// need support this
        addPrimitiveParametric("RegU");// need support this
        addPrimitiveParametric("Wire");
        addPrimitiveParametric("BypassWire");
        addPrimitiveParametric("DWire");
        addPrimitiveParametric("Vector");// need support this
        // function
        // to Bit Conversion 
        addPrimitiveFunc("pack");// need support this
        addPrimitiveFunc("unpack");// need support this
        
        
        // function of Bit#(n) but does not require parametric use since these are bluespec syntax

        addPrimitiveFunc("zeroExtend");// need support this
        addPrimitiveFunc("signExtend");// need support this
        addPrimitiveFunc("truncate");// need support this
        addPrimitiveFunc("parity");// need support this
        addPrimitiveFunc("reverseBits");// need support this
        addPrimitiveFunc("truncateLSB");// need support this
        
        // Bool comparison function

        addPrimitiveFunc("signedLT");// need support this
        addPrimitiveFunc("signedGE");// need support this
        //Maybe Type Tagged Union

        addPrimitiveFunc("Valid");// need support this
        addPrimitiveFunc("Invalid");// need support this
        addPrimitiveFunc("isValid");// need support this
        addPrimitiveFunc("fromMaybe");// need support this

        //Tuple related

        addPrimitiveFunc("tuple2");
        addPrimitiveFunc("tuple3");
        addPrimitiveFunc("tpl_1");
        addPrimitiveFunc("tpl_2");
        addPrimitiveFunc("tpl_3");
        
        //Vector / List function

        addPrimitiveFunc("newVector");
        addPrimitiveFunc("genVector");
        addPrimitiveFunc("replicate");
        addPrimitiveFunc("cons");
        addPrimitiveFunc("nil");
        addPrimitiveFunc("concat");
        addPrimitiveFunc("append");
        addPrimitiveFunc("select");
        addPrimitiveFunc("update");
        addPrimitiveFunc("head");
        addPrimitiveFunc("last");
        addPrimitiveFunc("tail");
        addPrimitiveFunc("init");
        addPrimitiveFunc("take");
        addPrimitiveFunc("takeTail");
        addPrimitiveFunc("drop");
        addPrimitiveFunc("takeAt");
        addPrimitiveFunc("rotate");
        addPrimitiveFunc("rotateR");
        addPrimitiveFunc("rotateBy");
        addPrimitiveFunc("shiftInAt0");
        addPrimitiveFunc("shiftInAtN");
        addPrimitiveFunc("shiftOutFrom0");
        addPrimitiveFunc("shiftOutFromN");
        addPrimitiveFunc("reverse");
        addPrimitiveFunc("transpose");
        addPrimitiveFunc("transposeLN");
        addPrimitiveFunc("elem");
        addPrimitiveFunc("any");
        addPrimitiveFunc("all");
        addPrimitiveFunc("or");
        addPrimitiveFunc("and");
        // Combining vector into vector of tuple

        addPrimitiveFunc("zip");
        addPrimitiveFunc("zip3");
        addPrimitiveFunc("unzip");
        
        // display and multip
        addPrimitiveFunc("$display");
        addPrimitiveFunc("$write");
        addPrimitiveFunc("$finish");
        addPrimitiveFunc("fshow");
        
    }

    
    public void enterImmutableScope() {
        Scope newContext = Scope.immutableScope();
        scopeTree.push(newContext);
    }
    public void enterMutableScope() {
        Scope newContext = Scope.MutableContext();
        scopeTree.push(newContext);
    }
    public void enterMethodScope() {
        Scope newContext = Scope.MethodContext();
        scopeTree.push(newContext);
    }
    public void enterBranchScope() {
        Scope newContext = Scope.FlowSensitiveContext();
        scopeTree.push(newContext);
    }
    /**
     * Clean up the branching Scope (currently on the parent node that branches)
     * 
     */
    public void cleanUpBranchScopes() {
        Map<String,Variable> curVarMap = getCurrentVarMap();
        for(String varName: curVarMap.keySet()) {
            if(curVarMap.get(varName).type.equals(SemanticElement.INTEGER_TYPE)) {
                // Check for poisoning of Integer type
                for(StackTreeNode<Scope> childScope: scopeTree.curNode.children) {
                    assert childScope.data.isFlowSensitive: "Error the children of current node is not a branch";
                    if(childScope.data.variableMap.containsKey(varName)) {
                        throw new RuntimeException ("Found Posisoned Integer");
                    }
                }
            }
        }
        // Clean up
        for(int i=scopeTree.getChildrenCount()-1;i>=0;i--) {
            scopeTree.traverseDown(i);
            scopeTree.pop();
        }
        
    }
    /**
     * Exit the Scope if the durrent scope is not a branch scope or outer most scope
     * - if not a brnach scope then also delete context
     * - else maintain the context and just move up
     */
    public void exitScope() {
        assert scopeTree.curNode!=scopeTree.root: "Error context tree reach root";
        if(!scopeTree.get().isFlowSensitive)
            scopeTree.pop();
        else
            scopeTree.traverseUp();
    }
    
    /**
     * Try defining the Variable in the current Scope
     * @param varName the Generalized Identifier to be defined
     * @param e the SemantocElement of it
     * @return true if definition is successful false otherwise 
     */
    public boolean defineVar(String varName, Variable e) {
        if(getVar(varName)!=null) System.out.println("Warning: "+ varName + " Getting shadowed redefined as "+e);
        Map<String,Variable> curGidMap = getCurrentVarMap();
        if(curGidMap.containsKey(varName)) {System.out.println("Error the "+varName+" is already defined"); return false;}
        curGidMap.put(varName,e);
        return true;
    }
    /**
     * Find the variable
     * @param varName the generalized identifier of the element to find
     * @return the pointer to Semantic Element (directly change it will of course affect the thing) or nul if not found
     *          Please avoid changing the value directly
     */
    public Variable getVar(String varName) {
        StackTreeNode<Scope> savedPointer = scopeTree.curNode;
        Variable result = null;
        while(scopeTree.curNode!=scopeTree.root) {
            Map<String,Variable> curGidMap = getCurrentVarMap();
            if(curGidMap.containsKey(varName)){
                result=curGidMap.get(varName);
                break;
            }
            scopeTree.traverseUp();
        }
        // restire curNode
        scopeTree.curNode=savedPointer;
        return result;
    }
    
    /**
     * attempt to set Variable from current scope
     * @param varName the varaible name
     * @param value to set
     * @return whether the setting is successful or not
     */
    public boolean setVar(String varName, Object value) {

        StackTreeNode<Scope> savedPointer = scopeTree.curNode;
        Scope flowSensitiveScope=null;
        Variable found=null;
        while(scopeTree.curNode!=scopeTree.root) {
            Scope curScope = scopeTree.get();
            if(scopeTree.curNode!=savedPointer && !curScope.isMutableFromChildren) {
                // if reach the scope that is immutable but not current scope
                
                break;
            }
            Map<String,Variable> curGidMap = getCurrentVarMap();
            if(curGidMap.containsKey(varName)){
                found=curGidMap.get(varName);
                break;
            }
            if(flowSensitiveScope==null && curScope.isFlowSensitive)
                flowSensitiveScope=curScope;
            scopeTree.traverseUp();
        }
        scopeTree.curNode=savedPointer;
        if(found!=null) {
            if(flowSensitiveScope!=null) {
                // Poisoned set 
                assert !flowSensitiveScope.variableMap.containsKey(varName): "Error assertion flowsensitive scope actually has varr";
                assert found.name.equals(varName): " found differnet var name";
                Variable toSet =new Variable(found.type,varName);
                toSet.value=value;
                flowSensitiveScope.variableMap.put(varName,toSet);
            }else {
                // normal set
                found.value=value;
            }
        }

        return found!=null;
        
    }

    /**
     * Try defining the Function
     * @param funcGid the Generalized Identifier to be defined
     * @param e the SemantocElement of it
     * @return true if definition is successful false otherwise 
     */
    public boolean defineFunc(GeneralizedIdentifier funcGid, Func e) {
        if(funcMap.containsKey(funcGid)) {System.out.println("Error the "+funcGid+" is already defined"); return false;}
        funcMap.put(funcGid,e);
        return true;
    }
    /**
     * @param funcGid
     * @return Function object ( that can be manipulated drectly)or nul if not found
     */
    public Func getFunc(GeneralizedIdentifier funcGid) { return funcMap.getOrDefault(funcGid, null);}
    /**
     * Try defining the type
     * @param typeGid the Generalized Identifier to be defined
     * @param e the SemantocElement of it
     * @return true if definition is successful false otherwise 
     */
    public boolean defineType(GeneralizedIdentifier typeGid, Type e) {
        if(typeMap.containsKey(typeGid)) {System.out.println("Error the type "+typeGid+" is already defined"); return false;}
        typeMap.put(typeGid,e);
        return true;
    }
    /**
     * @param typeGid
     * @return Type object or nul if not found
     */
    public Type getType(GeneralizedIdentifier typeGid) { return typeMap.getOrDefault(typeGid, null);}
    
    /**
     * Try defining the Parametric defintion
     * @param name the paramteric string
     * @param e the SemanticElement of it
     * @return true if definition is successful false otherwise 
     */
    public boolean defineParametric(String name, Parametric e) {
        if(parametricMap.containsKey(name)) {System.out.println("Error the parametric "+name+" is already defined"); return false;}
        parametricMap.put(name,e);
        return true;
    }
    /**
     * 
     * @param name
     * @return Parametric object or nul if not found
     */
    public Parametric getParametric(String name) { return parametricMap.getOrDefault(name, null);}
    /**
     * 
     * @return the GidentifierMap of where the curNode is
     */
    private Map<String,Variable> getCurrentVarMap(){
        Scope curContext = scopeTree.get();
        assert curContext!=null : "Error curContext is null";
        Map<String,Variable> curGidMap = curContext.variableMap;
        return curGidMap;
    }
//
//    public boolean setElement(String gid, Variable e) {}

    
    @Override
    public String toString() {
        return this.scopeTree.toString();
    }
    
    public static void main(String[] args) {
        GeneralizedIdentifierManager manager = new GeneralizedIdentifierManager();
        System.out.println(manager);
    }
    
}



/**
 * Define the Scope (Set of Integers/Variable defined in each scope) + what th scope is
 * @author boomza654
 *
 */
 class Scope{
    public final Map<String,Variable> variableMap;
    // mark that the context contains variable that can be set from children scope
    public final boolean isMutableFromChildren;
    // mark that current context is Flow sensitive context , cannot be exited lightly (need to have proper merged)
    public final boolean isFlowSensitive; 
    // Mark that current context is a method (Just disallow register write and sub module wire in )
    public final boolean isMethod;
    
    public static Scope immutableScope() {return new Scope(false, false, false);}
    public static Scope MutableContext() {return new Scope(true, false, false);}
    public static Scope MethodContext() {return new Scope(true, false, true);}
    public static Scope FlowSensitiveContext() {return new Scope(true, true, true);}
    
    public Scope(boolean isMutableFromChildren, boolean isFlowSensitive, boolean isMethod) {
        this.isMutableFromChildren=isMutableFromChildren;
        this.isFlowSensitive=isFlowSensitive;
        this.isMethod=isMethod;
        this.variableMap=new HashMap<>();
    }
    
    @Override 
    public String toString() {
        String mapOut="";
        for(String id: variableMap.keySet()) {
            mapOut+= "    "+String.format("%-10s", id.toString())+":"+variableMap.get(id).toString()+"\n";
        }
        return "< Scope\n"
                + "  isMutableFromChildren:"+isMutableFromChildren+" \n"
                + "  isFlowSensitive      :"+isFlowSensitive+" \n"
                + "  isMethod             :"+isMethod+" \n"
                + "  identifierMap        :\n"
                + mapOut
                +">\n";
    }
    
}
