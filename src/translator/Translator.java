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
        
        private final Map<Integer,String> toInsertAt = new HashMap<>();
        private final String prefix = "Boom Debug:";
        private final int tabSize = 4;
        private final String toTranslateModuleName;
        
        
        /**
         * Initialize the current aimed to translate module name
         * @param moduleName to be translated
         */
        public TranslateVisitor(String moduleName) {
            this.toTranslateModuleName=moduleName;
        }
        
        
        /**********************
         *  Helper Part
         **********************/
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
        /*
         * Add watch statement in the header of the rule in the toInsert At
         * @param position posititon to add
         */
        private void addWatcher(int position) {
            String displayStmt="\n$display(\"--------------------\");\n"; // add display for dash lines
            for(String regName : allRegNames) {
                displayStmt+=displayStmt(showStmt("Reg", regName))+"\n";
            }
            for(String inputName : allInputNames) {

                displayStmt+=displayStmt(showStmt("Input", inputName))+"\n";
            }
            toInsertAt.put(position, displayStmt);
            System.out.println("displayStmt: "+ displayStmt);
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
        @Override public Void visitModuleDef(MinispecParser.ModuleDefContext ctx) {
            assert ctx.moduleId()!=null : "module with no Identifier ?!?!?";
            assert ctx.moduleId().name!=null : "module with no name!?!?!";
            String moduleName = ctx.moduleId().name.getText();
            System.out.println("Found module Name : "+moduleName);
            // Find all register + submodule first
            for(MinispecParser.ModuleStmtContext childctx: ctx.moduleStmt()) {

                // Assumption checking : only Global constant declaration is allowed in module level Statement
                if(childctx.stmt()!=null) assert childctx.stmt().varDecl()!=null : "Expect the module Level Statement to be global Constant declaration only";
                if(childctx.submoduleDecl()!=null) visit(childctx.submoduleDecl());
                if(childctx.inputDef()!=null) visit(childctx.inputDef());
               
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
        @Override public Void visitRuleDef(MinispecParser.RuleDefContext ctx) { 
            String ruleName = ctx.name.getText();
            System.out.println("Found rule : "+ruleName + "@ "+ ctx.getSourceInterval());
            final int ruleDeclLength=3;
            final int toInsert = ctx.getSourceInterval().a+ruleDeclLength;
            addWatcher(toInsert);
            
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
            TranslateVisitor translator = new TranslateVisitor("");
            translator.visit(p.parseTree());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
