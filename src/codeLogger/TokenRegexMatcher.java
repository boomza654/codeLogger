package codeLogger;

import java.util.*;

/**
 * a Regex Matcher for Token
 * @author boomza654
 *
 */
public class TokenRegexMatcher {

}

/**
 * Token regx Interface
 * @author boomza654
 *
 */
interface TRgx{
    public static TRgx empty() { return new TRgxEmpty();}
    public static TRgx token(TokenType t) { return new TRgxToken(t);}
    public static TRgx tSet(Set<TokenType> t) { return new TRgxTokenSet(t);}
    public static TRgx tRgxQM(TRgx t) { return new TRgxOr(new TRgxEmpty(), t);}
    public static TRgx tRgxStar(TRgx t) { return new TRgxRepeat(t);}
    public static TRgx tRgxPlus(TRgx t) { return new TRgxConcat(t,tRgxStar(t));}
    public static TRgx tRgxPipe(TRgx ...t1) { return new TRgxOr(Arrays.asList(t1));}
    public static TRgx tRgxConcat(TRgx ...t1) { return new TRgxConcat(Arrays.asList(t1));}
       
}

class TRgxEmpty implements TRgx{
    
}
class TRgxToken implements TRgx{
    private final TokenType type;
    TRgxToken(TokenType t){ type=t;}
    TokenType getToken() { return type;}
}
class TRgxTokenSet implements TRgx{
    private final Set<TokenType> types;
    TRgxTokenSet(Set<TokenType> t){ types= Set.copyOf(t);}
    Set<TokenType> getTokenSet(){ return Set.copyOf(types);}
}
class TRgxConcat implements TRgx{
    private final List<TRgx> exprs;

    TRgxConcat(TRgx t1, TRgx t2){ this(List.of(t1,t2));}
    TRgxConcat(List<TRgx> t){ exprs= List.copyOf(t);}
    List<TRgx> getExprList(){ return List.copyOf(exprs);}
}
class TRgxOr implements TRgx{
    private final List<TRgx> exprs;
    TRgxOr(TRgx t1, TRgx t2){ this(List.of(t1,t2));}
    TRgxOr(List<TRgx> t){ exprs= List.copyOf(t);}
    List<TRgx> getExprList(){ return List.copyOf(exprs);}
}

class TRgxRepeat implements TRgx{
    private final TRgx expr;
    TRgxRepeat(TRgx t){ expr= t;}
    TRgx getExprList(){ return expr;}
}