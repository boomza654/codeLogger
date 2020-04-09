package bmsc;

import static bmsc.BBoolean.bbool;
import static bmsc.GeneralizedIdentifier.identifier;

import java.util.ArrayList;
import java.util.List;

import api.antlr4.MinispecBaseVisitor;
import api.antlr4.MinispecParser.BinopExprContext;
import api.antlr4.MinispecParser.BitConcatContext;
import api.antlr4.MinispecParser.BlockExprContext;
import api.antlr4.MinispecParser.CallExprContext;
import api.antlr4.MinispecParser.CaseExprContext;
import api.antlr4.MinispecParser.CaseExprItemContext;
import api.antlr4.MinispecParser.CondExprContext;
import api.antlr4.MinispecParser.ExprPrimaryContext;
import api.antlr4.MinispecParser.ExpressionContext;
import api.antlr4.MinispecParser.FieldExprContext;
import api.antlr4.MinispecParser.IntLiteralContext;
import api.antlr4.MinispecParser.MemberBindContext;
import api.antlr4.MinispecParser.ParenExprContext;
import api.antlr4.MinispecParser.ReturnExprContext;
import api.antlr4.MinispecParser.SliceExprContext;
import api.antlr4.MinispecParser.StringLiteralContext;
import api.antlr4.MinispecParser.StructExprContext;
import api.antlr4.MinispecParser.UndefinedExprContext;
import api.antlr4.MinispecParser.UnopExprContext;
import api.antlr4.MinispecParser.VarExprContext;

/**
 * Evalautor that tries unroll everything at compile time and return the text/ value represneting it
 * return string in case of needing to use code to represent the expression instead of value
 *  (Since I'm too lazy to create an Expression class and String literal cant do anything anyway)
 * 
 * NOTE: Since this also involve escaping a function name / Struct name based on things => Make sure that escaping mechanism is called after the first pass
 * @author boomza654
 *
 */
public class ExpressionEvaluator extends MinispecBaseVisitor<Object>{
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
        if(Utility.isMinispecUnsizedLiteral(ctx.getText())){
            try {
                return Utility.getValueMinispecIntLiteral(ctx.getText());
            } catch(NumberFormatException e) {
                return ctx.getText()+"/*The number literal is too large to be preocessed*/";
            }
        }
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
        } else if (value instanceof BBoolean) {
            BBoolean v = (BBoolean) value;
            switch(op) {
                case "!": return bbool(!v.value); 
                default: return op+(v);
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
            case "<": return bbool(l<r);
            case "<=": return bbool(l<=r);
            case ">": return bbool(l>r);
            case ">=": return bbool(l>=r);
            case "==": return bbool(l==r);
            case "!=": return bbool(l!=r);
            default: return l+" "+op+" "+r;
            }
        } else if(left instanceof BBoolean && right instanceof BBoolean) {
            BBoolean l=(BBoolean) left;
            BBoolean r = (BBoolean) right;
            switch(op) {
            case "&&": return bbool(l.value&&r.value);
            case "||": return bbool(l.value||r.value);
            default: return l+" "+op+" "+r; 
            }
        } else {
            return left+" "+op+" "+right;
        }
    }
    
    @Override public Object visitCondExpr(CondExprContext ctx) { 
        Object pred = visit(ctx.pred);
        if (pred instanceof BBoolean) {
            Object value = visit(ctx.expression(((BBoolean)pred).value?1:2));
            if(isCompileTimeValue(value)) {
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
            GeneralizedIdentifier gid =GidExtracter.extractGidAndRegisterFunc(ctx, gidManager);
            
            return gid.toStringEscapeParametric();
            
        } else {
            // either function with no parametric or varaible
            String name = ctx.var.getText();
            // boolean literal
            if(name.equals("True") || name.equals("False")) {
                return bbool(name.equals("True"));
            }
            Func foundFunc = gidManager.getFunc(identifier(name));
            Variable foundVar = gidManager.getVar(name);
            if(foundFunc==null && foundVar==null) {
                Utility.println("Warning "+name+" is not either Minispec function or varaible");
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
        //Utility.println("Start Evaluating :"+ ctx.getText());
        ExpressionEvaluator evaluator = new ExpressionEvaluator(gidManager);
        Object result = evaluator.visit(ctx);
        //Utility.println("Evaluted: "+ctx.getText()+" = "+ result);
        return result;
    }
    
    private static boolean isCompileTimeValue(Object x) {return x instanceof Integer || x instanceof BBoolean;}
}