package tokenParser;

import java.util.*;
import static tokenParser.TokenGrammar.*;
/**
 * a Regex Matcher for Token
 * @author boomza654
 *
 */
public class TokenGrammarManager {
    private final Map<String,TokenGrammar> grammarMap;
    // Always make String toLower
    
    public TokenGrammarManager() { grammarMap=new HashMap<>();initializeMapWithToken();}
    /**
     * @param m must be map from lower case string to Token Grammar
     */
    public TokenGrammarManager(Map<String,TokenGrammar> m) {grammarMap= new HashMap<>(m); checkRep();}
    public TokenGrammarManager(TokenGrammarManager m) {grammarMap= new HashMap<>(m.grammarMap); checkRep();}
    
    private void checkRep() {
        for(String k:grammarMap.keySet()) {
            assert k.toLowerCase().equals(k): k+"must be lower case";
            assert grammarMap.get(k)!=null : "null entry??";
        }
    }
    
    /**
     * 
     * @param uri of a file that contains grammar
     * @return Token Grammar Manager that contains all grammar from File
     */
    public static TokenGrammarManager readFromFile(String uri) {
        return null; //TODO
    }
    public Map<String, TokenGrammar> getGrammarMap() {
        return new HashMap<>(grammarMap);
    }
    /**
     * Initilize grammarMap with nonternminal for every token
     */
    private void initializeMapWithToken() {
        for(TokenType t: TokenType.values()) {
            grammarMap.put(t.name().toLowerCase(),tgToken(t));
        }
    }
    @Override public String toString() {
        String output="GramamrManager Content:\n";
        for(String k:grammarMap.keySet()) {
            output+=k+": "+grammarMap.get(k)+"\n";
        }
        return output;
    }
}

