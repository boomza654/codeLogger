package codeLogger;

import static tokenParser.TokenGrammar.*;
import static tokenParser.TokenType.*;

import java.util.*;

import tokenParser.Lexer;
import tokenParser.TokenGrammar;
import tokenParser.TokenType;
import tokenParser.TokenizedWord;


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
        TokenGrammar grammar = tgConcat(tgAny(), tgToken(LEFT_PAREN_PUNC),tgToken(STRING_LITERAL),tgToken(RIGHT_PAREN_PUNC),tgToken(SEMICOLON_PUNC));
        System.out.println(grammar.endOfMatch(firstLine, 0));
        
    }
}
