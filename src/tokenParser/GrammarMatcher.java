package tokenParser;

import java.util.*;

import javax.management.RuntimeErrorException;

/**
 * Match things with grammar
 * 
 * @author boomza654
 *
 */
public class GrammarMatcher {
    
    /**
     * parse a List of tokenized words
     * @param words a list o tokenized words
     * @param prodMap a Mapping of the definition of each nonterminal
     * @param startNonterminal a nonterminal we should start parsing from
     * @return a ParseTree of the given list of tokenized words
     * @throws UnableToParseException if the list cannot be parsed
     */
    public static ParseTree parse(final List<TokenizedWord> words, final Map<String,TokenProdExpr> prodMap, final String startNonterminal) throws UnableToParseException {
        throw new RuntimeException("unimplemented");
    }

    /**
     * a class of List traverser that  traverse the tokenized words list and tries to match them with grammar
     */
    static class ParserTraverser{
        private final List<TokenizedWord> words;
        private final Map<String,TokenProdExpr> prodMap;
        private int currentPos;
        /**
         * Construct a Parsertraverser that traverse
         * @param words List of tokenized words to be traversing
         * @param prodMap Map of string to Expression
         * @param start the current index of the list to be traversing
         *          0<=start<words.size()
         */
        public ParserTraverser(final List<TokenizedWord> words, final Map<String,TokenProdExpr> prodMap, final int start){
            this.words=words;
            this.prodMap=prodMap;
            this.currentPos=start;
        }
        
        /**
         * Try match list of tokenized wrods from current Pos to the given expression
         * @param expr the token Expression
         *      must not be null
         * @return the ParseTree of the matching 
         *      The currentPos will get mutated by callig this
         * @throws UnableToParseException if Such expr cannot be matched
         */
        public ParseTree parseAndMove(final TokenProdExpr expr) throws UnableToParseException{
            assert expr!=null: "expr is null ?!?!?!?";
            throw new RuntimeException("not implemented");
        }
    }

}
