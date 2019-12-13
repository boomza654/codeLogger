package tokenParser;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Immutable
 * Token grammar Interface
 * @author boomza654
 *
 */
public interface TokenGrammar{
    

    public static TokenGrammar tgEmpty() { return new TGEmpty();}
    public static TokenGrammar tgToken(TokenType t) { return new TGToken(t);} // will possibly be used only by Grammar manager
    public static TokenGrammar tgSet(Set<TokenType> t) { return new TGTokenSet(t);}
    public static TokenGrammar tgNotSet(Set<TokenType> t) { return new TGNotTokenSet(t);}
    public static TokenGrammar tgNonTerminal(String t) {return new TGNonTerminal(t);}
    public static TokenGrammar tgQM(TokenGrammar t) { return new TGOr(new TGEmpty(), t);}
    public static TokenGrammar tgStar(TokenGrammar t) { return new TGRepeat(t);}
    public static TokenGrammar tgPlus(TokenGrammar t) { return new TGConcat(t,tgStar(t));}
    public static TokenGrammar tgOr(TokenGrammar ...t1) { return new TGOr(Arrays.asList(t1));}
    public static TokenGrammar tgConcat(TokenGrammar ...t1) { return new TGConcat(Arrays.asList(t1));}
    public static TokenGrammar tgAny() { return new TGNotTokenSet(Set.of());}
       
}

class TGEmpty implements TokenGrammar{
}
class TGToken implements TokenGrammar{
    private final TokenType type;
    TGToken(TokenType t){ type=t;}
    TokenType getToken() { return type;}
}
class TGTokenSet implements TokenGrammar{
    private final Set<TokenType> types;
    TGTokenSet(Set<TokenType> t){ types= Set.copyOf(t);}
    Set<TokenType> getTokenSet(){ return Set.copyOf(types);}
}
class TGNonTerminal implements TokenGrammar{
    private final String name;
    TGNonTerminal(String n){ name=n;}
    String getName() {return name;}
}
class TGNotTokenSet implements TokenGrammar{
    private final Set<TokenType> types;
    TGNotTokenSet(Set<TokenType> t){ types= Set.copyOf(t);}
    Set<TokenType> getTokenSet(){ return Set.copyOf(types);}
}
class TGConcat implements TokenGrammar{
    private final List<TokenGrammar> exprs;

    TGConcat(TokenGrammar t1, TokenGrammar t2){ this(List.of(t1,t2));}
    TGConcat(List<TokenGrammar> t){ exprs= List.copyOf(t);}
    List<TokenGrammar> getExprList(){ return List.copyOf(exprs);}
}
class TGOr implements TokenGrammar{
    private final List<TokenGrammar> exprs;
    TGOr(TokenGrammar t1, TokenGrammar t2){ this(List.of(t1,t2));}
    TGOr(List<TokenGrammar> t){ exprs= List.copyOf(t);}
    List<TokenGrammar> getExprList(){ return List.copyOf(exprs);}
}

class TGRepeat implements TokenGrammar{
    private final TokenGrammar expr;
    TGRepeat(TokenGrammar t){ expr= t;}
    TokenGrammar getExprList(){ return expr;}
}