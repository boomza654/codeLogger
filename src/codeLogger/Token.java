package codeLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represent a recognizable token with its Regex
 * @author boomza654
 *
 */
public enum Token {
    WHITESPACE ("\\s+"),
    ONELINE_COMMENT ("//[^\\n]*\\n"),
    MULTILINE_COMMENT ("/\\*(.|\\s)*\\*/")
    ;
    
    public final String regex;
    private final Pattern pattern;
    /*
     * Construct enum with this regex
     */
    Token(String regex){
        this.regex="^"+regex;        
        pattern = Pattern.compile(this.regex);
    }

    /**
     * 
     * @param input charsequence to match
     * @return -1 if not match else index of the end of Match
     */
    int endOfMatch(CharSequence input) {
        Matcher m = pattern.matcher(input);
        
        return m.find()?m.end():-1;
    }
    
}
