package bmsc;
import api.antlr4.*;
import api.antlr4.MinispecParser.CaseExprItemContext;
import api.antlr4.MinispecParser.ExprPrimaryContext;
import api.antlr4.MinispecParser.ExpressionContext;
import api.antlr4.MinispecParser.LetBindingContext;
import api.antlr4.MinispecParser.TypeDefEnumElementContext;
import api.antlr4.MinispecParser.VarBindingContext;
import api.antlr4.MinispecParser.VarInitContext;

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

    public static String INDENT = "    ";
    public static Set<String> DISPLAY_FUNCS = Set.of("$display","$write","$displayb","$displayo","$displayh","$writeb","$writeo","$writeh");
    public final GeneralizedIdentifierManager gidManager;
    //public final HashMap<String,Object> resultMap;// to put in other results other than code into there  ( like reporting result)
    public final List<GeneralizedIdentifier> dependentSubModules; // to report dependent Submodules result from parsing them odule
    public Translator(GeneralizedIdentifierManager gidManager) {
        this.gidManager=gidManager;
        //this.resultMap=new HashMap<>();
        this.dependentSubModules=new ArrayList<>();
    }
    
    @Override public String visitTypeDefEnumElement(TypeDefEnumElementContext ctx) {
        return ctx.getText();
    }
    @Override public String visitTypeDefEnum(TypeDefEnumContext ctx) {
        String out = ("typedef enum { ");
        out+=visitJoin(ctx.typeDefEnumElement(),", ");
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
    // Just return the code of ARG Formal but not register / match any args
    @Override public String visitArgFormals(ArgFormalsContext ctx) {
        return "("+visitJoin(ctx.argFormal(), ", ")+")";
    }
    @Override public String visitStmt(StmtContext ctx) {
        if(ctx.expression()!=null) {
            // ignore display and write statement
            // Expression in stmt context can potentailly be system calls so we haveto be careful with that
            if(ctx.expression() instanceof OperatorExprContext) {
                OperatorExprContext octx = (OperatorExprContext)ctx.expression();
                if(octx.binopExpr().unopExpr()!=null && octx.binopExpr().unopExpr().exprPrimary()!=null && octx.binopExpr().unopExpr().exprPrimary() instanceof CallExprContext) {
                    CallExprContext cctx = (CallExprContext)octx.binopExpr().unopExpr().exprPrimary();
                    if(DISPLAY_FUNCS.contains(cctx.exprPrimary().getText())) {
                        return "";
                    }
                }
            } 
            return ExpressionEvaluator.evaluate(ctx.expression(), gidManager)+";\n";
        } else if (ctx.varDecl()!=null) {
            return visit(ctx.varDecl());
        } else if (ctx.varAssign()!=null) {
            return visit(ctx.varAssign());
        } else if (ctx.regWrite()!=null) {
            return visit(ctx.regWrite());
        } else if (ctx.beginEndBlock()!=null) {
            return visit(ctx.beginEndBlock());
        } else if (ctx.ifStmt()!=null) {
            return visit(ctx.ifStmt());
        } else if (ctx.caseStmt()!=null) {
            return visit(ctx.caseStmt());
        } else if (ctx.forStmt()!=null) {
            return visit(ctx.forStmt());
        }
        throw new RuntimeException("WTF Stmt un handled");
        //return "";
    }
    @Override public String visitBeginEndBlock(BeginEndBlockContext ctx) {
        String out = "begin\n";
        gidManager.enterMutableScope();
        out+=Utility.addPrefix(visitJoin(ctx.stmt(), "")+"\n",INDENT);
        out+="end\n";
        gidManager.exitScope();
        return out;
        
    }
    @Override public String visitVarBinding(VarBindingContext ctx) {
        String out = "";
        GeneralizedIdentifier typeId =GidExtracter.extractGid(ctx.type().name.getText(),
                (ctx.type().params()!=null? ctx.type().params().param(): List.of()),
                gidManager);
        out+=typeId.toProperTypeString(gidManager)+" ";
        List<String> varInitString = new ArrayList<>();
        for(VarInitContext victx:ctx.varInit()) {
            Variable var = new Variable(typeId,victx.var.getText());
            if(victx.expression()!=null)
                var.value= ExpressionEvaluator.evaluate(victx.expression(), gidManager); 
            else
                var.value="?";
            // no new function / type will be instatiate since there is no parametric registered yet
            if(ctx.type().getText().equals("Integer"))
                assert var.value instanceof Integer: "THe evaluated value of "+var.name+" is not compile-time Integer";
            else
                var.value=var.value.toString();
            //Utility.println("Register Variable      "+typeId.toString()+" "+var.name+" = "+var.value);
            gidManager.defineVar(var.name, var);
            varInitString.add(var.name+"="+var.value);
        }
        out+=String.join(", ", varInitString)+";\n";
        String integerDecl = "";
        return ctx.type().getText().equals("Integer")?integerDecl:out;
    }
    
    @Override public String visitLetBinding ( LetBindingContext ctx) {
        Object value = ExpressionEvaluator.evaluate(ctx.expression(), gidManager);
        if(value instanceof Integer) {
            // Integer variable
            assert ctx.lowerCaseIdentifier().size()==1 : "Erro can't let Integer to more than 1 var";
            String varName =ctx.lowerCaseIdentifier().get(0).getText();
            Variable var = new Variable(SemanticElement.INTEGER_TYPE.typeId,varName);
            var.value=value;
            gidManager.defineVar(varName, var);
            return "";
        } else { // normal let
            List<String> varNames = ctx.lowerCaseIdentifier().stream().map((e)->e.getText()).collect(Collectors.toList());
            for(String varName:varNames) {
                gidManager.defineVar( varName, new Variable(GeneralizedIdentifier.UNKNOWN,varName));
            }
            if(varNames.size()==1) {
                return "let "+varNames.get(0)+"="+value+";\n";
            }else {
                return "let {"+String.join(", ", varNames)+"}="+value+";\n";
            }
            
        }
    }
    
    @Override public String visitVarAssign(VarAssignContext ctx) {
        if(ctx.var==null) {
            // bit unpacking assing
            String out ="{";
            out+=visitJoin(ctx.lvalue(),", ")+"}=";
            out+= ExpressionEvaluator.evaluate(ctx.expression(), gidManager)+";\n";
            return out;
        } else {
            // deal with Integer
            if(ctx.var instanceof SimpleLvalueContext) {
                String varName = ((SimpleLvalueContext)ctx.var).getText();
                Variable var = gidManager.getVar(varName);
                if(var.typeId.equals(SemanticElement.INTEGER_TYPE.typeId)) {
                    // deal with integer
                    Object assignedValue= ExpressionEvaluator.evaluate(ctx.expression(), gidManager);
                    assert assignedValue instanceof Integer : "Error cannot convert Expression "+ ctx.expression().getText()+" to int value";
                    gidManager.setVar(varName, assignedValue);
                    return "";
                }
            } else if (ctx.var instanceof MemberLvalueContext) {
                MemberLvalueContext mctx = (MemberLvalueContext)ctx.var;
                // Daniels' comment herer
                // memberLvalue's lvalue() can be a Simple/Index/Slice/Member lvalue.
                // Two of these are correct: simple and index.
                // The other two denote different errors (when used on submodules):
                // slice is just garbage, and member means the user is trying to reach
                // into a submodule's input (also super-illegal).
                // We first get to the base, and raise errors only if it's a submodule.
                LvalueContext lvctx = mctx.lvalue();
                boolean hasSlice= false;
                boolean hasMember=false; // We are assuming that struct of modules are pretty much undefined
                // as well as subinterface and stuff
                // but Vector of submodules are still fine... (which is questionable)
                while(!(lvctx instanceof SimpleLvalueContext)) {
                    if(lvctx instanceof MemberLvalueContext) {
                        hasMember=true;
                        lvctx=((MemberLvalueContext)lvctx).lvalue();
                    } else if(lvctx instanceof IndexLvalueContext) {
                        lvctx=((IndexLvalueContext)lvctx).lvalue();
                    }  else if(lvctx instanceof SliceLvalueContext) {
                        lvctx=((SliceLvalueContext)lvctx).lvalue();
                    }  
                }
                String varName = lvctx.getText();
                Variable var = gidManager.getVar(varName);
                if (var.isModule) {
                    // submodule ocmmunication here
                    assert !(hasSlice || hasMember || gidManager.isInMethod()) : "Err wrong submodule assignment syntax" ;

                    assert !gidManager.isInMethod(): "Error: perform submodule write inside method"; 
                    String out = visit(mctx.lvalue())
                            +"."
                            +inputNameToActionName(mctx.lowerCaseIdentifier().getText())
                            +"("+ExpressionEvaluator.evaluate(ctx.expression(), gidManager)+");\n";
                    return out;
                }
            }

            // Make sure not module input TODO
            return visit(ctx.var)+" = "+ExpressionEvaluator.evaluate(ctx.expression(), gidManager)+";\n";
        }
    }
    /* IF LVALUE are visited we assume that this is not referring to any submodule's input*/
    @Override public String visitSimpleLvalue(SimpleLvalueContext ctx){
        return ctx.getText();
    }
    @Override public String visitMemberLvalue(MemberLvalueContext ctx) {
        return visit(ctx.lvalue())+"."+ctx.lowerCaseIdentifier().getText();
    }
    @Override public String visitIndexLvalue(IndexLvalueContext ctx) {
        return visit(ctx.lvalue())+"["+ExpressionEvaluator.evaluate(ctx.index, gidManager)+"]";
    }
    @Override public String visitSliceLvalue(SliceLvalueContext ctx) {
        return visit(ctx.lvalue())+"["+ExpressionEvaluator.evaluate(ctx.msb, gidManager)+":"+ExpressionEvaluator.evaluate(ctx.lsb, gidManager)+"]";
    }
    @Override public String visitRegWrite(RegWriteContext ctx) {
        assert !gidManager.isInMethod(): "Error: perform register write inside method"; 
        return visit(ctx.lvalue())+" <= "+ ExpressionEvaluator.evaluate(ctx.expression(), gidManager)+";\n";
    }
    
    @Override public String visitIfStmt(IfStmtContext ctx) {
        Object branch = ExpressionEvaluator.evaluate(ctx.expression(),gidManager);
        if(branch instanceof BBoolean) {
            // Statically elaborated branch
            boolean sel = ((BBoolean) branch).value;
            boolean hasElse = ctx.stmt().size()==2;
            String code="";
            if(sel) {
                StmtContext sctx=ctx.stmt().get(0);
                gidManager.enterMutableScope();
                code =visit(sctx);
                gidManager.exitScope();
                if(sctx.beginEndBlock()==null) {
                    // encapsulate single statment in a begin end block
                    code=Utility.addPrefix(code, INDENT);
                    code= "begin\n"+code+"end\n";
                }
            } else if(hasElse) {
                StmtContext sctx=ctx.stmt().get(1);
                gidManager.enterMutableScope();
                code =visit(sctx);
                gidManager.exitScope();
                if(sctx.beginEndBlock()==null) {
                    // encapsulate single statment in a begin end block
                    code=Utility.addPrefix(code, INDENT);
                    code= "begin\n"+code+"end\n";
                }
            } else {
                code= "";
            }            
            return code;
        } else {
            String out = "if("+branch+")";
            gidManager.enterBranchScope();
            if(ctx.stmt().get(0).beginEndBlock()!=null) {
                out+=visit(ctx.stmt().get(0).beginEndBlock());
            } else {
                out+="begin\n"+Utility.addPrefix(visit(ctx.stmt().get(0)), INDENT)+"end\n";
            }
            gidManager.exitScope();
            boolean hasElse = ctx.stmt().size()==2;
            if(hasElse) {
                out+="else ";
                gidManager.enterBranchScope();
                if(ctx.stmt().get(1).beginEndBlock()!=null) {
                    out+=visit(ctx.stmt().get(1).beginEndBlock());
                } else {
                    out+="begin\n"+Utility.addPrefix(visit(ctx.stmt().get(1)), INDENT)+"end\n";
                }
                gidManager.exitScope();
            }
            gidManager.cleanUpBranchScopes();
            return out;
        }
    }
    
    @Override public String visitCaseStmt(CaseStmtContext ctx) {
        Object branch= ExpressionEvaluator.evaluate(ctx.expression(),gidManager);
        if(branch instanceof BBoolean || branch instanceof Integer) {
            // Statically elaborated case Stmt
            gidManager.enterMutableScope();
            String code =null;
            for(CaseStmtItemContext ictx: ctx.caseStmtItem()) {
                for(ExpressionContext ectx: ictx.expression()) {
                    Object choice = ExpressionEvaluator.evaluate(ectx, gidManager);
                    if(code==null && branch.equals(choice)) {
                        if(ictx.stmt().beginEndBlock()!=null) 
                            code=visit(ictx.stmt());
                        else
                            code= "begin\n"+Utility.addPrefix(visit(ictx.stmt()), INDENT)+"end\n";
                    }
                }
            }
            // Else match with default:
            if(ctx.caseStmtDefaultItem()!=null && code==null)
                if(ctx.caseStmtDefaultItem().stmt().beginEndBlock()!=null) 
                    code=visit(ctx.caseStmtDefaultItem().stmt());
                else
                    code= "begin\n"+Utility.addPrefix(visit(ctx.caseStmtDefaultItem().stmt()), INDENT)+"end\n";
            gidManager.exitScope();
            if(code==null) code="";
            return code;
        }
        else {
            String out = "case("+branch+")\n";
            for(CaseStmtItemContext ictx : ctx.caseStmtItem()) {
                //out+=INDENT_STRING;
               String item="";
               gidManager.enterBranchScope();
               List<String> exprs = new ArrayList<>();
               for(ExpressionContext ectx: ictx.expression()) {exprs.add(ExpressionEvaluator.evaluate(ectx, gidManager).toString());}
               item+=String.join(", ", exprs)+" : ";
               if(ictx.stmt().beginEndBlock()!=null)
                   item+=(visit(ictx.stmt()));
               else
                   item+="begin \n"+Utility.addPrefix(visit(ictx.stmt()),INDENT)+"end\n";
               out+=Utility.addPrefix(item, INDENT);
               gidManager.exitScope();
            }
            if(ctx.caseStmtDefaultItem()!=null) {

                String item="";
                item+="default : ";
                if(ctx.caseStmtDefaultItem().stmt().beginEndBlock()!=null)
                    item+=(visit(ctx.caseStmtDefaultItem().stmt()));
                else
                    item+="begin \n"+Utility.addPrefix(visit(ctx.caseStmtDefaultItem().stmt()),INDENT)+"end\n";

                out+=Utility.addPrefix(item, INDENT);
            }
            out+="endcase\n";
            gidManager.cleanUpBranchScopes();
            return out;
        }
    }
    
    @Override public String visitForStmt(ForStmtContext ctx) {
        String code="";
        gidManager.enterMutableScope();
        assert ctx.type().getText().equals("Integer"): "For looping not on Integer";
        String varName= ctx.initVar.getText();
        Object initObj = ExpressionEvaluator.evaluate(ctx.expression().get(0), gidManager);
        assert initObj instanceof Integer : "THe initializ value is not compile-time integer";
        Integer initValue = (Integer) initObj;
        assert ctx.updVar.getText().equals(varName): "Incremental varaible is not same as init var";
        Variable loopVar = SemanticElement.IntegerVar(varName);
        loopVar.value=initValue;
        gidManager.defineVar(varName, loopVar);
        ExpressionContext loopingPredicate = ctx.expression().get(1);
        ExpressionContext incExpression = ctx.expression().get(2);
        while(true) {
            Object predicate = ExpressionEvaluator.evaluate(loopingPredicate, gidManager);
            assert predicate instanceof BBoolean : "looping predicate is not evaluable compile time";
            BBoolean bpredicate = (BBoolean)predicate;
            if(!bpredicate.value) break;
            // then continue synthesizing
            //Utility.println("Elaborate For loop: "+varName+" = "+loopVar.value);
            if(ctx.stmt().beginEndBlock()!=null) {
                code+=visit(ctx.stmt());
            }else {
                code +="begin\n"+Utility.addPrefix(visit(ctx.stmt()), INDENT)+"end\n";
            }
            // Increment
            Object nextValue = ExpressionEvaluator.evaluate(incExpression, gidManager);
            assert nextValue instanceof Integer: "THe incremental expression is not compile time elaboratable";
            gidManager.setVar(varName, nextValue);
        }
        gidManager.exitScope();
        return code;
        
    }
    /**
     * Join visiting things in ctxs by j
     * @param ctxs visit each node in ctxs
     * @param j string to join
     * @return String of joined thing
     */
    public String visitJoin(List<? extends ParserRuleContext> ctxs, String j) {
        List<String> out = ctxs.stream().map((e)->visit(e)).collect(Collectors.toList());
        return String.join(j, out);
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
        dependentSubModules.clear(); // clear result out 
        Utility.println("Start Translating Type: "+ gid);
        
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
     * Start translating the registered type "gid" into the codeBuffer
     * @param gid of the type to translate
     * @return the code of that type definition
     */
    public String translateFunc(GeneralizedIdentifier gid) {
        if(gidManager.getFunc(gid)==null) {throw new RuntimeException("Error: No function name "+ gid); }
        if(gidManager.getFunc(gid).definition==null) {throw new RuntimeException("Error: function "+gid+" has no definition"); }
        if(!(gidManager.getFunc(gid).definition instanceof FunctionDefContext)) {throw new RuntimeException("Error: function "+gid+" has no body?!?"); }
        dependentSubModules.clear(); // clear result out 
        Utility.println("Start Translating Function: "+ gid);
        
        FunctionDefContext ctx=(FunctionDefContext)gidManager.getFunc(gid).definition; // you should not translate type of Minispec static synonym at all
        List<ParamFormalContext> paramFormalNodes = ctx.functionId().paramFormals()!=null?ctx.functionId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        if(isParametric) {
            gidManager.enterImmutableScope();
            matchParameter(gid.params,paramFormalNodes);
        }
        gidManager.enterMutableScope();
        List<Variable> argVars = extractArgFormalFunc(ctx.argFormals()!=null? ctx.argFormals().argFormal(): List.of());
        
        
        String out = "(*noinline*)\n"
                + "function "+ visit(ctx.type())+" "+ gid.toStringEscapeParametric()+"(";
        List<String> argVarString = new ArrayList<>();
        for(Variable var:argVars) {
            gidManager.defineVar(var.name, var);
            argVarString.add(var.typeId.toProperTypeString(gidManager)+" "+var.name);
        }
        
        out+=String.join(", ", argVarString)+")";
        // case expression function
        if(ctx.expression()!=null) {
            out+=" = ";
            out+= ExpressionEvaluator.evaluate(ctx.expression(),gidManager);
            out+=";\n";
        } else {
            // nrmal function def
            out+=";\n";
            String code="";
            code+= visitJoin(ctx.stmt(), "");
            out+=Utility.addPrefix(code, INDENT);
            out+="endfunction\n";
        }

        gidManager.exitScope();
        if(isParametric) {
            gidManager.exitScope(); // SCOPE OFparameter
        }
        return out;
    }
    
    /**
     * Translate begin end block context (will be used by expression evaluator)
     * @param ctx
     * @return code for the begin end
     */
    public String translateBeginEndBlock(BeginEndBlockContext ctx) {

        dependentSubModules.clear(); // clear result out 
        return visit(ctx);
    }
    /**
     * Start translating te type "type" which should be registered in the gidManager
     * @param type
     * @return the code of that type
     */
    public String translateType(Type type) {
        return translateType(type.typeId);
    }
    /**
     * Start translating Variable "var" which shold be registered in the gidManager in the outer most scope
     * @param var
     * @return the code of declaration of that var (in the outermost scope)
     */
    public String translateVar(Variable var) {
         dependentSubModules.clear(); // clear result out 
         Utility.println("Start Translating Outermost Variable Declaration: "+ var.name);
         GidExtracter.tryRegisterType(var.typeId, gidManager);
         return var.typeId.toProperTypeString(gidManager)+" "+var.name+"="+var.value+";\n";
    }
    /**
     * Start translating Func func which should be registered in the gidManager
     * @param func
     * @return the code of declaration of that func (in the outermost scope)
     */
    public String translateFunc(Func func) {
        return translateFunc(func.funcId);
    }
    /**
     * create a module for toplevel
     * @param gid
     * @return code ofr top level module representing that func
     */
    public String translateToToplevelFunc(GeneralizedIdentifier gid) {
        assert gidManager.getFunc(gid)!=null && gidManager.getFunc(gid).definition instanceof FunctionDefContext: "Undefined function "+gid;
        dependentSubModules.clear(); // clear result out 
        Utility.println("Start creating toplevel Function: "+ gid);
        FunctionDefContext ctx = (FunctionDefContext)(gidManager.getFunc(gid).definition);
        List<ParamFormalContext> paramFormalNodes = ctx.functionId().paramFormals()!=null?ctx.functionId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        if(isParametric) {
            gidManager.enterImmutableScope();
            matchParameter(gid.params,paramFormalNodes);
        }
        gidManager.enterMutableScope();
        List<Variable> argVars = extractArgFormalFunc(ctx.argFormals()!=null? ctx.argFormals().argFormal(): List.of());
        List<String> argVarString = new ArrayList<>();
        List<String> argString = new ArrayList<>();
        for(Variable var:argVars) {
            gidManager.defineVar(var.name, var);
            argVarString.add(var.typeId.toProperTypeString(gidManager)+" "+var.name);
            argString.add(var.name);
        }
        
       
        String interfaceCode="interface TopLevel;\n";
        interfaceCode+=Utility.addPrefix("method "+visit(ctx.type())+ " "+ gid.toStringEscapeParametric()+("("+String.join(", ", argVarString)+")")+";\n", INDENT);
        interfaceCode+="endinterface\n";
        String out = "(*synthesize*)\nmodule mkTopLevel(TopLevel);\n";
        String methodDef="method "+visit(ctx.type())+ " "+gid.toStringEscapeParametric()+("("+String.join(", ", argVarString)+")")+" = ";
        methodDef += gid.toStringEscapeParametric()+"("+ String.join(", ", argString)+");\n";
        out+=Utility.addPrefix(methodDef, INDENT)+"endmodule\n";
        gidManager.exitScope();
        return interfaceCode+out;
        
    }
    /**
     * create a module for toplevel
     * @param gid
     * @return code ofr top level module representing that func
     */
    public String translateToToplevelFunc(Func func) {
        return translateToToplevelFunc(func.funcId);
    }
    
    /**
     * Translate the bsv import into nromal bluespec import
     * @param ctx
     * @return the code of bluespec import
     */
    public String translateBSVImport(BsvImportDeclContext ctx) {

        dependentSubModules.clear(); // clear result out 
        String out="";

        Utility.println("Start Translating BSV Imports ");
        for(int j=0;j<ctx.upperCaseIdentifier().size();j++)
            out+="import "+ctx.upperCaseIdentifier(j).getText()+"::*;\n";
        return out;
        
    }
    /**
     * try translate the STruct type def (take care of parameterization as well)
     * @param gid
     * @param ctx
     * @return string for transaltion
     */
    public String translateTypeDefStruct(GeneralizedIdentifier gid,TypeDefStructContext ctx) {
        dependentSubModules.clear(); // clear result out 
        List<ParamFormalContext> paramFormalNodes = ctx.typeId().paramFormals()!=null?ctx.typeId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        gidManager.enterImmutableScope();
        if(isParametric) {
            matchParameter(gid.params,paramFormalNodes);
        }
        String out = "typedef struct {\n";
        String members = visitJoin(ctx.structMember(), "\n")+"\n";
        out+=Utility.addPrefix(members, INDENT);
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
        dependentSubModules.clear(); // clear result out 
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
        dependentSubModules.clear(); // clear result out 
        List<ParamFormalContext> paramFormalNodes = ctx.moduleId().paramFormals()!=null?ctx.moduleId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormalNodes);
        // if the definition is parametric but the gid is concrete
        // It measn we are trying to synthesize gid Tyep by matching ctx parameter
        gidManager.enterImmutableScope();
        if(isParametric) {
            matchParameter(gid.params,paramFormalNodes);
        }
        String properInterfaceName = gid.toStringEscapeParametric();
        String interfaceCode = "interface "+properInterfaceName+";\n";
        // WE can only syntheszie module without Module argument
        String out = (ctx.argFormals()==null?"(*synthesize*)\n":"")+"module "+ gid.toProperModuleString(gidManager);
        List<Variable> argList = extractArgFormalFunc(ctx.argFormals()!=null? ctx.argFormals().argFormal():List.of());
        argList.forEach((var)->{var.isModule=true;gidManager.defineVar(var.name, var);}); // tag that each of arguments mst be Value or module
        String moduleMethodCode="";
        if(ctx.argFormals()!=null) out+="#"+visit(ctx.argFormals());
        out+="("+properInterfaceName+");\n";
        /*
         * Setting up part:
         * First register all variable
         * then
         *  read all input /submodue / method/ VarDecl
         *  - submodule -> register as submodule (We will report dependent Submodule +translate now)
         *  - input -> convert to Action method and put inside interface + translate now
         *  - method -> put inside interface + translate later
         *  - VarDecl -> just declare + translate now
         * */
        
        //(so that when interact with them) we need change syntax
        for(ModuleStmtContext mctx: ctx.moduleStmt()) {
            if(mctx.submoduleDecl()!=null) {
                //register type
                GeneralizedIdentifier typeId = GidExtracter.extractGidAndRegisterType(mctx.submoduleDecl().type(), gidManager);
                String varName = mctx.submoduleDecl().name.getText();
                Variable var = new Variable(typeId,varName);
                var.isModule=true;
                gidManager.defineVar(varName, var);
                List<String> args = mctx.submoduleDecl().args()!=null?
                        mctx.submoduleDecl().args().arg().stream().map((e)->
                            (ExpressionEvaluator.evaluate(e.expression(), gidManager)).toString())
                            .collect(Collectors.toList())
                        :List.of();
                String submoduleDecl=typeId.toProperTypeString(gidManager)+" "+varName+" <- "+typeId.toModuleInitializer(args, gidManager)+";\n";
                this.dependentSubModules.add(typeId);
                out+=Utility.addPrefix(submoduleDecl, INDENT);
                
            } else if(mctx.inputDef()!=null) {
                String action="method Action ";
                String wireName =mctx.inputDef().name.getText();
                String actionName = inputNameToActionName(wireName);
                GeneralizedIdentifier typeId = GidExtracter.extractGidAndRegisterType(mctx.inputDef().type(), gidManager);
                String arg = "("+typeId.toProperTypeString(gidManager) +" value)";
                interfaceCode+=INDENT+action+actionName+arg+";\n";
                // will instead of visit node just translate it here since I'm too lazy to use gidExtractor withihn the node again
                String wireDecl = "Wire#("+typeId.toProperTypeString(gidManager)+") "+ wireName +" <-"
                        + (mctx.inputDef().defaultVal!=null? "mkDWire("+ExpressionEvaluator.evaluate(mctx.inputDef().defaultVal, gidManager)+")":"mkBypassWire")
                        +";\n";
                String methodDef = action+actionName+arg+";\n"+INDENT+wireName+"<= value;\nendmethod\n";
                out+=Utility.addPrefix(wireDecl, INDENT);
                moduleMethodCode+=Utility.addPrefix(methodDef, INDENT); // to put method Code at the end of the block
                gidManager.defineVar(wireName, new Variable(typeId,wireName));
                
            } else if (mctx.methodDef()!=null) { 
                GeneralizedIdentifier retTypeId = GidExtracter.extractGidAndRegisterType(mctx.methodDef().type(), gidManager);
                String name = mctx.methodDef().name.getText();
                String methodDef = "method "+retTypeId.toProperTypeString(gidManager)+" "+name+(mctx.methodDef().argFormals()!=null?visit(mctx.methodDef().argFormals()):"")+";";
                interfaceCode+=INDENT+methodDef+"\n";
            } else if (mctx.stmt()!=null) {
                StmtContext sctx = mctx.stmt();
                assert sctx.varDecl()!=null: "THe statement inside module must be variable declaration";
                String varDeclCode=visit(sctx.varDecl()); // just to register variable first
                out+=Utility.addPrefix(varDeclCode, INDENT); // and also add to module translated text by the way since we dont want to
            }
        }
        // Then visit rules and elaborate methods
        for(ModuleStmtContext mctx: ctx.moduleStmt()) {
            if (mctx.methodDef()!=null) { 
                GeneralizedIdentifier retTypeId = GidExtracter.extractGidAndRegisterType(mctx.methodDef().type(), gidManager);
                String name = mctx.methodDef().name.getText();
                String methodDefCode = "method "+retTypeId.toProperTypeString(gidManager)+" "+name+(mctx.methodDef().argFormals()!=null?visit(mctx.methodDef().argFormals()):"");
                
                List<Variable> methodArgList = extractArgFormalFunc(mctx.methodDef().argFormals()!=null?mctx.methodDef().argFormals().argFormal():List.of());
                
                gidManager.enterMethodScope();
                methodArgList.forEach((v)->gidManager.defineVar(v.name, v)); // define Var accordingly
                if(mctx.methodDef().expression()!=null) {
                    methodDefCode +=" = "+ExpressionEvaluator.evaluate(mctx.methodDef().expression(),gidManager)+";\n";
                } else {
                    methodDefCode+= ";\n";
                    String code="";
                    for(StmtContext sctx:mctx.methodDef().stmt()) {
                        //Utility.println(sctx.getText());
                        code+=visit(sctx);
                    }
                    methodDefCode+=Utility.addPrefix(code, INDENT)+"endmethod\n";
                }
                gidManager.exitScope();
                moduleMethodCode+=Utility.addPrefix(methodDefCode,INDENT);
            } else if (mctx.ruleDef()!=null) {
                String ruleDef="(* no_implicit_conditions, fire_when_enabled *)\n";
                gidManager.enterMutableScope();
                ruleDef+="rule "+mctx.ruleDef().name.getText()+";\n";
                for(StmtContext sctx: mctx.ruleDef().stmt())
                    ruleDef+=Utility.addPrefix(visit(sctx), INDENT);
                ruleDef+="endrule\n";
                out+=Utility.addPrefix(ruleDef, INDENT);
                gidManager.exitScope();
            }
        }

        out+=moduleMethodCode; // put method of wire input at the end
        interfaceCode+="endinterface\n";
        out+="endmodule\n";
        gidManager.exitScope();
        return interfaceCode+out;
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
                Utility.println("Match parametric "+varName+"="+var.value);
                gidManager.defineVar(varName,var);
            } else {
                //type
                GeneralizedIdentifier typeGid = identifier(pf.upperCaseIdentifier().getText());
                Type type = new Type(typeGid,p.gid);

                Utility.println("Match parametric "+typeGid+"="+p.gid);
                gidManager.defineType(typeGid, type); // Static elaboration Type def are definied using Tyep instead of using parse tree
            }
        }
    }
    /**
     * Define variable into gidManager 
     * @param argFormals
     */
    private List<Variable> extractArgFormalFunc( List<ArgFormalContext> argFormals ) {
        List<Variable> out = new ArrayList<>();
        for(int i=0;i< argFormals.size();i++) {
            GeneralizedIdentifier typeId = GidExtracter.extractGidAndRegisterType(argFormals.get(i).type(), gidManager);
            String varName = argFormals.get(i).argName.getText();
            Variable var = new Variable(typeId,varName);
            out.add(var);
        }
        return out;
    }
    
    /**
     * Convert submodule input wire name to Action name
     * @param inputName
     * @return action name of that input
     */
    private static String inputNameToActionName(String inputName) { return inputName+"___input";}
}


