package tokenParser;

import java.util.*;

import util.*;


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
     * @throws UnableToParseException if the list cannot be parsed or the parsing is unsuccessful
     */
    public static ParseTree parse(final List<TokenizedWord> words, final Map<String,TokenProdExpr> prodMap, final String startNonterminal) throws UnableToParseException {
        ParserTraverser parser = new ParserTraverser(words, prodMap);
        Pair<Integer,List<ParseTree>> result = parser.parseAndMove(TokenProdExpr.nonT(startNonterminal), 0);
        if(result.first != words.size()){
            throw new UnableToParseException("Under parse");
        }
        assert result.second.size()==1: " return non 1 children";
        return result.second.get(0);
    }


    /**
     * a class of List traverser that  traverse the tokenized words list and tries to match them with grammar
     */
    private static class ParserTraverser{
        private final List<TokenizedWord> words;
        private final Map<String,TokenProdExpr> prodMap;
        /**
         * Construct a Parsertraverser that traverse
         * @param words List of tokenized words to be traversing
         * @param prodMap Map of string to Expression
         */
        public ParserTraverser(final List<TokenizedWord> words, final Map<String,TokenProdExpr> prodMap){
            this.words=words;
            this.prodMap=prodMap;
        }
        /**
         * Try match list of tokenized wrods from current Pos to the given expression
         * @param expr Token Production expression to parse (should be a part of non terminal with curr Non terminal)
         * @param pos the starting position in the list that we try to match
         * @return Pair of end of match index and the List of related nonterminal / terminal 's
         *      ParseTree of the matching 
         *      the returned tree can be empty list if matched against empty expression
         * @throws UnableToParseException if Such expr cannot be matched
         */
        public Pair<Integer,List<ParseTree>> parseAndMove(final TokenProdExpr expr, final int pos) throws UnableToParseException{
            assert expr!=null: "expr is null ?!?!?!?";
            final TokenizedWord t = (pos>=words.size() || pos<0) ?null:words.get(pos);
            switch(expr.getType()){
                case EMPTY:  return new Pair<Integer,List<ParseTree>>(pos,List.of()); 
                case TOKEN: {
                    final TGToken curExpr=(TGToken) expr; // this should always work
                    if(t!=null && t.type.equals(curExpr.getToken())){
                        final ParseTree p=new ParseTree(t.content, List.of(), "$"+t.type.name(), pos, pos+1); 
                        return new Pair<Integer,List<ParseTree>>(pos+1, List.of(p));
                    } else{ throw new UnableToParseException("Cannot match "+ curExpr + " element @ "+pos); }
                }

                case TOKEN_SET:  {
                    final TGTokenSet curExpr=(TGTokenSet) expr; // this should always work
                    if(t!=null && curExpr.getTokenSet().contains(t.type)){
                        final ParseTree p=new ParseTree(t.content, List.of(), "$"+t.type.name(), pos, pos+1); 
                        return new Pair<Integer,List<ParseTree>>(pos+1, List.of(p));
                    } else{ throw new UnableToParseException("Cannot match "+ curExpr + " element @ "+pos); }
                }
                case NOT_TOKEN_SET: {
                    final TGNotTokenSet curExpr=(TGNotTokenSet) expr; // this should always work
                    if(t!=null && !curExpr.getTokenSet().contains(t.type)){
                        final ParseTree p=new ParseTree(t.content, List.of(), "$"+t.type.name(), pos, pos+1); 
                        return new Pair<Integer,List<ParseTree>>(pos+1, List.of(p));
                    } else{ throw new UnableToParseException("Cannot match "+ curExpr + " element @ "+pos); }
                }
                case NON_TERMINAL:{
                    final TGNonTerminal curExpr=(TGNonTerminal) expr; // this should always work
                    if(t!=null && prodMap.containsKey(curExpr.getName())){
                        final Pair<Integer,List<ParseTree>> result = parseAndMove(prodMap.get(curExpr.getName()), pos); // if this cant match Unable to Parse Excetion will auomatically thrown
                        final int endPos=result.first.intValue();
                        String curContent="";
                        for(ParseTree children: result.second){
                            curContent+= children.text+" ";
                        }
                        final ParseTree p=new ParseTree(curContent, result.second, curExpr.getName(), pos, endPos); 
                        return new Pair<Integer,List<ParseTree>>(endPos, List.of(p));
                    } else{ throw new UnableToParseException("Cannot match "+ curExpr + " element @ "+pos); }
                }
                case CONCAT: {
                    final TGConcat curExpr=(TGConcat) expr; // this should always work
                    int curPos=pos;
                    List<ParseTree> allRelatedTrees = new ArrayList<>();
                    for(TokenProdExpr part: curExpr.getExprList()){
                        final Pair<Integer,List<ParseTree>> result = parseAndMove(part, curPos); // if this cant match Unable to Parse Excetion will auomatically thrown
                        curPos=result.first;
                        allRelatedTrees.addAll(result.second);
                    }
                    return new Pair<Integer,List<ParseTree>>(curPos, allRelatedTrees);
                }
                case OR:{
                    final TGOr curExpr=(TGOr) expr; // this should always work
                    for(TokenProdExpr part: curExpr.getExprList()){
                        try{
                            final Pair<Integer,List<ParseTree>> result = parseAndMove(part, pos); // if this cant match Unable to Parse Excetion will auomatically thrown
                            // If success
                            return result;
                        } catch(UnableToParseException e){
                            continue;
                        }
                    }
                    throw new UnableToParseException("Cannot match "+ curExpr + " element @ "+pos);
                }
                case REPEAT:{
                    final TGRepeat curExpr=(TGRepeat) expr; // this should always work
                    int curPos=pos;
                    List<ParseTree> allRelatedTrees = new ArrayList<>();
                    TokenProdExpr toRepeatMatch = curExpr.getExpr();
                    boolean finished=false;
                    while(!finished){
                        try{
                            final Pair<Integer,List<ParseTree>> result = parseAndMove(toRepeatMatch, curPos); // if this cant match Unable to Parse Excetion will auomatically thrown
                            curPos=result.first;
                            allRelatedTrees.addAll(result.second);
                        } catch(UnableToParseException e){
                            finished=true;
                        }
                    }
                    return new Pair<Integer,List<ParseTree>>(curPos, allRelatedTrees);
                }
                default : throw new AssertionError("shoudl not reach here");
            }
        }
    }

}
