package codeLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represent a recognizable token with its Regex
 * @author boomza654
 *
 */
public enum Token {
    // Please declare token from highest precedent to lowest
    WHITESPACE ("\\s+"),
    ONELINE_COMMENT ("//[^\\n]*\\n"),
    MULTILINE_COMMENT ("/\\*(.|\\s)*\\*/"),
    
    IMPORT_KEYWORD("import"),
    BSVIMPORT_KEYWORD("bsvimport"),
    
    MODULE_KEYWORD("module"),
    ENDMODULE_KEYWORD("endmodule"),
    RULE_KEYWORD("rule"),
    ENDRULE_KEYWORD("endrule"),
    METHOD_KEYWORD("method"),
    ENDMETHOD_KEYWORD("endmethod"),
    FUNCTION_KEYWORD("function"),
    ENDFUNCTION_KEYWORD("endfunction"),
    
    INPUT_KEYWORD("input"),
    
    RETURN_KEYWORD("return"),
    CASE_KEYWORD("case"),
    FOR_KEYWRD("for"),
    DEFAULT_KEYWORD("default"),
    ENDCASE_KEYWORD("endcase"),
    BEGIN_KEYWORD("begin"),
    END_KEYWORD("end"),
    IF_KEYWORD("if"),
    ELSE_KEYWORD("else"),

    ENUM_KEYWORD("enum"),
    
    UPPER_IDENTIFIER("[A-Z][\\w_\\$]*"),
    LOWER_IDENTIFIER("[a-z][\\w_\\$]*"),
    DOLLAR_IDENTIFIER("\\$[\\w_\\$]*"),
    UNDERSCORE_IDENTIFIER("_[\\w_\\$]*"),
    
    LEFT_BRACE_PUNC("\\{"),
    RIGHT_BRACE_PUNC("\\}"),
    LEFT_BRACKET_PUNC("\\["),
    RIGHT_BRACKET_PUNC("\\]"),
    LEFT_PAREN_PUNC("\\("),
    RIGHT_PAREN_PUNC("\\)"),
    SHARP_PUNC("#"),
    SEMICOLON_PUNC(";"),
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
