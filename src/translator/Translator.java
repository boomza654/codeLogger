package translator;

import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.*;

import api.antlr4.*;
import parser.*;
/**
 * Transate old minispec code to code with more displays
 * @author boomza654
 *
 */
public class Translator {
    

    
    /**
     *  Visitor that translate piece of code as specified
     * @author boomza654
     *
     */
    public static class TranslateVisitor extends MinispecBaseVisitor<Void>{
        private final List<String> allRegNames = new ArrayList<>();
        private final List<String> allInputNames = new ArrayList<>();
        private final List<Token> tokenList;
        
        private final Map<Integer,String> toInsertAt = new HashMap<>();
        private final String prefix = "Boom Debug:";
        private final int tabSize = 4;
        private final String toTranslateModuleName;
        
        
        /**
         * Initialize the current aimed to translate module name
         * @param moduleName to be translated
         * @param tokenList the List of Lexer's token 
         */
        public TranslateVisitor(String moduleName, List<Token> tokenLists) {
            this.toTranslateModuleName=moduleName;
            this.tokenList=List.copyOf(tokenLists);
        }
        
        
        /**********************
         *  Helper Part
         **********************/
        /**
         * insert string s to code at token index *index* 
         * ( Note that this just track what you want to insert not really insert the thing)
         * @param index the index in Token list at which we want to insert in 
         * 
         * @param s the string to insert
         */
        private void insertToCode(int index, String s) {
            if(!toInsertAt.containsKey(index)) toInsertAt.put(index,"");
            String currentToInsert = toInsertAt.get(index);
            toInsertAt.put(index, currentToInsert+"\n"+s);
        }
        /**
         * 
         * @param s string with quotes to add into display statmeent
         * @return a String of display statement
         */
        private String displayStmt(String s) { return "$display(\""+prefix+"\", "+s+");";}
        /**
         * 
         * @param tag tag of what to descrbie s
         * @param s the name of reg / input wnat to show
         * @return the '"tag s", fshow(s)' string
         */
        private String showStmt(String tag, String s) { 
            final int totLength = tag.length()+s.length();
            final int toFill = tabSize-1-totLength%tabSize;
            String spaces =""; for(int i=0;i<toFill; i++) spaces+=" ";
            return "\""+tag+": "+s+spaces+" =  \", fshow("+s+")";
        }
        /**
         * 
         * @param tag tag of what to descrbie s
         * @param varName the name of var wnat to create a log
         * @param expr the String of expressino being written into varName
         * @param row row number
         * @param col col number
         * @return the '"tag varName =", fshow(expr)' string
         */
        private String setStmt(String tag, String varName, String expr, int row, int col) { 
            final int totLength = tag.length()+varName.length();
            final int toFill = tabSize-1-totLength%tabSize;
            String spaces =""; for(int i=0;i<toFill; i++) spaces+=" ";
            return "\""+tag+": "+varName+spaces+" =  \", fshow("+expr+") , \" @ "+row+","+col+"\"";
        }
        
        /**
         *  add display statement of dashes at token index *position*
         * @param position to add
         */
        private void addDisplayDash(int position) {
            String stmt = "$display(\"--------------------\");";
            insertToCode(position, stmt);            
        }
        /**
         * add watch statement in the header of the rule in the toInsert At
         * @param position position to add (which should be at the start of the rule when rule decl finish)
         */
        private void addDisplayWatcher(int position) {
            for(String regName : allRegNames) {
                insertToCode(position,displayStmt(showStmt("Reg", regName)));
            }
            for(String inputName : allInputNames) {
                insertToCode(position,displayStmt(showStmt("Input", inputName)));
            }
            //System.out.println(toInsertAt.get(position));
        }

