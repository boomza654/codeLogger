package translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static api.antlr4.MinispecParser.*;
import parser.ParserResult;
/**
 * Translator that gets all imports
 * @author boomza654
 *
 */
public class Utility {
    /**
     * 
     * @param fileName file name
     * @return list of all imports (non bsv) from that file name
     * @throws IOException if file not found
     */
    public static List<String> getImportListFromFile(String fileName) throws IOException{
       ParserResult p = ParserResult.fromFileName(fileName);
       List<PackageStmtContext> parts = ((PackageDefContext)p.parseTree()).packageStmt();
       List<String> importedFiles = new ArrayList<>();
       for(PackageStmtContext part:parts) {
           if(part.importDecl()!=null) {
               for(IdentifierContext idc : part.importDecl().identifier()) {
                   importedFiles.add(idc.getText());
               }
           }
       }
       return importedFiles;
    }
}
