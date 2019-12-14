package codeLogger;

import static tokenParser.TokenGrammar.*;
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
        TokenGrammar t = tgConcat(tgNonTerminal("boom"),tgOr(tgToken(DOT_PUNC),tgToken(INPUT_KEYWORD)),tgStar(tgAny()));
        System.out.println(t);
        TokenGrammarManager tm = new TokenGrammarManager();
        System.out.println(tm);
    }
}
