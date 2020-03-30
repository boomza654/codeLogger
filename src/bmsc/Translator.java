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
 * <b>SHOULD NOT CALL VISIT DIRECTLY UNLESS YOU KNOW WHAT YOU ARE DOING</b>
 * Or else the intermediate result might not be ready for stuff
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
    public static String INDENT = "    ";
    public final GeneralizedIdentifierManager gidManager;
    public final HashMap<String,Object> resultMap;// to put in other results other than code into there  ( like reporting result)
    public Translator(GeneralizedIdentifierManager gidManager) {
        this.gidManager=gidManager;
        this.resultMap=new HashMap<>();
    }
    
    
    @Override public String visitTypeDefEnum(TypeDefEnumContext ctx) {
        String out = ("typedef enum { ");
        List<String> elements = ctx.typeDefEnumElement().stream().map((e)->e.getText()).collect(Collectors.toList());
        out+=String.join(", ", elements);
        out+=("} "+ctx.upperCaseIdentifier().getText()+" deriving(Bits, Eq, FShow);\n");
        return out;
    }
    @Override public String visitStructMember(StructMemberContext ctx) {
        String out = visit(ctx.type())+" "+ctx.lowerCaseIdentifier().getText()+";";
        return out;
    }
    @Override public String visitType(TypeContext ctx) {
         GeneralizedIdentifier gid = GidExtracter.extractGidAndRegisterType(ctx, gidManager);
         return gid.toProperTypeString(gidManager);
    }
    @Override public String visitArgFormal(ArgFormalContext ctx) {
        return visit(ctx.type())+" "+ctx.argName.getText();
    }
    @Override public String visitArgFormals(ArgFormalsContext ctx) {
        List<String> argFormals = ctx.argFormal().stream().map((e)->visit(e)).collect(Collectors.toList());
        return "("+String.join(", ", argFormals)+")";
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
        resultMap.clear(); // clear result out 
        System.out.println("Start Translating Type: "+ gid);
        
        ParserRuleContext toTranslate=(ParserRuleContext)gidManager.getType(gid).definition; // you should not translate type of Minispec static synonym at all
        if(toTranslate instanceof TypeDefStructContext) {
            TypeDefStructContext ctx = (TypeDefStructContext) toTranslate;
            return translateTypeDefStruct(gid, ctx);
        } else if(toTranslate instanceof TypeDefSynonymContext){

            TypeDefSynonymContext ctx = (TypeDefSynonymContext) toTranslate;
            return translateTypeDefSynonym(gid, ctx);
        }else if(toTranslate instanceof ModuleDefContext){

            ModuleDefContext ctx = (ModuleDefContext) toTranslate;
            return translateModuleDef(gid, ctx);
        }else if(toTranslate instanceof TypeDefEnumContext){
            return visit(toTranslate);
        }
        return null;
    }
    
    /**
     * try translate the STruct type def (take care of parameterization as well)
     * @param gid
     * @param ctx
     * @return string for transaltion
     */
    public String translateTypeDefStruct(GeneralizedIdentifier gid,TypeDefStructContext ctx) {
        resultMap.clear(); // clear result out 
        List<ParamFormalContext> paramFormalNodes = ctx.typeId().paramFormals()!=null?ctx.typeId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        gidManager.enterImmutableScope();
        if(isParametric) {
            matchParameter(gid.params,paramFormalNodes);
        }
        String out = "typedef struct {\n";
        for(StructMemberContext ectx: ctx.structMember()) {
            out+=INDENT+visit(ectx)+"\n";
        }
        out+="} "+gid.toStringEscapeParametric()+"  deriving(Bits, Eq, FShow);\n";
        gidManager.exitScope();
        return out;
    }
    /**
     * try translate the synonym typedef (take care of parameterization as well)
     * @param gid
     * @param ctx
     * @return string for transaltion
     */
    public String translateTypeDefSynonym(GeneralizedIdentifier gid,TypeDefSynonymContext ctx) {
        resultMap.clear(); // clear result out 
        List<ParamFormalContext> paramFormalNodes = ctx.typeId().paramFormals()!=null?ctx.typeId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        gidManager.enterImmutableScope();
        if(isParametric) {
            matchParameter(gid.params,paramFormalNodes);
        }
        String out = "typedef "+visit(ctx.type())+" "+gid.toStringEscapeParametric()+";\n";
        gidManager.exitScope();
        return out;
    }
    
    /**
     * try translate the module (take care of parameterization as well)
     * @param gid
     * @param ctx
     * @return string for transaltion
     */
    public String translateModuleDef(GeneralizedIdentifier gid,ModuleDefContext ctx) {
        resultMap.clear(); // clear result out 
        List<ParamFormalContext> paramFormalNodes = ctx.moduleId().paramFormals()!=null?ctx.moduleId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        gidManager.enterImmutableScope();
        if(isParametric) {
            matchParameter(gid.params,paramFormalNodes);
        }
        // Real synthesis
        String properInterfaceName = gid.toStringEscapeParametric();
        String interfaceCode = "interface "+properInterfaceName+";\n";
        // More on interface code later
        String out = "module "+ gid.toProperModuleString(gidManager);
        if(ctx.argFormals()!=null) out+="#"+visit(ctx.argFormals());
        out+=";\n";
        out+="endmodule\n";
        gidManager.exitScope();
        return out;
    }
    /**
     * Will let gidManager define Var/ type according to the matched param=> paramformal
     * @param parameters List of Parameter being matched
     * @param paramFormals List of ParamFormal Nodes being matched
     * @effect  gidManager get defined new Var / Type
     */
    private void matchParameter(List<Parameter> parameters, List<ParamFormalContext> paramFormals ) {
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
                gidManager.defineType(typeGid, type); // Static elaboration Type def are definied using Tyep instead of using parse tree
            }
        }
    }
    /**
     * Define variable into gidManager 
     * @param argFormals
     */
    private void defineArgFormal( List<ArgFormalContext> argFormals ) {
        for(int i=0;i< argFormals.size();i++) {
        }
    }
}


