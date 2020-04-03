package bmsc;
import api.antlr4.*;
import api.antlr4.MinispecParser.ParamContext;

import static api.antlr4.MinispecParser.*;
import static bmsc.GeneralizedIdentifier.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bunch of methods that will perform static elaboration
 * - Loop unroll
 * - If/case Integer
 * - Parametrics unroll
 * 
 * adapted directly fromm elaborator class in translate.cpp
 * @author boomza654
 *
 */
public class Elaborater {


    /**
     * Perform First pass on the content of the parsedfile 
     * registering Types / function / Integer variable outmost / Parametrics into the gidManager
     * @param parsedFile
     * @param gidManager
     */
    public static void firstPassGidRegister(ParsedFile parsedFile, GeneralizedIdentifierManager gidManager) {
        FirstPassGidRegister.firstPass((PackageDefContext)parsedFile.parserResult.parseTree(), gidManager);
    }
    /**
     * 
     * @param exprNode needs to be a compile-time unrollable expression
     * @param gidManager
     * @return evaluated expression exprNode
     */
    public static Object evaluateExpressionNode(ExpressionContext exprNode, GeneralizedIdentifierManager gidManager) {
        // Fake expressino evaluation
        return ExpressionEvaluator.evaluate(exprNode, gidManager);
        //throw new RuntimeException("Unimplemented");
        
    }

}

/*
 * 
 *  Note that:
 *  
 *  Outmost Integer declaration  depends on other outmost Integer variable (But they are top-down so that's fine) ( 1st pass)
 *  Type def depends on definition of nothing other than Integer (but they are evaluated top-down so that's fine)(1st pass)
 *  Function def depends on Types (but no longer anymore since we dont care about tye of function), and Integer (1st Pass)
 *  Variable Decl depends on type ( not top-down) ( 2nd Pass)
 *  Expression depends on var(but in a top-down manner so that does not really count),func,type  ( 2nd Pass)
 *  
 *  
 *  Technically, we should have some Toposort to sort out dependencies but too lazy now
 */


/**
 * Register Parametric/Type/function names/ Integer outermost varaible into General Id Manager in the first pass (so that we know which Type identifier is our identifier and which one is not)
 * ( Before actually synthesize them)<br/>
 * - All statement are ignored except for VarDecl for Integer<br/>
 * - We can try register function name in the first pass since we do no longer care about types
 * 
 * @author boomza654
 *
 */
class FirstPassGidRegister extends MinispecBaseVisitor<Void>{
    
    public final GeneralizedIdentifierManager gidManager;
    public FirstPassGidRegister(GeneralizedIdentifierManager gidManager) {
        this.gidManager=gidManager;
    }
    
