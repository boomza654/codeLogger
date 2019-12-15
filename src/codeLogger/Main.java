package codeLogger;

import static tokenParser.TokenProdExpr.*;
import static tokenParser.TokenType.*;

import java.util.*;

import tokenParser.*;


public class Main {
    /**
     * main method
     * @param args args
     */
    public static void main(String[] args) throws Exception{
        
        List<List<TokenizedWord>> words =Lexer.tokenizeFileByLine("src/tokenParser/testmini.ms"); 
        System.out.println(words);
        List<TokenizedWord> firstLine = new ArrayList<>(words.get(0));
        firstLine.removeIf((s)->Set.of(WHITESPACE,ONELINE_COMMENT,MULTILINE_COMMENT).contains(s.type));
        
        System.out.println(firstLine);
        TokenProdExpr t = and(nonT("boom"),or(token(DOT_PUNC),token(INPUT_KEYWORD)),repeat(any()));
        TokenGrammar tm = TokenGrammar.compileFromFile("src/tokenParser/minispec.gb");
        System.out.println(tm);
        ParseTree p = new ParseTree("boom", List.of(), "test", 0, 5);
        ParseTree p2 = new ParseTree("moob", List.of(), "@test", 5, 10);
        ParseTree pp = new ParseTree("boommoob", List.of(p,p2), "combine" , 0,10);
        System.out.println(pp);

    }
}
