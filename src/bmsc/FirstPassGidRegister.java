package bmsc;

import static bmsc.GeneralizedIdentifier.ENUMVALUE;
import static bmsc.GeneralizedIdentifier.identifier;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import api.antlr4.MinispecBaseVisitor;
import api.antlr4.MinispecParser.FunctionDefContext;
import api.antlr4.MinispecParser.LetBindingContext;
import api.antlr4.MinispecParser.ModuleDefContext;
import api.antlr4.MinispecParser.PackageDefContext;
import api.antlr4.MinispecParser.ParamFormalContext;
import api.antlr4.MinispecParser.TypeDefEnumContext;
import api.antlr4.MinispecParser.TypeDefEnumElementContext;
import api.antlr4.MinispecParser.TypeDefStructContext;
import api.antlr4.MinispecParser.TypeDefSynonymContext;
import api.antlr4.MinispecParser.VarBindingContext;
import api.antlr4.MinispecParser.VarInitContext;



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
public class FirstPassGidRegister extends MinispecBaseVisitor<Void>{
    
    public final GeneralizedIdentifierManager gidManager;

    private final List<Token> tokenList ;
    public FirstPassGidRegister(GeneralizedIdentifierManager gidManager, List<Token> tokenList) {
        this.gidManager=gidManager;
        this.tokenList=tokenList;
    }
    
    @Override public Void visitTypeDefSynonym (TypeDefSynonymContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.typeId().paramFormals()!=null)? ctx.typeId().paramFormals().paramFormal():List.of();
        
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        if(isParametric) {
            String name =ctx.typeId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            Utility.println("Register Parametric     " +name);
            gidManager.defineParametric(name, definition);
        } else {
            GeneralizedIdentifier newGid = GidExtracter.extractGidFormal(ctx.typeId().name.getText(),paramFormals, gidManager);
            Type definition = new Type(newGid,ctx); // reserve definition of type GID for static elaboration type unrolling
            Utility.println("Register Type (Syn)    " +newGid);
            gidManager.defineType(newGid, definition);
        }
        return null;
    }
    @Override public Void visitTypeDefEnumElement(TypeDefEnumElementContext ctx) {
        gidManager.defineVar(ctx.upperCaseIdentifier().getText(), new Variable(ENUMVALUE, ctx.upperCaseIdentifier().getText()));
        return null;
    }
    @Override public Void visitTypeDefEnum(TypeDefEnumContext ctx) {
        String name = ctx.upperCaseIdentifier().getText();
        GeneralizedIdentifier newGid= identifier(name);
        Type newEnumType = new Type(newGid, ctx);
        Utility.println("Register Type (Enum)   " +newGid);
        gidManager.defineType(newGid, newEnumType);
        ctx.typeDefEnumElement().forEach((s)->visit(s));
        return null;
    }
    
    @Override public Void visitTypeDefStruct(TypeDefStructContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.typeId().paramFormals()!=null)? ctx.typeId().paramFormals().paramFormal():List.of();
        
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        if(isParametric) {
            String name =ctx.typeId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            Utility.println("Register Parametric    " +name);
            gidManager.defineParametric(name, definition);
        } else {
            
            GeneralizedIdentifier newGid= GidExtracter.extractGidFormal(ctx.typeId().name.getText(),paramFormals, gidManager);
            Type definition = new Type(newGid,ctx);
            Utility.println("Register Type (Struct) " +newGid);
            gidManager.defineType(newGid, definition);
        }
        return null;
    }
    
    @Override public Void visitModuleDef(ModuleDefContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.moduleId().paramFormals()!=null)? ctx.moduleId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        boolean toSynth = !hasNoSynthAttr(ctx);
        if(isParametric) {
            String name = ctx.moduleId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            definition.toSynth=toSynth;
            Utility.println("Register Parametric    " +name + (toSynth?"":" no Synth"));
            gidManager.defineParametric(name, definition);
        } else {
            GeneralizedIdentifier newGid= GidExtracter.extractGidFormal(ctx.moduleId().name.getText(), paramFormals, gidManager);
            Type definition = new Type(newGid,ctx);
            definition.toSynth=toSynth;
            Utility.println("Register Type (Module) " +newGid+ (toSynth?"":" no Synth"));
            gidManager.defineType(newGid, definition);
        }
        return null;
    }
    
    @Override public Void visitFunctionDef(FunctionDefContext ctx) {
        List<ParamFormalContext> paramFormals= (ctx.functionId().paramFormals()!=null)? ctx.functionId().paramFormals().paramFormal():List.of();
        boolean isParametric = GidExtracter.isParametric(paramFormals);
        boolean toSynth = !hasNoSynthAttr(ctx);
        if(isParametric) {
            String name = ctx.functionId().name.getText();
            Parametric definition= new Parametric(name,ctx);
            definition.toSynth=toSynth;
            Utility.println("Register Parametric    " +name+ (toSynth?"":" no Synth"));
            gidManager.defineParametric(name, definition);
        } else {
            GeneralizedIdentifier newGid= GidExtracter.extractGidFormal(ctx.functionId().name.getText(), paramFormals, gidManager);
            Func definition = new Func(newGid,ctx);
            definition.toSynth=toSynth;
            Utility.println("Register Type (Func)   " +newGid+ (toSynth?"":" no Synth"));
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
                    var.value=Utility.exprToString(var.value);
                Utility.println("Register Variable      "+typeId.toString()+" "+var.name+" = "+var.value);
                gidManager.defineVar(var.name, var);
            }
       // }
        return null;
    }
    
    @Override public Void visitLetBinding ( LetBindingContext ctx) {
        throw new RuntimeException("Warning: \'let\' is forbidden in top-level context");
    }
    
    public boolean hasNoSynthAttr(ParserRuleContext ctx) {
        String toFind = "bmsc_pragma:nosynth";
        for(int i=ctx.getSourceInterval().a;i<=ctx.getSourceInterval().b;i++) {
            if(tokenList.get(i).getText().contains(toFind)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Run first pass gid register on the whole package Def context
     * @param parsedFile of the file to run first pass
     * @param gidManager to register types and names
     */
    public static void firstPass(ParsedFile parsedFile, GeneralizedIdentifierManager gidManager) {
        FirstPassGidRegister visitor = new FirstPassGidRegister(gidManager, parsedFile.parserResult.tokenList());
        visitor.visit((PackageDefContext)parsedFile.parserResult.parseTree());
        
    }
}