        /**
         *  add watcher on ctx Variable initialization
         * @param position position to add
         * @param ctx the ParseTree Of the Initialization
         */
        private void addDisplayVarInit(int position, MinispecParser.VarInitContext ctx) {
            String varName=ctx.var.getText();
            String expr = (ctx.rhs==null)?"":ctx.rhs.getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setStmt("Init",varName,expr,lineNo,colNo)));

        }
        
        /**
         *  add watcher on ctx Variable initialization using let
         * @param position position to add
         * @param ctx the ParseTree Of the Initialization by let
         */
        private void addDisplayVarInitLet(int position, MinispecParser.LetBindingContext ctx) {
            String varName=ctx.lowerCaseIdentifier(0).getText();
            String expr = (ctx.rhs==null)?"":ctx.rhs.getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setStmt("Init",varName,expr,lineNo,colNo)));

        }
        
        /**********************
         *
         *  Visitor Part
         *  
         *********************/
        
        
        @Override public Void visitPackageDef(MinispecParser.PackageDefContext ctx) 
        { visitChildren(ctx); return null; }
        @Override public Void visitPackageStmt(MinispecParser.PackageStmtContext ctx) 
        { 
            if(ctx.moduleDef()!=null) visit(ctx.moduleDef());
            return null;
        }
        /**
         * Read everything in the module and add display statement into its rule accordingly
         */
        @Override public Void visitModuleDef(MinispecParser.ModuleDefContext ctx) {
            assert ctx.moduleId()!=null : "module with no Identifier ?!?!?";
            assert ctx.moduleId().name!=null : "module with no name!?!?!";
            String moduleName = ctx.moduleId().name.getText();
            System.out.println("Found module Name : "+moduleName);
            // Find all register + submodule first
            for(MinispecParser.ModuleStmtContext childctx: ctx.moduleStmt()) {

                // Assumption checking : only Global constant declaration is allowed in module level Statement
                if(childctx.stmt()!=null) assert childctx.stmt().varDecl()!=null : "Expect the module Level Statement to be global Constant declaration only";
                else if(childctx.submoduleDecl()!=null) visit(childctx.submoduleDecl());
                else if(childctx.inputDef()!=null) visit(childctx.inputDef());
               
            }
            // Then now we have all things to watch
            // Add display
            for(MinispecParser.ModuleStmtContext childctx: ctx.moduleStmt()) {
                if(childctx.ruleDef()!=null)
                    visit(childctx.ruleDef());
            }
            
            return null;
        }
        @Override public Void visitModuleStmt(MinispecParser.ModuleStmtContext ctx) { 
            throw new RuntimeException("should not reach here");
        }
        
        /**
         * Will Add Register to the watchlist if the submodule is a register
         */
        @Override public Void visitSubmoduleDecl(MinispecParser.SubmoduleDeclContext ctx) {
            if(ctx.type().name.getText().equals("Reg") ||  ctx.type().name.getText().equals("RegU")) {
                String regName = ctx.name.getText();
                System.out.println("Found known submodule : Register :"+regName);
                allRegNames.add(regName);
            }
            return null;
        }
        /**
         * Will Add input to the watchlist 
         */
        @Override public Void visitInputDef(MinispecParser.InputDefContext ctx) { 
            String inputName = ctx.name.getText();
            System.out.println("Found input : "+inputName);
            allInputNames.add(inputName);
            return null;
        }
        /**
         * Will add display statement of things in watchlist and every statmanet in the rule
         */
        @Override public Void visitRuleDef(MinispecParser.RuleDefContext ctx) { 
            String ruleName = ctx.name.getText();
            System.out.println("Found rule : "+ruleName + "@ "+ ctx.getSourceInterval());
            final int ruleDeclLength=3;
            final int indexToInsert = ctx.getSourceInterval().a+ruleDeclLength;
            addDisplayDash(indexToInsert);
            addDisplayWatcher(indexToInsert);
            for(MinispecParser.StmtContext childctx: ctx.stmt()) {
                visit(childctx);
            }
            return null;
        }
        /**
         * Visit only when want to add display statement surrounding statement 
         */
        @Override public Void visitStmt(MinispecParser.StmtContext ctx) {
            visit(ctx.getChild(0));
            return null;
        }
        
        /******************
         * Adding Display statement for each statement
         ******************/
        // VarDecl
        @Override public Void visitVarBinding(MinispecParser.VarBindingContext ctx)  { 
            final int position = ctx.getSourceInterval().b+1;
            for(MinispecParser.VarInitContext childctx: ctx.varInit()) {
                addDisplayVarInit(position, childctx);
            }
            //System.out.println(toInsertAt.get(position));
            return null;
        }
        @Override public Void visitLetBinding(MinispecParser.LetBindingContext ctx) {
            final int position = ctx.getSourceInterval().b+1;
            assert ctx.lowerCaseIdentifier().size()==1: " more than 1 identifier being initialized Not quite sure how to deal";
            // TODO Deal with let {a,b} = syntax; ??!?
            addDisplayVarInitLet(position, ctx);
            //System.out.println(toInsertAt.get(position));
            return null;
        }
    }
    /**
     *  main method and its arg
     * @param args sldkfjlskdjf
     */
    public static void main(String[] args) {
        final String fileName = "input_dir/Multipliers.ms";
        try {
            ParserResult p = ParserResult.fromFileName(fileName);
            TranslateVisitor translator = new TranslateVisitor("",p.tokenList());
            translator.visit(p.parseTree());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
