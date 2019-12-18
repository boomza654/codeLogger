package tokenParser;

import java.util.*;

/**
 * Immutable
 * Token grammar Interface
 * @author boomza654
 *
 */
public interface TokenProdExpr{
    

    public static TokenProdExpr empty() { return new TGEmpty();}
    public static TokenProdExpr token(final TokenType t) {
        return new TGToken(t);
    } // will possibly be used only by Grammar manager

    public static TokenProdExpr tSet(final Set<TokenType> t) {
        return new TGTokenSet(t);
    } // will possibly be used only by Grammar manager

    public static TokenProdExpr tNotSet(final Set<TokenType> t) {
        return new TGNotTokenSet(t);
    }// will possibly be used only by Grammar manager

    public static TokenProdExpr any() {
        return new TGNotTokenSet(Set.of());
    }

    public static TokenProdExpr nonT(final String t) {
        return new TGNonTerminal(t);
    }

    public static TokenProdExpr optional(final TokenProdExpr t) {
        return new TGOr(t, new TGEmpty());
    }

    public static TokenProdExpr repeat(final TokenProdExpr t) {
        return new TGRepeat(t);
    }

    public static TokenProdExpr plus(final TokenProdExpr t) {
        return new TGConcat(t, repeat(t));
    }

    public static TokenProdExpr or(final TokenProdExpr... t1) {
        return new TGOr(Arrays.asList(t1));
    }

    public static TokenProdExpr and(final TokenProdExpr... t1) {
        return new TGConcat(Arrays.asList(t1));
    }

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    @Override
    public String toString();

}

class TGEmpty implements TokenProdExpr {
    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof TGEmpty;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

class TGToken implements TokenProdExpr {
    private final TokenType type;

    TGToken(final TokenType t) {
        type = t;
    }

    TokenType getToken() {
        return type;
    }

    @Override
    public String toString() {
        return "$" + type.name();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGToken other = (TGToken) obj;
        if (type != other.type)
            return false;
        return true;
    }

}

class TGTokenSet implements TokenProdExpr {
    private final Set<TokenType> types;

    TGTokenSet(final Set<TokenType> t) {
        types = Set.copyOf(t);
    }

    Set<TokenType> getTokenSet() {
        return Set.copyOf(types);
    }

    @Override
    public String toString() {
        return "[ " + types.stream().map((s) -> "$" + s.name()).reduce("", (a, b) -> a + " " + b) + " ]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((types == null) ? 0 : types.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGTokenSet other = (TGTokenSet) obj;
        if (types == null) {
            if (other.types != null)
                return false;
        } else if (!types.equals(other.types))
            return false;
        return true;
    }

}

class TGNonTerminal implements TokenProdExpr {
    private final String name;

    TGNonTerminal(final String n) {
        name = n;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGNonTerminal other = (TGNonTerminal) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}

class TGNotTokenSet implements TokenProdExpr {
    private final Set<TokenType> types;

    TGNotTokenSet(final Set<TokenType> t) {
        types = Set.copyOf(t);
    }

    Set<TokenType> getTokenSet() {
        return Set.copyOf(types);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((types == null) ? 0 : types.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGNotTokenSet other = (TGNotTokenSet) obj;
        if (types == null) {
            if (other.types != null)
                return false;
        } else if (!types.equals(other.types))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[^ " + types.stream().map((s) -> "$" + s.name()).reduce("", (a, b) -> a + " " + b) + " ]";
    }
}

class TGConcat implements TokenProdExpr {
    private final List<TokenProdExpr> exprs;

    TGConcat(final TokenProdExpr t1, final TokenProdExpr t2) {
        this(List.of(t1, t2));
    }

    TGConcat(final List<TokenProdExpr> t) {
        exprs = List.copyOf(t);
    }

    List<TokenProdExpr> getExprList() {
        return List.copyOf(exprs);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exprs == null) ? 0 : exprs.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGConcat other = (TGConcat) obj;
        if (exprs == null) {
            if (other.exprs != null)
                return false;
        } else if (!exprs.equals(other.exprs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final List<String> stringExpr = new ArrayList<>();
        exprs.forEach((s) -> stringExpr.add(s.toString()));
        return "( " + String.join(" , ", stringExpr) + " )";
    }
}

class TGOr implements TokenProdExpr {
    private final List<TokenProdExpr> exprs;

    TGOr(final TokenProdExpr t1, final TokenProdExpr t2) {
        this(List.of(t1, t2));
    }

    TGOr(final List<TokenProdExpr> t) {
        exprs = List.copyOf(t);
    }

    List<TokenProdExpr> getExprList() {
        return List.copyOf(exprs);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exprs == null) ? 0 : exprs.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGOr other = (TGOr) obj;
        if (exprs == null) {
            if (other.exprs != null)
                return false;
        } else if (!exprs.equals(other.exprs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final List<String> stringExpr = new ArrayList<>();
        exprs.forEach((s) -> stringExpr.add(s.toString()));
        return "( " + String.join(" | ", stringExpr) + " )";
    }
}

class TGRepeat implements TokenProdExpr {
    private final TokenProdExpr expr;

    TGRepeat(final TokenProdExpr t) {
        expr = t;
    }

    TokenProdExpr getExprList() {
        return expr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expr == null) ? 0 : expr.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TGRepeat other = (TGRepeat) obj;
        if (expr == null) {
            if (other.expr != null)
                return false;
        } else if (!expr.equals(other.expr))
            return false;
        return true;
    }
    @Override public String toString() {
        return  expr + " *";
    }
}