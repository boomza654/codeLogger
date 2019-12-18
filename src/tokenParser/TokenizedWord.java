package tokenParser;


/**
 * Just a struct that store token + place+ content
 * @author boomza654
 *
 */
public class TokenizedWord {
    public TokenType type;
    public String content;
    public int row;
    public int column;
    
    /**
     * Basic constructor
     * @param type Tokentype
     * @param content String
     * @param row int
     * @param column int
     */
    public TokenizedWord(final TokenType type, final String content, final int row, final int column) {
        this.type = type;
        this.content = content;
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + type + " " + content + " @ " + row + "," + column + ")";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof TokenizedWord) {
            final TokenizedWord o = (TokenizedWord) obj;
            return o.type.equals(type) && o.content.equals(content) && o.row==row && o.column==column;
        }
        return false;
    }
    @Override public int hashCode() {
        return toString().hashCode();
    }
}
