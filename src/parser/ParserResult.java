package parser;

import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import api.antlr4.*;
/**
 * Real parser to parse things from  antlr4 API
 * 
 * Stores Token List as well as the entire parse tree when instantiated
 * @author boomza654
 *
 */
public class ParserResult {
    private final List<Token> tokenList ;
    private final ParseTree parseTree;
    /**
     * Mode of parser ( select which mode to parse)
     * @author boomza654
     *
     */
    private static enum ParserMode{
        PACKAGE_DEF,
        TYPE,
        FUNC
    };
    /**
     * Factory method to parse things from from String
     * 
     * parseTree is not aliasing with token List anymore
     * 
     * @param str string to be parsed
     * @returna a result of Parsing with initialized parseTree of packageDef root
     */
    public static ParserResult fromString(String str) {
        CharStream charstream = CharStreams.fromString(str);
        return new ParserResult(charstream,ParserMode.PACKAGE_DEF);
    }
    /**
     * Factory method to parse things from file
     * 
     * parseTree is not aliasing with token List anymore
     * 
     * @param fileName name of file containing things to parse ( minispec)
     * @return a result of Parsing with initialized parseTree of packageDef root
     * @throws IOException if cannot open file
     */
    public static ParserResult fromFileName(String fileName) throws IOException {
        CharStream charstream = CharStreams.fromFileName(fileName);
        return new ParserResult(charstream,ParserMode.PACKAGE_DEF);
    }
    /**
     * Factory method to parse  typename from String
     * 
     * parseTree is not aliasing with token List anymore
     * 
     * @param str string to be parsed
     * @returna a result of Parsing with initialized parseTree of type root
     */
    public static ParserResult moduleFromString(String str) {
        CharStream charstream = CharStreams.fromString(str);
        ParserResult out= new ParserResult(charstream,ParserMode.TYPE);
        return out;
    }
    /**
     * Factory method to parse function from String
     * 
     * parseTree is not aliasing with token List anymore
     * 
     * @param str string to be parsed
     * @returna a result of Parsing with initialized parseTree of packageDef root
     */
    public static ParserResult funcFromString(String str) {
        CharStream charstream = CharStreams.fromString(str);
        ParserResult out=new ParserResult(charstream,ParserMode.FUNC);

        return out;
    }
    
    private ParserResult(CharStream charstream, ParserMode mode) {
        MinispecLexer lexer = new MinispecLexer(charstream);
        CommonTokenStream tokstream = new CommonTokenStream(lexer);
        MinispecParser parser = new MinispecParser(tokstream);
        parser.setErrorHandler(new BailErrorStrategy());
        switch(mode) {
            case PACKAGE_DEF: 
                try {
                    parseTree = parser.packageDef(); // parse here
                } catch(Exception e) {
                    throw new RuntimeException("can't parse Code");
                }
                break;
            case TYPE:
                try {
                    parseTree = parser.type(); // parse here

                }catch(Exception e) {
                    throw new RuntimeException("can't parse module name");
                }
                if(! (parseTree instanceof MinispecParser.TypeContext) || parseTree.getText().charAt(0)<'A' || parseTree.getText().charAt(0)>'Z')
                    throw new RuntimeException("can't parse module name");

                break;
            case FUNC:
                try{
                    parseTree = parser.exprPrimary();
                } catch(Exception e) {
                    throw new RuntimeException("can't parse function name");
                }

                if(! (parseTree instanceof MinispecParser.VarExprContext) || parseTree.getText().charAt(0)<'a' || parseTree.getText().charAt(0)>'z')
                    throw new RuntimeException("can't parse function name");
                break;
            default:
                throw new RuntimeException("Invalid mode to select");
        }
        tokenList = new ArrayList<>(tokstream.getTokens()); // defensive copy
    }
    /**
     * @return a reference to parseTree ( should not be mutated at all pls)
     */
    public ParseTree parseTree() { return parseTree;}
    /**
     * 
     * @return a defnesive unmodifiable copy of tokenList
     */
    public List<Token> tokenList(){ return List.copyOf(tokenList);}
    /**
     * 
     * @param args file name of source file to parse ( right now still have no effect)
     */
    public static void main(String[] args) {
        final String fileName = "input_dir/Multipliers.ms";
        try {
            CharStream charstream = CharStreams.fromFileName(fileName);
            MinispecLexer lexer = new MinispecLexer(charstream);
            CommonTokenStream tokstream = new CommonTokenStream(lexer);
            MinispecParser parser = new MinispecParser(tokstream);

            MinispecParser.PackageDefContext tree = parser.packageDef(); // will initiaite the parsign here

            System.out.println(tokstream.getTokens().size()); // stream is not consumed
            System.out.println(tokstream.getTokens().size());
            //ParseTreeWalker walker = new ParseTreeWalker();
            //walker.walk(new TestListener(), tree);
            System.out.println("yey");
            System.out.println(tree.children.get(0).getClass().getCanonicalName());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
