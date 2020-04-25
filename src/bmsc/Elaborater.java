package bmsc;
import api.antlr4.*;
import static bmsc.BBoolean.*;
import static api.antlr4.MinispecParser.*;
import static bmsc.GeneralizedIdentifier.*;

/**
 * Bunch of methods that will perform static elaboration
 * - Loop unroll
 * - If/case Integer
 * - Parametrics unroll
 * 
 * adapted directly fromm elaborator class in translate.cpp
 * @author boomza654
 *
 */
public class Elaborater {


    /**
     * Perform First pass on the content of the parsedfile 
     * registering Types / function / Integer variable outmost / Parametrics into the gidManager
     * @param parsedFile
     * @param gidManager
     */
    public static void firstPassGidRegister(ParsedFile parsedFile, GeneralizedIdentifierManager gidManager) {
        FirstPassGidRegister.firstPass(parsedFile, gidManager);
    }
    /**
     * 
     * @param exprNode needs to be a compile-time unrollable expression
     * @param gidManager
     * @return evaluated expression exprNode
     */
    public static Object evaluateExpressionNode(ExpressionContext exprNode, GeneralizedIdentifierManager gidManager) {
        // Fake expressino evaluation
        return ExpressionEvaluator.evaluate(exprNode, gidManager);
        //throw new RuntimeException("Unimplemented");
        
    }

}





