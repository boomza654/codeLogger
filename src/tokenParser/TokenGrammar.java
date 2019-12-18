package tokenParser;

import java.io.*;
import java.util.*;

import util.Pair;

import static tokenParser.TokenProdExpr.*;
/**
 * a Regex Matcher for Token
 * @author boomza654
 *
 */
public class TokenGrammar {
    private final Map<String,TokenProdExpr> prodMap;
    // Always make String toLower
    
    /**
     * Create empty Token Grammar
     */
    public TokenGrammar() { prodMap=new HashMap<>();}
    /**
     * @param m must be map from lower case string to Token Grammar
     */
    public TokenGrammar(final Map<String, TokenProdExpr> m) {
        prodMap = new HashMap<>(m);
        checkRep();
    }

    /**
     * Perform defensive copy
     * 
     * @param m to be copied
     */
    public TokenGrammar(final TokenGrammar m) {
        prodMap = new HashMap<>(m.prodMap);
        checkRep();
    }

    private void checkRep() {
        for (final String k : prodMap.keySet()) {
            assert k.matches("\\w+") : k + "must be match \\w+";
            assert k.toLowerCase().equals(k) : k + "must be lower case";
            assert prodMap.get(k) != null : "null entry??";
        }
    }

    /**
     * set Production rule for non terminal k
     * 
     * @param k non terminal name k should be matches \\w+ which will be converted
     *          to lower case
     * @param t the production expression
     */
    public void setProdRule(final String k, final TokenProdExpr t) {
        prodMap.put(k.toLowerCase(), t);
    }

    /**
     * 
     * @param k non terminal name
     * @return Token Grammar for such non terminal or null if not found
     */
    public TokenProdExpr getProdRule(final String k) {
        return prodMap.containsKey(k) ? prodMap.get(k) : null;
    }

    public Map<String, TokenProdExpr> getGrammarMap() {
        return new HashMap<>(prodMap);
    }

    /**
     * 
     * @param uri of a file that contains grammar
     * @return Token Grammar Manager that contains all grammar from File
     * @throws FileNotFoundException if the file of such uri is not found
     */
    public static TokenGrammar compileFromFile(final String uri) throws FileNotFoundException {
        final TokenGrammar g = new TokenGrammar();
        try (Scanner sc = new Scanner(new File(uri));) {
            while (sc.hasNextLine()) {
                final String s = sc.nextLine();
                if (!s.isBlank()) {
                    final Pair<String, TokenProdExpr> pair = GrammarReader.parse(s);
                    g.setProdRule(pair.first, pair.second);
                }
            }
        }
        return g;
    }

    /**
     * Initilize grammarMap with nonternminal for every token ( for sake of
     * simplciity)
     */
    public void initializeMapWithToken() {
        for (final TokenType t : TokenType.values()) {
            prodMap.put(t.name().toLowerCase(), token(t));
        }
    }

    @Override
    public String toString() {
        String output = "GramamrManager Content:\n";
        for (final String k : prodMap.keySet()) {
            output+=k+": "+prodMap.get(k)+"\n";
        }
        return output;
    }
}

