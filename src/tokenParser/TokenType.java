package tokenParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represent a recognizable token with its Regex
 * This is pretty much almost all token used in Minispecs
 * @author boomza654
 *
 */
public enum TokenType {
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

    TYPEDEF_KEYWORD("typedef"),
    STRUCT_KEYWORD("struct"),
    ENUM_KEYWORD("enum"),
    LET_KEYWORD("let"),
    
    UPPER_IDENTIFIER("[A-Z][\\w_\\$]*"),
    LOWER_IDENTIFIER("[a-z][\\w_\\$]*"),
    DOLLAR_IDENTIFIER("\\$[\\w_\\$]*"),
    UNDERSCORE_IDENTIFIER("_[\\w_\\$]*"),
    
    ALL0S_LITERAL("\'0"),
    ALL1S_LITERAL("\'1"),
    INT_LITERAL("(([\\+\\-])?\\d+)"),
    BIT_LITERAL("(\\d+|\\+|\\-)?\'(([dD][0-9_]+)|([hH][0-9a-fA-F_]+)|([bB][01_]+))"),
    STRING_LITERAL("\"[^\\n]*\""),

    SEMICOLON_PUNC(";"),
    LEFT_BRACE_PUNC("\\{"),
    RIGHT_BRACE_PUNC("\\}"),
    LEFT_BRACKET_PUNC("\\["),
    RIGHT_BRACKET_PUNC("\\]"),
    LEFT_PAREN_PUNC("\\("),
    RIGHT_PAREN_PUNC("\\)"),
    COLON_PUNC(":"),
    DOT_PUNC("\\."),
    COMMA_PUNC(","),
    SHARP_PUNC("#"),
    
    PLUS_OP("\\+"),
    MINUS_OP("\\-"),
    POW_OP("\\*\\*"),
    MUL_OP("\\*"),
    DIV_OP("/"),
    MOD_OP("%"),
    LEFTSHIFT_OP("<<"),
    RIGHTSHIFT_OP(">>"),
    LE_OP("<="), // might be register write 
    GE_OP(">="),
    LT_OP("<"),
    GT_OP(">"),
    EQEQ_OP("=="),
    NEQ_OP("!="),
    AND_OP("\\&\\&"),
    OR_OP("\\|\\|"),
    BIT_AND_OP("\\&"),
    BIT_OR_OP("\\|"),
    BIT_XOR_OP("\\^"),
    BIT_NOT_OP("~"),
    NOT_OP("!"),
    EQ_OP("="),
    
    
    QUESTIONMARK("\\?"), // This can be interpret based on context
    
    ;
    
    public final String regex;
    private final Pattern pattern;
    /*
     * Construct enum with this regex
     */
    TokenType(String regex){
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
