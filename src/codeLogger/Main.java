package codeLogger;

//import static tokenParser.TokenProdExpr.*;
//import static tokenParser.TokenType.*;

import java.util.*;

import tokenParser.*;


public class Main {
    /**
     * main method
     * @param args args
     */
    public static void main(final String[] args) throws Exception {

        final List<List<TokenizedWord>> words = Lexer.tokenizeFileByLine("src/tokenParser/test2.ms");

        final List<TokenizedWord> firstLine = new ArrayList<>(words.get(0));
        firstLine.addAll(words.get(1));
        firstLine.addAll(words.get(2));        
        firstLine.addAll(words.get(3)); 
        firstLine.addAll(words.get(4));


        System.out.println(firstLine);
        final TokenGrammar tm = TokenGrammar.compileFromFile("src/tokenParser/minispec.gb");
        System.out.println(tm);
        final ParseTree p = new ParseTree("boom", List.of(), "test", 0, 5);
        final ParseTree p2 = new ParseTree("moob", List.of(), "@test", 5, 10);
        final ParseTree pp = new ParseTree("boommoob", List.of(p, p2), "combine", 0, 10);
        System.out.println(pp);
        System.out.println(tm.parse(firstLine, "start"));

    }
}
