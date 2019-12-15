package tokenParser;

import java.util.*;

/**
 * 
 * Immutable class for node of parse Tree
 * 
 * @author boomza654
 */
public class ParseTree{
    public final String text;
    public final List<ParseTree> children;
    public final String nonTerminalName; // will have $ infront if it is terminal
    public final int start;
    public final int end;
    
    /**
     *  construct a parseTree node
     * @param text text of whole non terminal
     * @param children Children of thi node
     * @param nonTerminalName Type of non terminal
     * @param start start index in the list
     * @param end end index (exclusive) in the list
     */
    public ParseTree(String text, List<ParseTree> children, String nonTerminalName, int start,int end) {
        this.text=text;
        this.children=List.copyOf(children);
        this.nonTerminalName=nonTerminalName;
        this.start=start;
        this.end=end;
    }
    
    @Override public boolean equals(Object obj) {
        if(obj instanceof ParseTree) {
            ParseTree that = (ParseTree) obj;
            return that.text.equals(text) 
                    && that.children.equals(children) 
                    && that.nonTerminalName.equals(nonTerminalName) 
                    && that.start==start && that.end==end;
        }
        return false;
    }
    @Override public int hashCode() {
        return children.hashCode()+nonTerminalName.hashCode()+text.hashCode()+start+end;
    }
    private List<String> toListString(){

        String curText = "-"+nonTerminalName+": "+text+" @ "+start+","+end;
        List<String> out = new ArrayList<>();
        out.add(curText);
        for(ParseTree t:children) {
            List<String> childrenText = t.toListString();
            childrenText.stream().forEach((s)-> out.add("    "+s));
        }
        return out;
        
    }
    @Override public String toString() {
        return String.join("\n", toListString());
    }
}
