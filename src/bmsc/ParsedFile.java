package bmsc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static api.antlr4.MinispecParser.*;
import parser.ParserResult;



/**
 * A representation of parsed file
 * This module tries to mimic original Minispec parse.cpp
 * @author boomza654
 *
 */
public class ParsedFile {
    
    public String fileName;
    public String path;
    public String code;
    public Map<String,ParsedFile> imports;
    public ParserResult parserResult; 
    
    
    /**
     * Parse a file (but haven't linked the imports yet)
     * @param fileName to parse
     * @param path to filename
     * @throws IOException on error
     */
    private ParsedFile(String fileName, String path) throws IOException {
        System.out.println("Parsing "+path+fileName);
        this.parserResult= ParserResult.fromFileName(path+fileName);
        this.fileName=fileName;
        if(!path.endsWith("/")) path+="/";
        this.path=path;
        this.code=String.join("\n",Files.readAllLines(Paths.get(path+fileName)));
    }
    
    
    @Override
    public String toString() {
        return String.format("%s%s importing %s", path,fileName,imports.keySet().toString());
    }
    
    
    /**
     * 
     * @param p the parse Result of a file
     * @return list of all imports (non bsv) from that file name
     */
    private static List<String> getImportListFromParserResult(ParserResult p) {
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
    /**
     * DFS through file imports tree and return the parsed file
     * @param fileName start parsing filename must ends with ".ms"
     * @param path starting path
     * 
     * @param outTopoSortedParsedFiles a list to store the output topo sroted list
     * @param outAlreadyParsedFileMap a map that tores all Parsed File so far
     * @param alreadyVisitedFileNames Hash set maintaining which file has been visited already
     * @throws IOException
     */
    private static void parseFilesDFS(
            String fileName, 
            String path, 
            ArrayList<ParsedFile> outTopoSortedParsedFiles,
            HashMap<String, ParsedFile> outAlreadyParsedFileMap,
            HashSet<String> alreadyVisitedFileNames) throws IOException{
        if(alreadyVisitedFileNames.contains(fileName)) return; // DFS stop
        alreadyVisitedFileNames.add(fileName);
        ParsedFile currentParsedFile = new ParsedFile(fileName,path);
        List<String> importedNames= getImportListFromParserResult(currentParsedFile.parserResult);
        Map<String,ParsedFile> importedParsedFiles = new HashMap<>();
        for(String importName : importedNames) {
            String importFileName = importName+".ms";
            parseFilesDFS(importFileName,path,outTopoSortedParsedFiles,outAlreadyParsedFileMap,alreadyVisitedFileNames);
            importedParsedFiles.put(importName, outAlreadyParsedFileMap.get(importName));
        }
        currentParsedFile.imports=importedParsedFiles;
        System.out.println("Finished Setting Import :"+ currentParsedFile.toString());
        outAlreadyParsedFileMap.put(fileName.substring(0,fileName.length()-3),currentParsedFile);
        outTopoSortedParsedFiles.add(currentParsedFile);
        
    }
    
    /**
     * assert no import cycle
     * @param topoSortedParsedFiles a topologically sortedimport (first one have highest in the tree)
     * @param allParsedFiles map of all parsed Fiels
     */
    private static void assertNoImportCycle(List<ParsedFile> topoSortedParsedFiles, Map<String,ParsedFile> allParsedFiles) {
        for(int i=0;i<topoSortedParsedFiles.size();i++) {
            for(int j=0;j<i;j++) {
                String dstFileName=topoSortedParsedFiles.get(j).fileName;
                boolean backwardPointing= topoSortedParsedFiles.get(i).imports.containsKey(dstFileName);
                assert (!backwardPointing): "Cyclic import occurred";
            }
            
        }
    }
    /**
     * 
     * @param startingFileName start parsing filename
     * @param path starting path
     * @return list of all parsed files sorted in topological order (with the last one being the original file)
     * @throws IOException
     */
    public static List<ParsedFile> parseAndSortAllFilesStartingAt(String startingFileName, String path) throws IOException{
        ArrayList<ParsedFile> topoSortedParsedFiles = new ArrayList<>();
        HashMap<String, ParsedFile> allParsedFiles = new HashMap<>();
        parseFilesDFS(startingFileName, path, topoSortedParsedFiles, allParsedFiles, new HashSet<>());
        assertNoImportCycle(topoSortedParsedFiles, allParsedFiles);
        return topoSortedParsedFiles;
        
    }
    
    
    
}
