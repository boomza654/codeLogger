package bmsc;

import java.util.*;

import static api.antlr4.MinispecParser.*;

/**
 * Provide utility
 * @author boomza654
 *
 */
public class Utility {
    
    public static boolean verbose=false;
    /**
     * 
     * With super slow + greedy algo replacement
     * single pass from the front found string => replace
     * 
     * <B>DEPRECATED: Please use string.replaceAll instead</b>
     * @param in the input string to replace
     * @param find  substring to find
     * @param toReplace substring to replace the found with
     * @return a replaced String
     */
    public static String stringReplace(String in, String find, String toReplace) {
        String out="";
        while(!in.isEmpty()) {
            int sliceIndex = in.indexOf(find);
            if(sliceIndex==-1) {
                out+=in;
                break;
            }
            out+= in.substring(0, sliceIndex)+toReplace;
            in=in.substring(sliceIndex+find.length());
            
        }
       return out;
       
    }
    
    /**
     * 
     * @param in the integer value string
     * @return int value of the literal
     * @throws NumberFormatException if the parsing is wrong
     */
    public static int getValueMinispecIntLiteral(String in) {
        in=in.replaceAll("_", ""); // replace things first
        if(in.indexOf("'")==-1) {
            return Integer.parseInt(in);
        }
        in=in.substring(in.indexOf("'")); // get rid of the size
        assert  (in.length()>=3): "Error the " +in +" has no integer value";
        char base = in.charAt(1);
        String num = in.substring(2);
        switch (base) {
            case 'd': return Integer.parseInt(num); 
            case 'b': return Integer.parseInt(num, 2); 
            case 'h': return Integer.parseInt(num, 16); 
            default: throw new NumberFormatException("int literal with unknown base");
        }
        
    }

    /**
     * 
     * @param in literal
     * @return whether the literal is sized or unsized
     */
    public static boolean isMinispecUnsizedLiteral(String in) {
        int quotePos = in.indexOf("'");
        return quotePos == -1 || quotePos == 0;
    }
    /**
     * Add prefix to the src string every line
     * @param src
     * @param prefix
     * @return
     */
    public static String addPrefix(String src, String prefix) {
       if(src.isEmpty()) return "";
       String out= Arrays.stream( src.split("\\n")).map((s)->prefix+s+"\n").reduce("",(a,b)->a+b);
       return out;
    }
    /**
     * return a proper Bluespec literal string
     * @param input
     * @return
     */
    public static String exprToString(Object input) {
        if(input instanceof Boolean)
            return ((Boolean)input)?"True":"False";
        return input.toString();
    }
    /**
     * Helper method for debug printing
     * So that turning of debug will be easy
     * @param s
     */
    public static void println(Object s) {
        if(verbose)
        System.out.println(s.toString());
    }
    public static void main(String[] args) {
        System.out.println(getValueMinispecIntLiteral("'h70000000"));
    }
}
