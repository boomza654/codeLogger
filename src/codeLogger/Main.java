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
        TokenGrammar t = and(nonT("boom"),or(token(DOT_PUNC),token(INPUT_KEYWORD)),repeat(any()));
        TokenProdRules tm = new TokenProdRules();
        System.out.println(tm);
        System.out.println(GrammarReader.parse("kuay = boom , ( $DOT_punc | $inpuT_keyword ) , . *"));

    }
}
