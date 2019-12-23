package translator;

import java.io.*;
import java.util.*;

import javax.swing.text.DefaultEditorKit.InsertContentAction;

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
        private final List<String> allSubModNames = new ArrayList<>();
        private final List<String> allInputNames = new ArrayList<>();
        private final List<Token> tokenList;
        
        private final Map<Integer,String> toInsertAt = new HashMap<>();
        private final String prefix = "Boom Debug:";
        private final int tabSize = 4;
        private final String toTranslateModuleId;
        private int currentScopeLevel = -1; // level 0 a rule scope
        
        /**
         * Initialize the current aimed to translate module name
         * @param moduleId to be translated or empty if want to translate all
         * @param tokenList the List of Lexer's token 
         */
        public TranslateVisitor(String moduleId, List<Token> tokenLists) {
            this.toTranslateModuleId=moduleId;
            this.tokenList=List.copyOf(tokenLists);
        }
        /**
         * 
         * @return a defensive copy of insertion map
         */
        public Map<Integer,String> getInsertionMap(){ return Map.copyOf(toInsertAt);}
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
            toInsertAt.put(index, currentToInsert+s);
        }
        /**
         * 
         * @param s string with quotes to add into display statmeent
         * @return a String of display statement
         */
        private String displayStmt(String s) { return "$display(\""+prefix+"\", "+s+");";}
        
        /**
         * Get the indentation before or at  the token position position
         * @param pos the token index
         * @return the indentation string
         */
        private String getIndentation(int pos) {
            int index=pos;
            while(index>=0 && !tokenList.get(index).getText().contains("\n")) index--;
            if(index==-1) // start of file and no white space
                return "";
            String whitespace = tokenList.get(index).getText();
            int newlineIndex = whitespace.lastIndexOf("\n");
            return whitespace.substring(newlineIndex+1);
        }
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
         * @param ctx the LValue context
         * @return the string to show the the l value into display
         */
        private static String showLhsStmt(MinispecParser.LvalueContext ctx) {
            List<String> parts = new ArrayList<>();
            //String lhs="\""; // will always have trailing " until the loop ends
            MinispecParser.LvalueContext currentNode = ctx; //  traverse down lvalue chain
            boolean innermostLvalue=false;
            do{
                boolean isSimple = currentNode instanceof MinispecParser.SimpleLvalueContext;
                boolean isMember = currentNode instanceof MinispecParser.MemberLvalueContext;
                boolean isIndex = currentNode instanceof MinispecParser.IndexLvalueContext;
                boolean isSlice = currentNode instanceof MinispecParser.SliceLvalueContext;
                if(isSimple) {
                    MinispecParser.SimpleLvalueContext cur = (MinispecParser.SimpleLvalueContext) currentNode;
                    innermostLvalue=true;
                    parts.add(0,  "\""+cur.lowerCaseIdentifier().getText()+"\"");
                    
                } else if(isMember) {
                    MinispecParser.MemberLvalueContext cur = (MinispecParser.MemberLvalueContext) currentNode;
                    parts.add(0,  "\"."+cur.lowerCaseIdentifier().getText()+"\"");
                    currentNode=cur.lvalue();
                }else if(isIndex) {
                    MinispecParser.IndexLvalueContext cur = (MinispecParser.IndexLvalueContext) currentNode;

                    parts.add(0,"\"[%0d]\","+cur.expression().getText());
                    //lhs="[%0d]\","+cur.expression().getText()+",\""+lhs;
                    currentNode=cur.lvalue();
                }
                else if(isSlice) {
                    MinispecParser.SliceLvalueContext cur = (MinispecParser.SliceLvalueContext) currentNode;

                    parts.add(0,"\"[%0d:%0d]\","+cur.expression(0).getText()+","+cur.expression(1).getText());
                    //lhs="[%0d:%0d]\","+cur.expression(0).getText()+","+cur.expression(1).getText()+",\""+lhs;
                    currentNode=cur.lvalue();
                }
                else {throw new RuntimeException("Should not reach here");}
            } while (!innermostLvalue);
            String lhs = parts.stream().reduce((a,b)->{ 
                if(a.endsWith("\"") && b.startsWith("\"")) 
                    return a.substring(0, a.length()-1)+b.substring(1);
                return a+b;}).orElse("\"\"");
            return lhs;
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
        private String setVarStmt(String tag, String varName, String expr, int row, int col) { 
            final int totLength = tag.length()+varName.length();
            final int toFill = tabSize-1-totLength%tabSize;
            String spaces =""; for(int i=0;i<toFill; i++) spaces+=" ";
            return "\""+tag+": "+varName+spaces+" =  \", fshow("+expr+") , \" @ "+row+","+col+"\"";
        }
        /**
         * 
         * @param tag tag of what to descrbie s
         * @param lhs the thing to be inserted between
         * @param expr the String of expressino being written into varName
         * @param row row number
         * @param col col number
         * @return the '"tag ",lhs," =", fshow(expr)' string (with no tab spacing ( since the spacing is dynamic)
         */
        private static String setLhsStmt(String tag, String lhs, String expr, int row, int col) { 
            return "\""+tag+": \", "+lhs+", \" =  \", fshow("+expr+") , \" @ "+row+","+col+"\"";
        }
        
        /**
         * 
         * @param tag tag of what to descrbie s
         * @param scopeLevel the level of scope
         * @param row row number
         * @param col col number
         * @return the '"tag scope = scopeLevel ' string
         */
        private String scopeStmt(String tag, int scopeLevel, int row, int col) { 
            String scope = "Scope Level";
            final int totLength = tag.length()+scope.length();
            final int toFill = tabSize-1-totLength%tabSize;
            String spaces =""; for(int i=0;i<toFill; i++) spaces+=" ";
            return "\""+tag+": "+scope+spaces+" =  "+scopeLevel+" @ "+row+","+col+"\"";
        }
        /**
         * 
         * @param ctx a type in minispec
         * @return whether such type is (Register or Vector of register) or not
         */
        private static boolean isVectorOrSingleRegister(MinispecParser.TypeContext ctx) {
            String outMostType = ctx.name.getText();
            if(outMostType.equals("Reg") ||  outMostType.equals("RegU")) {
                return true;
            }
            else if (outMostType.equals("Vector")) {
                assert ctx.params()!=null : "Vector must have parameter";
                assert ctx.params().param().size()==2 : "Vector must have exactly 2 parameter";
                assert ctx.params().param(0).expression()!=null : "Vector must have first argument being int";
                assert ctx.params().param(1).type()!=null : "Vector must have second argument being type";
                return isVectorOrSingleRegister(ctx.params().param(1).type());
            }
            return false;
        }
        /**
         *  add display statement of dashes at token index *position*
         *  
         *  This is inserted right after rule name so need \n infront
         * @param position to add  (intended to be index of a white space that possible has \n right after rule decl)
         */
        private void addDisplayDash(int position) {
            String stmt = "$display(\"--------------------\");";
            insertToCode(position, "\n"+getIndentation(position)+stmt);            
        }
        /**
         * add watch statement in the header of the rule in the toInsert At
         * 
         *  This is inserted right after rule name so need \n infront
         * @param position position to add (intended to be index of a white space that possible has \n right after rule decl)
         */
        private void addDisplayWatcher(int position) {
            String indent = getIndentation(position);
            for(String regName : allSubModNames) {
                insertToCode(position,"\n"+indent+displayStmt(showStmt("Modl ", regName)));
            }
            for(String inputName : allInputNames) {
                insertToCode(position,"\n"+indent+displayStmt(showStmt("Input", inputName)));
            }
            //System.out.println(toInsertAt.get(position));
        }

        /**
         *  add watcher on ctx Variable initialization
         *  
         *  This is inserted right before init 
         * @param position position to add
         * @param ctx the ParseTree Of the Initialization
         */
        private void addDisplayVarInit(int position, MinispecParser.VarInitContext ctx) {
            String varName=ctx.var.getText();
            String expr = (ctx.rhs==null)?"":ctx.rhs.getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setVarStmt("Init ",varName,expr,lineNo,colNo))+" ");

        }
        
        /**
         *  add watcher on ctx Variable initialization using let
         *  
         *  This is inserted right before let so need \n at back
         * @param position position to add
         * @param ctx the ParseTree Of the Initialization by let
         */
        private void addDisplayVarInitLet(int position, MinispecParser.LetBindingContext ctx) {
            String varName=ctx.lowerCaseIdentifier(0).getText();
            String expr = (ctx.rhs==null)?"\"\"":ctx.rhs.getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setVarStmt("Init ",varName,expr,lineNo,colNo))+" ");

        }
        
        /**
         *  add watcher on ctx Variable assignment
         *  
         *  This is inserted right before assign so need \n at back
         * @param position position to add
         * @param ctx the ParseTree Of the Assignment (must only have 1 lvalue ( simple assignment not concat assignment))
         */
        private void addDisplayVarAssign(int position, MinispecParser.VarAssignContext ctx) {
            String lhs = showLhsStmt(ctx.lvalue(0));
            String expr = (ctx.expression()==null)?"\"\"":ctx.expression().getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setLhsStmt("Set  ",lhs,expr,lineNo,colNo))+" ");

        }
        /**
         *  add watcher on ctx regsiter write
         *  
         *  This is inserted right before write so need \n at back
         * @param position position to add
         * @param ctx the ParseTree Of the register write
         */
        private void addDisplayRegWrite(int position, MinispecParser.RegWriteContext ctx) {
            String lhs = showLhsStmt(ctx.lvalue());
            String expr = ctx.expression().getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setLhsStmt("Write",lhs,expr,lineNo,colNo))+" ");

        }
        /**
         *  add watcher on ctx If Else branching
         *  
         *  This is inserted right before if so need \n at back
         * @param position position to add
         * @param ctx the ParseTree Of If Else
         */
        private void addDisplayIf(int position, MinispecParser.IfStmtContext ctx) {
            
            String expr = ctx.expression().getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setVarStmt("If   ","Taken",expr,lineNo,colNo))+"\n" + getIndentation(position));

        }
        /**
         *  add watcher on ctx CaseStmt branching
         *  
         *  This is inserted right before if so need \n at back
         * @param position position to add
         * @param ctx the ParseTree Of CaseStmt
         */
        private void addDisplayCaseStmt(int position, MinispecParser.CaseStmtContext ctx) {
            
            String expr = ctx.expression().getText();
            final int lineNo = tokenList.get(ctx.getSourceInterval().a).getLine(); // the line of the left most token
            final int colNo = tokenList.get(ctx.getSourceInterval().a).getCharPositionInLine()+1; // get the column
            insertToCode(position, displayStmt(setVarStmt("Case ","Choice",expr,lineNo,colNo))+"\n"+ getIndentation(position));

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
         *  get All text including white space in the Interval x
         * @param x the interval
         * @return all text in te interval x
         */
        private String getAllText(Interval x) {
            if(x.equals(Interval.INVALID)) return "";
            final int a=x.a,b=x.b;
            String out="";
            for(int i=a;i<=b;i++) out+= tokenList.get(i).getText();
            return out;
        }
        /**
         * Read everything in the module and add display statement into its rule accordingly
         */
        @Override public Void visitModuleDef(MinispecParser.ModuleDefContext ctx) {
            assert ctx.moduleId()!=null : "module with no Identifier ?!?!?";
            assert ctx.moduleId().name!=null : "module with no name!?!?!";
            String moduleName = getAllText(ctx.moduleId().getSourceInterval());
            System.out.println("Found module Id : "+moduleName);
            if(!(toTranslateModuleId.isEmpty() || moduleName.equals(toTranslateModuleId))) return null; // skip if not the intended module name
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
            
            // Clear before go out of module
            allSubModNames.clear();
            allInputNames.clear();
            return null;
        }
        @Override public Void visitModuleStmt(MinispecParser.ModuleStmtContext ctx) { 
            throw new RuntimeException("should not reach here");
        }
        
        /**
         * Will Add Register to the watchlist if the submodule is a register
         */
        @Override public Void visitSubmoduleDecl(MinispecParser.SubmoduleDeclContext ctx) {
            if(isVectorOrSingleRegister(ctx.type())) {
                String regName = ctx.name.getText();
                System.out.println("Found known submodule: "+ctx.type().getText()+": "+regName);
                allSubModNames.add(regName);
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
            final int indexToInsert = ctx.getSourceInterval().a+ruleDeclLength+1;
            addDisplayDash(indexToInsert);
            addDisplayWatcher(indexToInsert);
            currentScopeLevel++;
            for(MinispecParser.StmtContext childctx: ctx.stmt()) {
                visit(childctx);
            }
            //System.out.println(toInsertAt);
            currentScopeLevel--;
            return null;
        }
        /**
         * Visit only when want to add display statement surrounding statement 
         */
        @Override public Void visitStmt(MinispecParser.StmtContext ctx) {
            if(ctx.expression()==null) // not expression
            visit(ctx.getChild(0));
            return null;
        }
        
        /******************
         * Adding Display statement for each statement
         ******************/
        // VarDecl
        @Override public Void visitVarBinding(MinispecParser.VarBindingContext ctx)  { 
            final int position = ctx.getSourceInterval().a;
            for(MinispecParser.VarInitContext childctx: ctx.varInit()) {
                addDisplayVarInit(position, childctx);
            }
            //System.out.println(toInsertAt.get(position));
            return null;
        }
        @Override public Void visitLetBinding(MinispecParser.LetBindingContext ctx) {
            final int position = ctx.getSourceInterval().a;
            assert ctx.lowerCaseIdentifier().size()==1: " more than 1 identifier being initialized Not quite sure how to deal";
            // TODO Deal with let {a,b} = syntax; ??!?
            addDisplayVarInitLet(position, ctx);
            //System.out.println(toInsertAt.get(position));
            return null;
        }
        // Varassign
        @Override public Void visitVarAssign(MinispecParser.VarAssignContext ctx) { 
            final int position = ctx.getSourceInterval().a;
            assert ctx.lvalue().size()==1 : "concat of assignment. Not quite sure what to do";
            addDisplayVarAssign(position, ctx);
            //System.out.println(toInsertAt.get(position));
            return null;
        }
        //regWrite 
        @Override public Void visitRegWrite(MinispecParser.RegWriteContext ctx) { 
            final int position = ctx.getSourceInterval().a;
            addDisplayRegWrite(position, ctx);
            //System.out.println(toInsertAt.get(position));
            return null;
        }
        // block
        @Override public Void visitBeginEndBlock(MinispecParser.BeginEndBlockContext ctx) { 
            final int startpos = ctx.getSourceInterval().a+1;
            final int endpos = ctx.getSourceInterval().b;
            final int startrow = tokenList.get(startpos).getLine();
            final int endrow = tokenList.get(endpos).getLine();
            final int startcol = tokenList.get(startpos).getCharPositionInLine()+1;
            final int endcol = tokenList.get(endpos).getCharPositionInLine()+1;
            
            currentScopeLevel++;
            insertToCode(startpos, "\n"+ getIndentation(startpos-1)+displayStmt(scopeStmt("Enter", currentScopeLevel, startrow, startcol)));
            for(MinispecParser.StmtContext childctx:ctx.stmt()) visit(childctx);
            insertToCode(endpos, displayStmt(scopeStmt("Exit ", currentScopeLevel, endrow, endcol))+"\n"+ getIndentation(endpos));
            currentScopeLevel--;
            return null;
        }
        // if
        @Override public Void visitIfStmt(MinispecParser.IfStmtContext ctx) { 
            final int position = ctx.getSourceInterval().a;
            addDisplayIf(position, ctx);
            addScopeIfSingleStmtThenVisit(ctx.stmt(0));
            if(ctx.stmt(1)!=null) 
                addScopeIfSingleStmtThenVisit(ctx.stmt(1));
            return null; 
        }
        //Case stmt
        @Override public Void visitCaseStmt(MinispecParser.CaseStmtContext ctx) {
            
            final int position = ctx.getSourceInterval().a;
            addDisplayCaseStmt(position, ctx);
            for(MinispecParser.CaseStmtItemContext childctx: ctx.caseStmtItem()) {
                addScopeIfSingleStmtThenVisit(childctx.stmt());
            }
            if(ctx.caseStmtDefaultItem()!=null) 
                addScopeIfSingleStmtThenVisit(ctx.caseStmtDefaultItem().stmt());
            return null; 
        }
        //for
        @Override public Void visitForStmt(MinispecParser.ForStmtContext ctx) {
            addScopeIfSingleStmtThenVisit(ctx.stmt());
            return null;
        }
        
        /**
         * Add begin and end in the output code if the current statement is not a begin end block (does not get count as scoping yet)
         * @param ctx the Statmenet context
         * @return Null 
         */
        public Void addScopeIfSingleStmtThenVisit(MinispecParser.StmtContext ctx) {
            boolean isBeginEndBlock = (ctx.beginEndBlock()!=null);
            final int startpos = ctx.getSourceInterval().a;
            final int endpos = ctx.getSourceInterval().b+1;
            if(!isBeginEndBlock) insertToCode(startpos, "begin\n" + getIndentation(startpos)); // inserted right before single statment
            visit(ctx);
            if(!isBeginEndBlock) insertToCode(endpos, "\n"+ getIndentation(endpos)+"end"); // inserted right after single stateement
            
            return null;
        }
        
    }
    
    /**
     * Output text to printwriter pw and also output to stdou if verbose is true
     * @param pw the prinWriter object ot output
     * @param text the text to print
     * @param verbose verbose?
     */
    private static void print(PrintWriter pw,String text, boolean verbose) {
        pw.print(text);
        pw.flush();
        if(verbose)
            System.out.print(text);
    }
    
    /**
     *  main method and its arg
     * @param args look At Help String pls
     */
    public static void main(String[] args) {
        System.out.println("Input argument : "+Arrays.toString(args));
        final String helpMessage = "usage: [--option] input_filename output_filename \n\n" + 
                "Result : Will Add debugging statement to input_filename and write it to output_filename \n\n"+
                "Available option:\n" + 
                "  --module <moduleid>                     only add Debugging tatement to a single module \n"+ 
                "                                          (default to adding all module in the inputFile)\n" + 
                "  -v,--verbose                            Show Log message out to stdout as well (default to false)\n" + 
                "  -h,--help                               Print help message\n" + 
                "\n\n"+
                "<moduleid> is the module name with the parameter that defines it\n";
        final String errorMessage = "Type -h,--help option to gain more info";
        String moduleId="";
        String fileName="";
        String outFileName="";
        boolean verbose=false;
        for(int i=0;i<args.length;i++)
        {
            String arg=args[i];
            //System.out.println(arg);
            if(arg.equals("--module")) {
                if(i+1>=args.length) {
                    System.out.println("no module Id after --module ??");
                    System.out.println(errorMessage);
                    return;
                }
                moduleId=args[i+1];
                i++;
                continue;
            } 
            else if (arg.equals("-v") || arg.equals("--verbose")) 
                verbose=true;
            else if (arg.equals("-h") || arg.equals("--help")) {
                System.out.println(helpMessage);
                return;
            }
            else {
                if(i!=args.length-2) {
                    System.out.println("Format Error");
                    System.out.println(errorMessage);
                    return;
                }
                fileName=args[i];
                outFileName=args[i+1];
                i++;
            }
            
        }

        if(args.length<2) {

            System.out.println("too few arguments");
            System.out.println(errorMessage);
            return;
        }
        try(PrintWriter pw = new PrintWriter(outFileName);) {
            ParserResult p = ParserResult.fromFileName(fileName);
            TranslateVisitor translator = new TranslateVisitor(moduleId,p.tokenList());
            translator.visit(p.parseTree());
            Map<Integer,String> insertionMap = translator.getInsertionMap();
            for(int i=0;i<p.tokenList().size();i++) {
                if(insertionMap.containsKey(i)) {
                    print(pw,insertionMap.get(i),verbose);
                }
                print(pw,p.tokenList().get(i).getText(),verbose);
            }
            if(insertionMap.containsKey(p.tokenList().size()))
                print(pw,insertionMap.get(p.tokenList().size()),verbose);
            
        } catch ( IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished translation");
    }
}