    @Override public Void visitTypeDefSynonym (TypeDefSynonymContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.typeId().paramFormals()!=null)? ctx.typeId().paramFormals().paramFormal():List.of();
        
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        if(isParametric) {
            String name =ctx.typeId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            System.out.println("Register Parametric     " +name);
            gidManager.defineParametric(name, definition);
        } else {
            GeneralizedIdentifier newGid = GidExtracter.extractGidFormal(ctx.typeId().name.getText(),paramFormals, gidManager);
            Type definition = new Type(newGid,ctx); // reserve definition of type GID for static elaboration type unrolling
            System.out.println("Register Type (Syn)    " +newGid);
            gidManager.defineType(newGid, definition);
        }
        return null;
    }
    
    @Override public Void visitTypeDefEnum(TypeDefEnumContext ctx) {
        String name = ctx.upperCaseIdentifier().getText();
        GeneralizedIdentifier newGid= identifier(name);
        Type newEnumType = new Type(newGid, ctx);
        System.out.println("Register Type (Enum)   " +newGid);
        gidManager.defineType(newGid, newEnumType);
        return null;
    }
    
    @Override public Void visitTypeDefStruct(TypeDefStructContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.typeId().paramFormals()!=null)? ctx.typeId().paramFormals().paramFormal():List.of();
        
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        if(isParametric) {
            String name =ctx.typeId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            System.out.println("Register Parametric    " +name);
            gidManager.defineParametric(name, definition);
        } else {
            
            GeneralizedIdentifier newGid= GidExtracter.extractGidFormal(ctx.typeId().name.getText(),paramFormals, gidManager);
            Type definition = new Type(newGid,ctx);
            System.out.println("Register Type (Struct) " +newGid);
            gidManager.defineType(newGid, definition);
        }
        return null;
    }
    
    @Override public Void visitModuleDef(ModuleDefContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.moduleId().paramFormals()!=null)? ctx.moduleId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        if(isParametric) {
            String name = ctx.moduleId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            System.out.println("Register Parametric    " +name);
            gidManager.defineParametric(name, definition);
        } else {
            GeneralizedIdentifier newGid= GidExtracter.extractGidFormal(ctx.moduleId().name.getText(), paramFormals, gidManager);
            Type definition = new Type(newGid,ctx);
            System.out.println("Register Type (Module) " +newGid);
            gidManager.defineType(newGid, definition);
        }
        return null;
    }
    
    @Override public Void visitFunctionDef(FunctionDefContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.functionId().paramFormals()!=null)? ctx.functionId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        if(isParametric) {
            String name = ctx.functionId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            System.out.println("Register Parametric    " +name);
            gidManager.defineParametric(name, definition);
        } else {
            GeneralizedIdentifier newGid= GidExtracter.extractGidFormal(ctx.functionId().name.getText(), paramFormals, gidManager);
            Func definition = new Func(newGid,ctx);
            System.out.println("Register Type (Func)   " +newGid);
            gidManager.defineFunc(newGid, definition);
        }
        return null;
        
    }
    
    @Override public Void visitVarBinding(VarBindingContext ctx) {
        // Support only Integer
        //if(ctx.type().getText().equals("Integer")) {
            for(VarInitContext victx:ctx.varInit()) {
                GeneralizedIdentifier typeId =GidExtracter.extractGid(ctx.type().name.getText(),
                        (ctx.type().params()!=null? ctx.type().params().param(): List.of()),
                        gidManager);
                Variable var = new Variable(typeId,victx.var.getText());
                var.value= ExpressionEvaluator.evaluate(victx.expression(), gidManager); 
                // no new function / type will be instatiate since there is no parametric registered yet
                if(ctx.type().getText().equals("Integer"))
                    assert var.value instanceof Integer: "THe evaluated value of "+var.name+" is not compile-time Integer";
                else
                    var.value=var.value.toString();
                System.out.println("Register Variable      "+typeId.toString()+" "+var.name+" = "+var.value);
                gidManager.defineVar(var.name, var);
            }
       // }
        return null;
    }
    
    @Override public Void visitLetBinding ( LetBindingContext ctx) {
        throw new RuntimeException("Warning: \'let\' is forbidden in top-level context");
    }

    /**
     * Run first pass gid register on the whole package Def context
     * @param ctx packade def context of a file
     * @param gidManager to register types and names
     */
    public static void firstPass(PackageDefContext ctx, GeneralizedIdentifierManager gidManager) {
        FirstPassGidRegister visitor = new FirstPassGidRegister(gidManager);
        visitor.visit(ctx);
        
    }
}

/**
 * Collection of function related to extractin GID from Node
 * @author boomza654
 *
 */
class GidExtracter{
    
