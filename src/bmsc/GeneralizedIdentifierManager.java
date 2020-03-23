package bmsc;

import java.util.*;
import static bmsc.GeneralizedIdentifier.identifier;
import stacktree.*;
import static stacktree.StackTree.StackTreeNode;

/**
 * Class for managing Scope/context/value/state of each variable/ type/ identifier and stuff & its immutability & stuff
 * 
 * This is similar to IntegerContext Class in the original translate.cpp but is built on totally different concept
 * 
 * @author boomza654
 *
 */
public class GeneralizedIdentifierManager {
    

    
    /**
     * tree of context / scope
     */
    public final StackTree<GeneralizedIdentifierContext> contextTree;
    public GeneralizedIdentifierManager() {
        contextTree=new StackTree<>();
        enterPrimitiveScope();
        enterImmutableScope();
    }
    
    /**
     * Helper for assigning bluespec/primitive Types/fuc
     */
    private static void addPrimitiveType(Map<GeneralizedIdentifier,SemanticElement> curGidMap, String s) {
        curGidMap.put(identifier(s), new Type(identifier(s), null));
    }
    private static void addPrimitiveParametric(Map<GeneralizedIdentifier,SemanticElement> curGidMap, String s) {
        curGidMap.put(identifier(s), new Parametric(s, null));
    }
    private static void addPrimitiveFunc(Map<GeneralizedIdentifier,SemanticElement> curGidMap, String s) {
        curGidMap.put(identifier(s), new Func(null, identifier(s), null));
    }

