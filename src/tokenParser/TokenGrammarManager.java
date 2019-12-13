package tokenParser;

import java.util.*;

/**
 * a Regex Matcher for Token
 * @author boomza654
 *
 */
public class TokenGrammarManager {
    private final Map<String,TokenGrammar> grammarMap;
    
    public TokenGrammarManager() { grammarMap=new HashMap<>();}
    public TokenGrammarManager(Map<String,TokenGrammar> m) {grammarMap= new HashMap<>(m);}
    public TokenGrammarManager(TokenGrammarManager m) {grammarMap= new HashMap<>(m.grammarMap);}
    
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
}

