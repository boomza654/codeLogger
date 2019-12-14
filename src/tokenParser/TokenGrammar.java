package tokenParser;

import java.util.*;

/**
 * Immutable
 * Token grammar Interface
 * @author boomza654
 *
 */
public interface TokenGrammar{
    

    public static TokenGrammar empty() { return new TGEmpty();}
    public static TokenGrammar token(TokenType t) { return new TGToken(t);} // will possibly be used only by Grammar manager
    public static TokenGrammar tSet(Set<TokenType> t) { return new TGTokenSet(t);} // will possibly be used only by Grammar manager
    public static TokenGrammar tNotSet(Set<TokenType> t) { return new TGNotTokenSet(t);}// will possibly be used only by Grammar manager
    public static TokenGrammar any() { return new TGNotTokenSet(Set.of());}
    public static TokenGrammar nonT(String t) {return new TGNonTerminal(t);}
    public static TokenGrammar optional(TokenGrammar t) { return new TGOr(new TGEmpty(), t);}
    public static TokenGrammar repeat(TokenGrammar t) { return new TGRepeat(t);}
    public static TokenGrammar plus(TokenGrammar t) { return new TGConcat(t,repeat(t));}
    public static TokenGrammar or(TokenGrammar ...t1) { return new TGOr(Arrays.asList(t1));}
    public static TokenGrammar and(TokenGrammar ...t1) { return new TGConcat(Arrays.asList(t1));}
    @Override public boolean equals(Object obj);
    @Override public int hashCode();
    @Override public String toString();
       
}

class TGEmpty implements TokenGrammar{
    @Override public String toString() { return "";}
    @Override public boolean equals(Object obj) { return obj instanceof TGEmpty;}
    @Override public int hashCode() { return 31;}
}
class TGToken implements TokenGrammar{
    private final TokenType type;
    TGToken(TokenType t){ type=t;}
    TokenType getToken() { return type;}
   
    @Override public String toString() {
        return "Token [" + type + "]";
    }
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGToken other = (TGToken) obj;
        if (type != other.type)
            return false;
        return true;
    }

}
class TGTokenSet implements TokenGrammar{
    private final Set<TokenType> types;
    TGTokenSet(Set<TokenType> t){ types= Set.copyOf(t);}
    Set<TokenType> getTokenSet(){ return Set.copyOf(types);}
    @Override public String toString() {
        return "TokenSet " + types ;
    }
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((types == null) ? 0 : types.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGTokenSet other = (TGTokenSet) obj;
        if (types == null) {
            if (other.types != null)
                return false;
        } else if (!types.equals(other.types))
            return false;
        return true;
    }
    
    
}
class TGNonTerminal implements TokenGrammar{
    private final String name;
    TGNonTerminal(String n){ name=n;}
    String getName() {return name;}
    @Override public String toString() {
        return name ;
    }
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGNonTerminal other = (TGNonTerminal) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
class TGNotTokenSet implements TokenGrammar{
    private final Set<TokenType> types;
    TGNotTokenSet(Set<TokenType> t){ types= Set.copyOf(t);}
    Set<TokenType> getTokenSet(){ return Set.copyOf(types);}
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((types == null) ? 0 : types.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGNotTokenSet other = (TGNotTokenSet) obj;
        if (types == null) {
            if (other.types != null)
                return false;
        } else if (!types.equals(other.types))
            return false;
        return true;
    }
    @Override public String toString() {
        return "NotTokenSet !" + types + "";
    }
}
class TGConcat implements TokenGrammar{
    private final List<TokenGrammar> exprs;

    TGConcat(TokenGrammar t1, TokenGrammar t2){ this(List.of(t1,t2));}
    TGConcat(List<TokenGrammar> t){ exprs= List.copyOf(t);}
    List<TokenGrammar> getExprList(){ return List.copyOf(exprs);}
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exprs == null) ? 0 : exprs.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGConcat other = (TGConcat) obj;
        if (exprs == null) {
            if (other.exprs != null)
                return false;
        } else if (!exprs.equals(other.exprs))
            return false;
        return true;
    }
    @Override public String toString() {
        List<String> stringExpr =new ArrayList<>();
        exprs.forEach((s)-> stringExpr.add(s.toString()));
        return "(" + String.join(" ", stringExpr) + ")";
    }
}
class TGOr implements TokenGrammar{
    private final List<TokenGrammar> exprs;
    TGOr(TokenGrammar t1, TokenGrammar t2){ this(List.of(t1,t2));}
    TGOr(List<TokenGrammar> t){ exprs= List.copyOf(t);}
    List<TokenGrammar> getExprList(){ return List.copyOf(exprs);}
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exprs == null) ? 0 : exprs.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGOr other = (TGOr) obj;
        if (exprs == null) {
            if (other.exprs != null)
                return false;
        } else if (!exprs.equals(other.exprs))
            return false;
        return true;
    }
    @Override public String toString() {
        List<String> stringExpr =new ArrayList<>();
        exprs.forEach((s)-> stringExpr.add(s.toString()));
        return "(" + String.join(" | ", stringExpr) + ")";
    }
}

class TGRepeat implements TokenGrammar{
    private final TokenGrammar expr;
    TGRepeat(TokenGrammar t){ expr= t;}
    TokenGrammar getExprList(){ return expr;}
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expr == null) ? 0 : expr.hashCode());
        return result;
    }
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TGRepeat other = (TGRepeat) obj;
        if (expr == null) {
            if (other.expr != null)
                return false;
        } else if (!expr.equals(other.expr))
            return false;
        return true;
    }
    @Override public String toString() {
        return "(" + expr + ")*";
    }
}