    /**
     * Try to extract GId of a type and also register them if the type did not exist before but its parametrized version exist)
     * 
     * @param ctx
     * @param gidManager
     * @return Generalized identifier from the extraction
     */
    public static GeneralizedIdentifier extractGidAndRegisterType(TypeContext ctx, GeneralizedIdentifierManager gidManager) {

        List<ParamContext> paramNodes= ctx.params()!=null?ctx.params().param():List.of();
        GeneralizedIdentifier gid = GidExtracter.extractGid(ctx.name.getText(), paramNodes, gidManager);
        if(gidManager.getType(gid)==null && gidManager.getParametric(ctx.name.getText())!=null) {
            // REgister new Type
            Object definition = gidManager.getParametric(ctx.name.getText()).definition;
            System.out.println("Define new Type "+ gid+" from parametric");
            gidManager.defineTypeAtTop(gid, new Type(gid,definition));
        }
        return gid;
    }
    /**
     * Create a generalized identifier
     * @param name the name of gID
     * @param paramNodes the param Node needed to be synthesized
     * @param gidManager the gidManager to extract integer value from
     * @return Gid of name#(paramNodes)
     */
    public static GeneralizedIdentifier extractGid (String name, List<ParamContext> paramNodes , GeneralizedIdentifierManager gidManager) {
        List<Parameter> params = new ArrayList<>();
        if(paramNodes.isEmpty() && gidManager.getType(identifier(name))!=null && gidManager.getType(identifier(name)).definition instanceof GeneralizedIdentifier ) {
            // Minispec static typedef need substitution
            return (GeneralizedIdentifier)gidManager.getType(identifier(name)).definition;
        }
        if(!paramNodes.isEmpty()) {
            for(ParamContext paramNode : paramNodes) {
                if(paramNode.type()!=null) {
                    TypeContext paramType = paramNode.type();
                    List<ParamContext> typeParamNodes = paramType.params()!=null? paramType.params().param():List.of();
                    GeneralizedIdentifier toAdd =extractGid(paramType.name.getText(),typeParamNodes,gidManager);
                    params.add(new Parameter(null,toAdd));
                    
                } else if (paramNode.intParam!=null) {
                    Object value = ExpressionEvaluator.evaluate(paramNode.expression(), gidManager);
                    assert value instanceof Integer: "The parameter is not compiletime unrolled";
                    params.add(new Parameter((Integer)value,null));
                }
            }
        } 
        return new GeneralizedIdentifier(name, params);
    }
    /**
     * Create a generalized identifier
     * @param name the name of gID
     * @param paramFormalNodes the paramforml Node needed to be synthesized ( must be concrete)
     * @param gidManager the gidManager to extract integer value from
     * @return Gid of name#(paramNodes)
     */
    public static GeneralizedIdentifier extractGidFormal (String name, List<ParamFormalContext> paramFormalNodes , GeneralizedIdentifierManager gidManager) {
        List<ParamContext> paramNodes = new ArrayList<>();
        for(ParamFormalContext paramFormalNode : paramFormalNodes) {
            ParamContext paramNode= paramFormalNode.param();
            assert paramNode!=null : "paramFormal is not concrete";
            paramNodes.add(paramNode);
        }
        return extractGid(name,paramNodes,gidManager);
    }
    /**
     * Return whether in those Param Formal nodes it is all concrete
     * @param paramFormalNodes
     * @return boolean
     */
    public static boolean isParametric(List<ParamFormalContext> paramFormalNodes) {
        boolean out=false;
        for(ParamFormalContext paramFormalNode : paramFormalNodes) {
            if(paramFormalNode.param()==null) {out=true; break;}
        }
        return out;
    }
}
/**
 * Evalautor that tries unroll everything at compile time and return the text/ value represneting it
 * return string in case of needing to use code to represent the expression instead of value
 *  (Since I'm too lazy to create an Expression class and String literal cant do anything anyway)
 * 
 * NOTE: Since this also involve escaping a function name / Struct name based on things => Make sure that escaping mechanism is called after the first pass
 * @author boomza654
 *
 */
class ExpressionEvaluator extends MinispecBaseVisitor<Object>{
    public static final String INDENT_STRING="    ";
    public final GeneralizedIdentifierManager gidManager;
    public ExpressionEvaluator(GeneralizedIdentifierManager gidManager) {this.gidManager=gidManager;}
    @Override public Object visitStringLiteral(StringLiteralContext ctx) {
        return ctx.getText();
    }
    
    @Override public Object visitUndefinedExpr(UndefinedExprContext ctx) {
        return "?";
    }
    
    @Override public Object visitIntLiteral(IntLiteralContext ctx) {
        if(Utility.isMinispecUnsizedLiteral(ctx.getText()))
            return Utility.getValueMinispecIntLiteral(ctx.getText());
        return ctx.getText();
    }
    
    @Override public Object visitUnopExpr(UnopExprContext ctx) { 
        
        if(ctx.op==null) return visit(ctx.exprPrimary());
        String op=ctx.op.getText();
        Object value = visit(ctx.exprPrimary());
        if(value instanceof Integer) {
            Integer v = (Integer) value;
            switch(op) {
                case "+": return v;
                case "-": return -v;
                case "~": return ~v;
                // Ignore reduce and,or ,xor
                default: return op+v;
            }
        } else if (value instanceof Boolean) {
            Boolean v = (Boolean) value;
            switch(op) {
                case "!": return !v;
                default: return op+(v?"True":"False");
            }
        } else {
            return op+value;
        }
    }
    
