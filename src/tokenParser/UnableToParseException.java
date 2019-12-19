
package tokenParser;

/**
 * Class for representing an exception about parser error
 * @author boomza654
 */
public class UnableToParseException extends Exception{
   

    /**
     * Just some serial Number so that the Java compiler does nto complain
     */
    private static final long serialVersionUID = -5991643169030589418L;

    public UnableToParseException(final String s) {
        super(s);
    }
    public UnableToParseException(final String s,Exception e){
        super(s, e);
    }
}