    /**
     * Set up Bluespec / Primitive Semantic Element
     */
    private void enterPrimitiveScope() {
        // Primitive Type
        GeneralizedIdentifierContext newContext = GeneralizedIdentifierContext.ImmutableContext();
        Map<GeneralizedIdentifier,SemanticElement> curGidMap = newContext.identifierMap;
        
        /* All These Primitive Type needs different kind of treatment on its own*/
        // Raw Type

        addPrimitiveType(curGidMap,"Bool");// need support this
        addPrimitiveType(curGidMap,"Integer");// need support this
        addPrimitiveType(curGidMap,"String");// need support this
        // Parametric Type
        addPrimitiveParametric(curGidMap,"Bit");// need support this
        addPrimitiveParametric(curGidMap,"Maybe");// need support this
        addPrimitiveParametric(curGidMap,"Tuple2");
        addPrimitiveParametric(curGidMap,"Tuple3");
        addPrimitiveParametric(curGidMap,"Reg");// need support this
        addPrimitiveParametric(curGidMap,"RegU");// need support this
        addPrimitiveParametric(curGidMap,"Wire");
        addPrimitiveParametric(curGidMap,"BypassWire");
        addPrimitiveParametric(curGidMap,"DWire");
        addPrimitiveParametric(curGidMap,"Vector");// need support this
        // function
        // to Bit Conversion 
        addPrimitiveFunc(curGidMap,"pack");// need support this
        addPrimitiveFunc(curGidMap,"unpack");// need support this
        
        
        // function of Bit#(n) but does not require parametric use since these are bluespec syntax

        addPrimitiveFunc(curGidMap,"zeroExtend");// need support this
        addPrimitiveFunc(curGidMap,"signExtend");// need support this
        addPrimitiveFunc(curGidMap,"truncate");// need support this
        addPrimitiveFunc(curGidMap,"parity");// need support this
        addPrimitiveFunc(curGidMap,"reverseBits");// need support this
        addPrimitiveFunc(curGidMap,"truncateLSB");// need support this
        
        // Bool comparison function

        addPrimitiveFunc(curGidMap,"signedLT");// need support this
        addPrimitiveFunc(curGidMap,"signedGE");// need support this
        //Maybe Type Tagged Union

        addPrimitiveFunc(curGidMap,"Valid");// need support this
        addPrimitiveFunc(curGidMap,"Invalid");// need support this
        addPrimitiveFunc(curGidMap,"isValid");// need support this
        addPrimitiveFunc(curGidMap,"fromMaybe");// need support this

        //Tuple related

        addPrimitiveFunc(curGidMap,"tuple2");
        addPrimitiveFunc(curGidMap,"tuple3");
        addPrimitiveFunc(curGidMap,"tpl_1");
        addPrimitiveFunc(curGidMap,"tpl_2");
        addPrimitiveFunc(curGidMap,"tpl_3");
        
        //Vector / List function

        addPrimitiveFunc(curGidMap,"newVector");
        addPrimitiveFunc(curGidMap,"genVector");
        addPrimitiveFunc(curGidMap,"replicate");
        addPrimitiveFunc(curGidMap,"cons");
        addPrimitiveFunc(curGidMap,"nil");
        addPrimitiveFunc(curGidMap,"concat");
        addPrimitiveFunc(curGidMap,"append");
        addPrimitiveFunc(curGidMap,"select");
        addPrimitiveFunc(curGidMap,"update");
        addPrimitiveFunc(curGidMap,"head");
        addPrimitiveFunc(curGidMap,"last");
        addPrimitiveFunc(curGidMap,"tail");
        addPrimitiveFunc(curGidMap,"init");
        addPrimitiveFunc(curGidMap,"take");
        addPrimitiveFunc(curGidMap,"takeTail");
        addPrimitiveFunc(curGidMap,"drop");
        addPrimitiveFunc(curGidMap,"takeAt");
        addPrimitiveFunc(curGidMap,"rotate");
        addPrimitiveFunc(curGidMap,"rotateR");
        addPrimitiveFunc(curGidMap,"rotateBy");
        addPrimitiveFunc(curGidMap,"shiftInAt0");
        addPrimitiveFunc(curGidMap,"shiftInAtN");
        addPrimitiveFunc(curGidMap,"shiftOutFrom0");
        addPrimitiveFunc(curGidMap,"shiftOutFromN");
        addPrimitiveFunc(curGidMap,"reverse");
        addPrimitiveFunc(curGidMap,"transpose");
        addPrimitiveFunc(curGidMap,"transposeLN");
        addPrimitiveFunc(curGidMap,"elem");
        addPrimitiveFunc(curGidMap,"any");
        addPrimitiveFunc(curGidMap,"all");
        addPrimitiveFunc(curGidMap,"or");
        addPrimitiveFunc(curGidMap,"and");
        // Combining vector into vector of tuple

        addPrimitiveFunc(curGidMap,"zip");
        addPrimitiveFunc(curGidMap,"zip3");
        addPrimitiveFunc(curGidMap,"unzip");
        
        // display and multip
        addPrimitiveFunc(curGidMap,"$display");
        addPrimitiveFunc(curGidMap,"$write");
        addPrimitiveFunc(curGidMap,"$finish");
        addPrimitiveFunc(curGidMap,"fshow");
        
        
        contextTree.push(newContext);
    }

    
    public void enterImmutableScope() {
        GeneralizedIdentifierContext newContext = GeneralizedIdentifierContext.ImmutableContext();
        contextTree.push(newContext);
    }
    public void enterMutableScope() {
        GeneralizedIdentifierContext newContext = GeneralizedIdentifierContext.MutableContext();
        contextTree.push(newContext);
    }
    public void enterMethodScope() {
        GeneralizedIdentifierContext newContext = GeneralizedIdentifierContext.MethodContext();
        contextTree.push(newContext);
    }
    public void enterBranchScope() {
        GeneralizedIdentifierContext newContext = GeneralizedIdentifierContext.FlowSensitiveContext();
        contextTree.push(newContext);
    }
    /**
     * Clean up the branching Scope
     */
    public void mergeBranchScopes() {
        
    }
    /**
     * Exit the Scope if the durrent scope is not a branch scope or outer most scope
     * - if not a brnach scope then also delete context
     * - else maintain the context and just move up
     */
    public void exitLevel() {
        assert contextTree.curNode!=contextTree.root: "Error context tree reach root";
        if(!contextTree.get().isFlowSensitive)
            contextTree.pop();
        else
            contextTree.traverseUp();
    }
    

