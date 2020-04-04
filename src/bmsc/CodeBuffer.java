package bmsc;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * To be a FIFO Buffer for writing code down
 * 
 * this is similar to TranslateCode class in original translate.cpp
 * @author boomza654
 *
 */
public class CodeBuffer {
    public final StringBuilder code;
    public CodeBuffer() {code = new StringBuilder();}
    public CodeBuffer(CodeBuffer other) {code = new StringBuilder(other.code.toString());}
    public CodeBuffer(String other) {code = new StringBuilder(other);}
    public void emit(String s) {code.append(s);}
    public void emit(boolean b) {emit(b?"True":"False");}
    public void emit(int x) {emit(x+"");}
    public void emit(CodeBuffer other) {emit(other.code.toString());}
    public void emit(ParserRuleContext p) {emit(p.getText());}
    public void emitLine() {emit("\n");}
}
