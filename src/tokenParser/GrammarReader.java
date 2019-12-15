package tokenParser;

import java.util.*;
import static tokenParser.TokenProdExpr.*;

import util.Pair;
/**
 * 
 * Class that read manual production expression and generate token Grammar
 * 
 * 
 * This manual grammar only consist of 
 * (From  lowest to highest precedent)
 * - | ( or)
 * - , ( concat)
 * - +,*,? (postfix)
 * - [ t1 t2 t3 ] (token class)
 * - [^ t1 t2 t3 ... ] (not token class)
 * - non terminal name (case insensitive + _+ 0-9 )
 * - terminal name (start with $ then name)
 * - ()
 * - empty expression can be inserted between | or , or before postfix operator
 * 
 * precedence () >   [],[^]> postfix > , > |
 * 
 * Form of grammar
 * nonterminal name = expr | expr | ...
 *            or expr , expr , epxr ,...
 *            or expr + or expr ? or expr *
 *            or ( expr )
 *            or [ t1 t2 t3 ] (token class) 
 *            or [^ t1 t2 t3 ... ] (not token class) 
 *            or non terminal name 
 *            or terminal name (start with $ then name)
 * 
 * Use *spacebar* to seaparate between all tokens (so that I dont have to make grammar lexers)
 * 
 * 
 */
public class GrammarReader {
    /**
     * 
     * @param expression an expression of Token Grammar (must be one line only with no line terminating char)
     * @return Pair of ( non terminal getting assigned,Token grammar corresponds to that expression)
     */
    public static Pair<String,TokenProdExpr> parse(String expression) {
        try(Scanner sc = new Scanner(expression);){
            List<String> grammarTokens = new ArrayList<>();
            while(sc.hasNext()) {
                grammarTokens.add(sc.next());
            }
            assert grammarTokens.get(1).equals("="): "no assignment happen";
            assert grammarTokens.get(0).matches("\\w+"):" not assigning non terminal ?!?!";
            return new Pair<String, TokenProdExpr>(grammarTokens.get(0).toLowerCase(),parseOr(grammarTokens,2, grammarTokens.size()));
        } 
    }
    
    /**
     * separate tokens into Or of multiple tokens  then recursively parse down
     * @param grammarTokens List of String for each token in Grammar Expression
     * @param start index inclusive
     * @param end index exclusive
     * @return Token grammar of the or-ed of  grammarTokens[start:end]
     */
    static TokenProdExpr parseOr(List<String> grammarTokens, int start, int end) {
        //System.err.println(grammarTokens+" "+start+" "+end);
        if(start>=end) return empty();
        
        int nestedLevel=0; 
        boolean isInTokenClass=false;
        List<Integer> orIndexes = new ArrayList<>();
        // Find outermost binary operator
        for(int i=start;i<end;i++) {
            String currentToken = grammarTokens.get(i);
            switch(currentToken) {
            case "(": nestedLevel++;break;
            case ")": nestedLevel--; break;
            case "[":
            case "[^": assert !isInTokenClass: "nested token class?!?!?!?!";
                    isInTokenClass = true;
                    break;
            case "]": assert isInTokenClass: "too much ]";
                isInTokenClass =false; 
                break;
            
            }
            
            if(nestedLevel==0 && currentToken.equals("|")) {
                assert !isInTokenClass: " found | in token class?!!?";
                orIndexes.add(i);
            }
        }
        if(orIndexes.size()==0) return parseAnd(grammarTokens,start,end);
        orIndexes.add(end);
        
        List<TokenProdExpr> grammars = new ArrayList<>();
        int startIndex=start;
        for(int endIndex:orIndexes) {
        
            grammars.add(parseAnd(grammarTokens,startIndex,endIndex));
            startIndex=endIndex+1;
        }
        return new TGOr(grammars);
    }
    