    @Override public Object visitBinopExpr(BinopExprContext ctx) {
        if(ctx.unopExpr()!=null) return visit(ctx.unopExpr());
        Object left = visit(ctx.left);
        Object right = visit(ctx.right);
        String op = ctx.op.getText();
        if(left instanceof Integer && right instanceof Integer) {
            Integer l = (Integer)left;
            Integer r = (Integer) right;
            switch(op) {
            case "+": return l+r;
            case "-": return l-r;
            case "*": return l*r;
            case "/": return l/r;
            case "%": return l%r;
            case "**": 
                int result = 1; for(int i=0;i<r;i++) result*=l; return result;
            case "<<": return l<<r;
            case ">>": return l>>r;
            case "&": return l&r;
            case "!": return l|r;
            case "^": return l^r;
            case "^~":
            case "~^": return l^~r;
            case "<": return l<r;
            case "<=": return l<=r;
            case ">": return l>r;
            case ">=": return l>=r;
            case "==": return l==r;
            case "!=": return l!=r;
            default: return l+" "+op+" "+r;
            }
        } else if(left instanceof Boolean && right instanceof Boolean) {
            Boolean l=(Boolean) left;
            Boolean r = (Boolean) right;
            switch(op) {
            case "&&": return l&&r;
            case "||": return l||r;
            default: return (l?"True":"False")+" "+op+" "+(r?"True":"False"); 
            }
        } else {
            return left+" "+op+" "+right;
        }
    }
    
    @Override public Object visitCondExpr(CondExprContext ctx) { 
        Object pred = visit(ctx.pred);
        if (pred instanceof Boolean) {
            Object value = visit(ctx.expression((Boolean)pred?1:2));
            if(value instanceof Boolean || value instanceof Integer) {
                return value;
            } else {
                return "("+value+")";
            }
            
        } else {
            return "("+pred+"?"+visit(ctx.expression(1))+":"+visit(ctx.expression(2))+")";
        }
    }
    
    @Override public Object visitCaseExpr(CaseExprContext ctx) {
        
        Object branch= visit(ctx.expression());
        if(isCompileTimeValue(branch)) {
            // Try to match some case
            ExpressionContext defaultExpression=null;
            for(CaseExprItemContext ictx: ctx.caseExprItem()) {
                if(ictx.exprPrimary().isEmpty()) defaultExpression=ictx.body;
                
                for(ExprPrimaryContext ectx: ictx.exprPrimary()) {
                    Object choice = visit(ectx);
                    if(branch.equals(choice))
                        return visit(ictx.body);
                }
            }
            // Else match with default:
            return visit(defaultExpression);
        }
        else {
            String out = "case("+branch+")\n";
            for(CaseExprItemContext ictx : ctx.caseExprItem()) {
                //out+=INDENT_STRING;
                String item="";
                if(ictx.exprPrimary().isEmpty()) item+="default : ";
                else {
                    List<String> matchCases = new ArrayList<>();
                    String matchCase="";
                    for(ExprPrimaryContext ectx: ictx.exprPrimary()) {
                        matchCases.add(visit(ectx).toString());
                    }
                    matchCase=String.join(", ", matchCases);
                    item+=matchCase+": ";
                }
                item+=visit(ictx.expression())+";\n";
                out+=Utility.addPrefix(item, INDENT_STRING);
            }
            out+="endcase";
            return out;
        }
    }
    
    @Override public Object visitParenExpr(ParenExprContext ctx) {
        Object value = visit(ctx.expression());
        if(isCompileTimeValue(value)) return value;
        else return "("+value+")";
    }
    
    @Override public Object visitFieldExpr(FieldExprContext ctx) {
        return visit(ctx.exprPrimary())+"."+(ctx.field.getText());
    }
    
