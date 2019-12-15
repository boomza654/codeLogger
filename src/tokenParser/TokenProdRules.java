package tokenParser;

import java.util.*;
import static tokenParser.TokenGrammar.*;
/**
 * a Regex Matcher for Token
 * @author boomza654
 *
 */
public class TokenProdRules {
    private final Map<String,TokenGrammar> prodMap;
    // Always make String toLower
    
    /**
     * Initilize Gramamr matcher with all primitive non terminal
     */
    public TokenProdRules() { prodMap=new HashMap<>();initializeMapWithToken();}
    /**
     * @param m must be map from lower case string to Token Grammar
     */
    public TokenProdRules(Map<String,TokenGrammar> m) {prodMap= new HashMap<>(m); checkRep();}
    /**
     * Perform defensive copy
     * @param m to be copied
     */
    public TokenProdRules(TokenProdRules m) {prodMap= new HashMap<>(m.prodMap); checkRep();}
    
    private void checkRep() {
        for(String k:prodMap.keySet()) {
            assert k.toLowerCase().equals(k): k+"must be lower case";
            assert prodMap.get(k)!=null : "null entry??";
        }
    }
    public void setProdRule(String k, TokenGrammar t) { prodMap.put(k, t); }
    /**
     * 
     * @param k non terminal name
     * @return Token Grammar for such non terminal or null if not found
     */
    public TokenGrammar getProdRule(String k) {return prodMap.containsKey(k)?prodMap.get(k):null;}
    /**
     * 
     * @param uri of a file that contains grammar
     * @return Token Grammar Manager that contains all grammar from File
     */
    public static TokenProdRules compileFromFile(String uri) {
        throw new RuntimeException("Not Implemented");
    }

    public Map<String, TokenGrammar> getGrammarMap() {
        return new HashMap<>(prodMap);
    }
    /**
     * Initilize grammarMap with nonternminal for every token
     */
    private void initializeMapWithToken() {
        for(TokenType t: TokenType.values()) {
            prodMap.put(t.name().toLowerCase(),token(t));
        }
    }
    @Override public String toString() {
        String output="GramamrManager Content:\n";
        for(String k:prodMap.keySet()) {
            output+=k+": "+prodMap.get(k)+"\n";
        }
        return output;
    }
}

