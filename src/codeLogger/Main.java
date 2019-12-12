package codeLogger;




public class Main {
    /**
     * main method
     * @param args args
     */
    public static void main(String[] args) throws Exception{
        
        TokenType t1 = TokenType.BIT_AND_OP;
        System.out.println(t1.endOfMatch("&"));
        
        System.out.println(Lexer.tokenizeFileByLine("src/codeLogger/test2.ms"));
    }
}