    @Override public Object visitVarExpr(VarExprContext ctx) {
        if(ctx.params()!=null) {
            // This is parametric function
            // There is no Bluespec Parametric function so Always escape the identifier
            // try to check if its user parametric so that we need to declare new func
            GeneralizedIdentifier gid = GidExtracter.extractGid(ctx.var.getText(), ctx.params().param(), gidManager);
            Func foundFunc = gidManager.getFunc(gid);
            Parametric foundParametric = gidManager.getParametric(ctx.var.getText());
            if(foundFunc==null) {
                if(foundParametric!=null) {
                    // call func#(4) but never declared func#(4)
                    // add new func
                    System.out.println("Define new Function "+gid+" from parametric");
                    gidManager.defineFunc(gid, new Func(gid,foundParametric.definition));
                }else {
                    // Error case: Paremetric call but nothing is found but let it slide cuz maybe some Bluespec thing is parametric
                    System.out.println("Warning "+gid+" has no defined function / parametric asssociated with it");
                }
            } // Else the func#(4) is already defined either by user / by prior synthesizing
            
            return gid.toStringEscapeParametric();
            
        } else {
            // either function with no parametric or varaible
            String name = ctx.var.getText();
            Func foundFunc = gidManager.getFunc(identifier(name));
            Variable foundVar = gidManager.getVar(name);
            if(foundFunc==null && foundVar==null) {
                System.out.println("Warning "+name+" is not either Minispec function or varaible");
                // Still okay cuz this might be lusepc function
            }
            if(foundVar!=null && foundVar.typeId.equals(SemanticElement.INTEGER_TYPE.typeId) && (foundVar.value instanceof Integer)) {
                // Try to substitute varaible with Integer
                return foundVar.value;
            }else {
                // just return text
                return ctx.getText();
            }
        }
    }
    
    @Override public Object visitReturnExpr(ReturnExprContext ctx) {
        return "return "+visit(ctx.expression());
    }
    
    @Override public Object visitBitConcat(BitConcatContext ctx) {
        List<String> items = new ArrayList<>();
        for(ExpressionContext ectx: ctx.expression()) {
            items.add(visit(ectx).toString());
        }
        return "{"+String.join(", ", items)+"}";
    }
    
    @Override public Object visitSliceExpr(SliceExprContext ctx) {
        return visit(ctx.array)+"["+visit(ctx.msb)+ (ctx.lsb!=null? ":"+visit(ctx.lsb):"")+"]";
    }
    
    @Override public Object visitCallExpr(CallExprContext ctx) {
        // log2 function is only valid compole time
        if(ctx.fcn.getText().equals("log2") && ctx.expression().size()==1) {
            Object value = visit(ctx.expression().get(0));
            if(value instanceof Integer) {
               Integer v = (Integer) value;
               return  31-Integer.numberOfLeadingZeros(v);
            }
        }
        List<String> items = new ArrayList<>();
        for(ExpressionContext ectx: ctx.expression()) {
            items.add(visit(ectx).toString());
        }
        return visit(ctx.fcn)+"("+ String.join(", ", items)+")";
    }

    @Override public Object visitBlockExpr(BlockExprContext ctx) {
        return (new Translator(gidManager)).translateBeginEndBlock(ctx.beginEndBlock()); // will be String for sure
    }
    
    @Override public Object visitStructExpr(StructExprContext ctx) {
        
        GeneralizedIdentifier gid = GidExtracter.extractGidAndRegisterType(ctx.type() , gidManager);

        List<String> items = new ArrayList<>();
        for(MemberBindContext mctx: ctx.memberBinds().memberBind()) {
            items.add(mctx.field.getText()+":"+visit(mctx.expression()).toString());
        }
        // There is no built-in Struct
        //TODO: maybe fix this to toProperTypeNAme()?
        return gid.toStringEscapeParametric()+"{"+ String.join(", ", items)+"}";
    }
    
    /**
     * Evaluate expression
     * @param ctx the expression node
     * @param gidManager the Identifier Manager to extract value / put more type into it
     * @return the evaluated Expression
     */
    public static Object evaluate(ExpressionContext ctx, GeneralizedIdentifierManager gidManager) {
        //System.out.println("Start Evaluating :"+ ctx.getText());
        ExpressionEvaluator evaluator = new ExpressionEvaluator(gidManager);
        Object result = evaluator.visit(ctx);
        System.out.println("Evaluted: "+ctx.getText()+" = "+ result);
        return result;
    }
    
    private static boolean isCompileTimeValue(Object x) {return x instanceof Integer || x instanceof Boolean;}
}
