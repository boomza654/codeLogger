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
     * Factory method to parse things from from String
     * 
     * parseTree is not aliasing with token List anymore
     * 
     * @param str string to be parsed
     * @returna a result of Parsing with initialized parseTree of packageDef root
     */
    public static ParserResult fromString(String str) {
        CharStream charstream = CharStreams.fromString(str);
        return new ParserResult(charstream);
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
        return new ParserResult(charstream);
    }
    private ParserResult(CharStream charstream) {
        MinispecLexer lexer = new MinispecLexer(charstream);
        CommonTokenStream tokstream = new CommonTokenStream(lexer);
        MinispecParser parser = new MinispecParser(tokstream);
        parseTree = parser.packageDef(); // parse here
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