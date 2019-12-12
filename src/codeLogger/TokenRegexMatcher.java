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
    TRgxConcat(List<TRgx> t){ exprs= List.copyOf(t);}
    List<TRgx> getExprList(){ return List.copyOf(exprs);}
}
class TRgxOr implements TRgx{
    private final List<TRgx> exprs;
    TRgxOr(List<TRgx> t){ exprs= List.copyOf(t);}
    List<TRgx> getExprList(){ return List.copyOf(exprs);}
}

class TRgxRepeat implements TRgx{
    private final TRgx expr;
    TRgxRepeat(TRgx t){ expr= t;}
    TRgx getExprList(){ return expr;}
}