    /**
     * Find the semantic element of gid 
     * @param gid the generalized identifier of the element to find
     * @return the pointer to Semantic Element (directly change it will of course affect the thing) or nul if not found
     *          Please avoid changing the value directly
     */
    public SemanticElement getElement(GeneralizedIdentifier gid) {
        StackTreeNode<GeneralizedIdentifierContext> savedPointer = contextTree.curNode;
        SemanticElement result = null;
        while(contextTree.curNode!=contextTree.root) {
            Map<GeneralizedIdentifier,SemanticElement> curGidMap = getCurrentGidMap();
            if(curGidMap.containsKey(gid)){
                result=curGidMap.get(gid);
                break;
            }
            contextTree.traverseUp();
        }
        // restire curNode
        contextTree.curNode=savedPointer;
        return result;
    }
    
    /**
     * Try defining the Semantic Element in the current Scope
     * @param gid the Generalized Identifier to be defined
     * @param e the SemantocElement of it
     * @return true if definition is successful false otherwise 
     */
    public boolean defineElement(GeneralizedIdentifier gid, SemanticElement e) {
        if(getElement(gid)==null) return false;
        Map<GeneralizedIdentifier,SemanticElement> curGidMap = getCurrentGidMap();
        curGidMap.put(gid,e);
        return true;
    }
    /**
     * 
     * @return the GidentifierMap of where the curNode is
     */
    private Map<GeneralizedIdentifier,SemanticElement> getCurrentGidMap(){
        GeneralizedIdentifierContext curContext = contextTree.get();
        assert curContext!=null : "Error curContext is null";
        Map<GeneralizedIdentifier,SemanticElement> curGidMap = curContext.identifierMap;
        return curGidMap;
    }
//
//    public boolean setElement(GeneralizedIdentifier gid, SemanticElement e) {}

    
    @Override
    public String toString() {
        return this.contextTree.toString();
    }
    
    public static void main(String[] args) {
        GeneralizedIdentifierManager manager = new GeneralizedIdentifierManager();
        System.out.println(manager);
    }
    
}



/**
 * Define the Context (Set of Integers/Variable defined in each scope) + what th scope is
 * @author boomza654
 *
 */
 class GeneralizedIdentifierContext{
    public final Map<GeneralizedIdentifier,SemanticElement> identifierMap;
    // mark that the context contains variable that can be set from children scope
    public final boolean isMutableFromChildren;
    // mark that current context is Flow sensitive context , cannot be exited lightly (need to have proper merged)
    public final boolean isFlowSensitive; 
    // Mark that current context is a method (Just disallow register write and sub module wire in )
    public final boolean isMethod;
    
    public static GeneralizedIdentifierContext ImmutableContext() {return new GeneralizedIdentifierContext(false, false, false);}
    public static GeneralizedIdentifierContext MutableContext() {return new GeneralizedIdentifierContext(true, false, false);}
    public static GeneralizedIdentifierContext MethodContext() {return new GeneralizedIdentifierContext(true, false, true);}
    public static GeneralizedIdentifierContext FlowSensitiveContext() {return new GeneralizedIdentifierContext(true, true, true);}
    
    public GeneralizedIdentifierContext(boolean isMutableFromChildren, boolean isFlowSensitive, boolean isMethod) {
        this.isMutableFromChildren=isMutableFromChildren;
        this.isFlowSensitive=isFlowSensitive;
        this.isMethod=isMethod;
        this.identifierMap=new HashMap<>();
    }
    
    @Override 
    public String toString() {
        String mapOut="";
        for(GeneralizedIdentifier id: identifierMap.keySet()) {
            mapOut+= "    "+id.toString()+":"+identifierMap.get(id).toString()+"\n";
        }
        return "< VariableContext\n"
                + "  isMutableFromChildren:"+isMutableFromChildren+" \n"
                + "  isFlowSensitive      :"+isFlowSensitive+" \n"
                + "  isMethod             :"+isMethod+" \n"
                + "  identifierMap        :\n"
                + mapOut
                +">\n";
    }
    
}