    /**
     * separate tokens into concats of multiple tokens  then recursively parse down
     * @param grammarTokens List of String for each token in Grammar Expression
     *          must not have unnested | token
     * @param start index inclusive
     * @param end index exclusive
     * @return Token grammar of the concat-ed of  grammarToken[start:end]
     */
    static TokenProdExpr parseAnd(List<String> grammarTokens, int start, int end) {

        //System.err.println(grammarTokens+" "+start+" "+end);
                
        if(start>=end) return empty();
        
        int nestedLevel=0; 
        boolean isInTokenClass=false;
        List<Integer> andIndexes = new ArrayList<>();
        // Find outermost binary operator
        for(int i=start;i<end;i++) {
            String currentToken = grammarTokens.get(i);
            switch(currentToken) {
            case "(": nestedLevel++;break;
            case ")": nestedLevel--; break;
            case "[":
            case "[^": assert !isInTokenClass: "nested token class?!?!?!?!";
                    isInTokenClass = true;
                    break;
            case "]": assert isInTokenClass: "too much ]";
                isInTokenClass =false; 
                break;
            
            }
            
            if(nestedLevel==0 && currentToken.equals(",")) {
                assert !isInTokenClass: " found , in token class?!!?";
                andIndexes.add(i);
            }
        }
        if(andIndexes.size()==0) return parsePost(grammarTokens,start,end);
        andIndexes.add(end);
        
        List<TokenProdExpr> grammars = new ArrayList<>();
        int startIndex=start;
        for(int endIndex:andIndexes) {
            grammars.add(parsePost(grammarTokens,startIndex,endIndex));
            startIndex=endIndex+1;
        }
        return new TGConcat(grammars);
        
    }
    /**
     * look at last token to see if need postFix operator then recursively parse down
     * @param grammarTokens List of String for each token in Grammar Expression
     *          must not have unnested | and , token
     * @param start index inclusive
     * @param end index exclusive 
     * @return Token grammar of the expression grammarToken[start:end]
     */
    static TokenProdExpr parsePost(List<String> grammarTokens, int start, int end) {

        //System.err.println(grammarTokens+" "+start+" "+end);
        if(start>=end) return empty();
        switch(grammarTokens.get(end-1)) {
            case "+": return plus(parsePrimitive(grammarTokens,start,end-1));
            case "?": return optional(parsePrimitive(grammarTokens,start,end-1));
            case "*": return repeat(parsePrimitive(grammarTokens,start,end-1));
            default: return parsePrimitive(grammarTokens,start,end);
        }
    }
    /**
     * separate tokens into concats of multiple tokens  then recursively parse down
     * @param expression an expression of Token Grammar
     *          must be literals  or [] or ()
     * @param start index inclusive
     * @param end index exclusive
     * @return Token grammar of the or-ed of entire expression
     */
    static TokenProdExpr parsePrimitive(List<String> grammarTokens, int start, int end) {

        //System.err.println(grammarTokens+" "+start+" "+end);
        if(start>=end) return empty();
        if(start==end-1) {
            String currentLiteral = grammarTokens.get(start);
            if(currentLiteral.equals(".")) return any();
            if(currentLiteral.startsWith("$")) {
                return token(TokenType.valueOf(currentLiteral.substring(1).toUpperCase()));
            }
            assert currentLiteral.matches("\\w+"):"Non identifier?!?!?";
            return nonT(currentLiteral.toLowerCase());
        }
        else if(grammarTokens.get(start).equals("(")) {
            assert grammarTokens.get(end-1).equals(")"): " non matching )";
            return parseOr(grammarTokens,start+1,end-1);
            
        }
        else if(grammarTokens.get(start).equals("[") || grammarTokens.get(start).equals("[^")) {
            assert grammarTokens.get(end-1).equals("]"): " non matching ]";
            Set<TokenType> s = new HashSet<>();
            for(int i=start+1;i<end-1;i++) {

                String currentLiteral = grammarTokens.get(i);
                assert currentLiteral.startsWith("$"): " expect token name not non terminal";
                s.add(TokenType.valueOf(currentLiteral.substring(1).toUpperCase()));
            }
            return grammarTokens.get(start).equals("[")?tSet(s):tNotSet(s);
        }
        else {
            throw new AssertionError("Syntax Error???S " + grammarTokens);
        }
    }
    
}
