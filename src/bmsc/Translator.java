package bmsc;
import api.antlr4.*;
import static api.antlr4.MinispecParser.*;
import java.util.*;
import java.util.stream.Collectors;

import static bmsc.GeneralizedIdentifier.*;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Translate Types/Function/Code write into the codeBuffer
 * 
 * This is similar to a apart of elaborater in the original translate.cpp
 * @author boomza654
 *
 */
public class Translator  extends MinispecBaseVisitor<String>{
    public static String MinispecPrelude = 
            "/* Minispec prelude -- automatically prepended to every Minispec file */\n" + 
            "import Vector::*;  // In Minispec, Vector is a basic type\n" + 
            "\n" + 
            "// Minispec doesn't separate module and interface names, so use typedefs to\n" + 
            "// allow using some of the BSV Prelude modules with different interface names\n" + 
            "typedef Reg#(t) RegU#(type t);\n" + 
            "typedef Wire#(t) BypassWire#(type t);\n" + 
            "typedef Wire#(t) DWire#(type t);\n" + 
            "/* End of Minispec prelude */\n";
    public final GeneralizedIdentifierManager gidManager;
    public Translator(GeneralizedIdentifierManager gidManager) {
        this.gidManager=gidManager;
    }
    
    
    @Override public String visitTypeDefEnum(TypeDefEnumContext ctx) {
        String out = ("typedef enum { ");
        List<String> elements = ctx.typeDefEnumElement().stream().map((e)->e.getText()).collect(Collectors.toList());
        out+=String.join(", ", elements);
        out+=("} "+ctx.upperCaseIdentifier().getText()+" deriving(Bits, Eq, FShow);\n");
        return out;
    }
    @Override public String visitTypeDefStruct(TypeDefStructContext ctx) {
        //check if this is parametric matching or not

        return null;
        
    }
    /**
     * Start translating the registered type "gid" into the codeBuffer
     * @param gid of the type to translate
     * @return the code of that type definition
     */
    public String translateType(GeneralizedIdentifier gid) {
        if(gidManager.getType(gid)==null) {throw new RuntimeException("Error: No Type/Module name "+ gid); }
        if(gidManager.getType(gid).definition==null) {throw new RuntimeException("Error: Type/Module "+gid+" has no definition"); }
        if(!(gidManager.getType(gid).definition instanceof ParserRuleContext)) {throw new RuntimeException("Error: Type/Module "+gid+" has typedef"); }
        System.out.println("Start Translating Type: "+ gid);
        
        ParserRuleContext toTranslate=(ParserRuleContext)gidManager.getType(gid).definition; // you should not translate type of Minispec static synonym at all
        if(toTranslate instanceof TypeDefStructContext) {
            TypeDefStructContext ctx = (TypeDefStructContext) toTranslate;
            translateTypeDefStruct(gid, ctx);
            return "";
        } else {
            return visit(toTranslate);
        }
    }
    
    /**
     * try translate the STruct type def (take care of parameterization as well)
     * @param gid
     * @param ctx
     */
    public void translateTypeDefStruct(GeneralizedIdentifier gid,TypeDefStructContext ctx) {

    }
    /**
     * Will let gidManager define Var/ type according to the matched param=> paramformal
     * @param parameters List of Parameter being matched
     * @param paramFormals List of ParamFormal Nodes being matched
     */
    public void matchParameter(List<Parameter> parameters, List<ParamFormalContext> paramFormals ) {
        if(parameters.size()!=paramFormals.size()) {throw new RuntimeException("not same numbers of parameter are found");}
        for(int i=0;i< parameters.size();i++) {
            Parameter p = parameters.get(i);
            ParamFormalContext pf = paramFormals.get(i);
            if(pf.param()!=null) {throw new RuntimeException("Partial Parametrization is not allowed");}
            boolean pIsNumber = p.number!=null;
            boolean pfIsNumber = pf.typeName==null;
            assert pIsNumber==pfIsNumber: "Error parameter has different type";
            
            if(pIsNumber) {
                // number
                String varName=(pf.intName.getText());
                Integer value = p.number;
                Variable var = SemanticElement.IntegerVar(varName);
                var.value=value;
                gidManager.defineVar(varName,var);
            } else {
                //type
                GeneralizedIdentifier typeGid = identifier(pf.upperCaseIdentifier().getText());
                Type type = new Type(typeGid,p.gid);
                gidManager.defineType(typeGid, type);
            }
        }
    }
}


