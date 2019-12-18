package tokenParser;

import java.io.*;
import java.util.*;


/**
 * Create a tokenizer that tokenize string into tokens
 * @author boomza654
 *
 */
public class Lexer {
    
    
    /**
     * Read from file line by line until EOF and tokenize each line (transform all line break to \n)
     * @param uri
     * @return List of list of tokenized word out[i][j] = j+1th token of the i+1th line
     * @throws IOException
     */
    public static List<List<TokenizedWord>> tokenizeFileByLine(final String uri) throws IOException {
        try (Scanner fileReader = new Scanner(new File(uri));) {
            final List<List<TokenizedWord>> out = new ArrayList<>();
            final StringBuffer currentBuf = new StringBuffer();
            int row = 1;
            while (fileReader.hasNextLine()) {
                currentBuf.append(fileReader.nextLine() + "\n");
                out.add(tokenize(currentBuf, row, 1));
                row++;
            }

            if (currentBuf.length() != 0)
                throw new IllegalArgumentException("File does not match all tokens" + currentBuf + "@ line" + row);
            return out;
        } catch (final FileNotFoundException e) {
            throw new IOException("Cannot open file somehow");
        }

    }

    /**
     * 
     * @param s   input String Buffer (will be mutated) s cannot be more than 1 line
     * @param row curent row of s Column is treated as starting at 1
     * @return a list of tokenized word The left over is in s
     */
    public static List<TokenizedWord> tokenize(final StringBuffer s, final int row) {
        return tokenize(s, row, 1);
    }

    /**
     * 
     * @param s      input String Buffer (will be mutated) s cannot be more than 1
     *               line
     * @param row    curent row of s
     * @param column starting column of the current buffer
     * @return a list of tokenized word The left over is in s
     */
    public static List<TokenizedWord> tokenize(final StringBuffer s, final int row, final int column) {
        int curColumn = column;
        final List<TokenizedWord> out = new ArrayList<>(); // can change to linked list for better performance
        boolean found;
        do {
            found = false;
            for (final TokenType t : TokenType.values()) {
                final int index = t.endOfMatch(s);
                if (index != -1) {
                    final String token = s.substring(0, index);
                    out.add(new TokenizedWord(t,token,0,curColumn));
                    curColumn+=token.length();
                    s.delete(0,index);
                    found=true;
                    break;
                }
            }
        }while(found);
        return out;
    }
}
