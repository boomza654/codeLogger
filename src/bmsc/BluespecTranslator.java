package bmsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import api.antlr4.MinispecParser;
import parser.ParserResult;

import static api.antlr4.MinispecParser.*;


/**
 * Translate minispec files into synthesizable Bluespec
 * @author boomza654
 *
 */
public class BluespecTranslator {
    public static String MinispecPrelude = 
            "/* Minispec prelude -- automatically prepended to every Minispec file */\n" + 
            "import Vector::*;  // In Minispec, Vector is a basic type\n" + 
            "\n" + 
            "// Minispec doesn't separate module and interface names, so use typedefs to\n" + 
            "// allow using some of the BSV Prelude modules with different interface names\n" + 
            "typedef Reg#(t) RegU#(type t);\n" + 
            "typedef Wire#(t) BypassWire#(type t);\n" + 
            "typedef Wire#(t) DWire#(type t);\n" + 
            "/* End of Minispec prelude */\n"+
            "\n"+
            "// The following code is trs=anslated using bmsc, Boomza654's version of msc that support\n"+
            "// Allowing better synthesization of module";
    public static void showHelp() {
        final String helpMessage = "usage: [option] input_filename topmodule \n\n" + 
                "Result : Will translate top_module of file_name in ms into synthesizable bsv \n\n"+
                "  -h,--help                               Print help message\n" + 
                "  -p,--path  <directory_path>             Set default path of compilation\n"+
                "\n\n";
        System.out.println(helpMessage);
        return;
    }
    public static void showError() {

        final String errorMessage = "Type -h,--help option to gain more info";
        System.out.println(errorMessage);
        return;
    }
    /**
     * The core of trasnaltion
     * @param path
     * @param inFileName
     * @param moduleName
     */
    public static void translate(String path, String inFileName,String moduleName) {

        String fullInFileName = path+inFileName;
        String fullOutFileName= path+"out.bsv";
        // Parse all file and its dependency
        List<ParsedFile> parsedFiles = null;
        ParserResult topLevelModule=null;
        ParserResult topLevelFunc=null;
        try {
            parsedFiles= (ParsedFile.parseAndSortAllFilesStartingAt(inFileName, path));
            try {
                topLevelModule=ParserResult.moduleFromString(moduleName);
                System.out.println("Top level module :"+topLevelModule.parseTree().getText());
            } catch(Exception e) {
                topLevelModule=null;
            }
            try {
                topLevelFunc=ParserResult.funcFromString(moduleName);
                System.out.println("Top level function :"+topLevelFunc.parseTree().getText());
            } catch(Exception e) {
                topLevelFunc=null;
            }
            assert topLevelModule==null || topLevelFunc==null : "Error parsing module / function name";
            
        } catch( IOException e){
            throw new RuntimeException(e);
        }
        // Register all identifiers into the manager

        List<Object> synthQueue= new LinkedList<Object>();
        GeneralizedIdentifierManager gidManager = new GeneralizedIdentifierManager(synthQueue);
        // get all bsv import first
        for(ParsedFile parsedFile:parsedFiles) {
            PackageDefContext p = (PackageDefContext)parsedFile.parserResult.parseTree(); 
            for(PackageStmtContext ps: p.packageStmt()) {
                if(ps.bsvImportDecl()!=null) synthQueue.add(ps.bsvImportDecl());
            }
        }
        for(ParsedFile parsedFile:parsedFiles) {
            Utility.println("Start Registering Variables/Types/Functions/Parametrics in file: "+ parsedFile.fileName);
            Elaborater.firstPassGidRegister(parsedFile, gidManager);
        }
        // register our current module / func
        if(topLevelModule!=null) {
            GidExtracter.extractGidAndRegisterType((MinispecParser.TypeContext)topLevelModule.parseTree(), gidManager);
        }
        if(topLevelFunc!=null) {
            GidExtracter.extractGidAndRegisterFunc((MinispecParser.VarExprContext)topLevelFunc.parseTree(), gidManager);
        }
        Utility.println("Finish Registering Variables/Types/functions/Parametrics");
        Utility.println(gidManager);
        Utility.println("SynthQueue:");
        for(Object e: synthQueue)
            Utility.println("\t"+e);
        Translator translator = new Translator(gidManager);
        
        // OPen file for writing
        try ( PrintWriter p=new PrintWriter(fullOutFileName);) {
            p.println(MinispecPrelude);
            for(int i=0;i<synthQueue.size();i++) { // get all outer most types
                    Object toSynth = synthQueue.get(i);
                    String code="";
                    if(toSynth instanceof Type) code=translator.translateType((Type)toSynth);
                    else if (toSynth instanceof Variable) code=translator.translateVar((Variable)toSynth);
                    else if (toSynth instanceof Func) code=translator.translateFunc((Func)toSynth);
                    else if (toSynth instanceof BsvImportDeclContext) {
                        BsvImportDeclContext bsvimport = (BsvImportDeclContext)toSynth;
                        code=translator.translateBSVImport(bsvimport);
                    }
                    else continue;
                    p.println(code); // emit code
            }
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Translation complete at :"+ fullOutFileName);
        
    }
    /**
     * run this thing with given arg (perserve main for Testing)
     * @param args the fileName and the topModule
     */
    public static void run(String[] args) {
        System.out.println("Input argument : "+Arrays.toString(args));
        String path="";
        String moduleName="";
        String inFileName="";
        if(args.length<2) {
            showError();
            return;
        }
        for(int i=0;i<args.length;i++) {
            if(args[i].equals("-h") || args[i].equals("--help")) {
                showHelp();
                return;
            }
            else if (args[i].equals("-p") || args[i].equals("--path")) {
                if(i==args.length-1) {
                    showError();
                    System.out.println("No Path specified");
                    return;
                }
                path=args[i+1];
                if(!path.endsWith("/"))
                    path+="/";
                i++;
            }
            else {
                if(i<args.length-2) {
                    showError();
                    System.out.println("Unknown option" + args[i]);
                    return;
                } else if (i>args.length-2) {
                    showError();
                    System.out.println("No module name specified");
                    return;
                }
                else {
                    inFileName=args[i];
                    moduleName=args[i+1];
                    i++;
                }
            }
        }
        
        if(!inFileName.endsWith(".ms")) {
            System.out.println("Unknown file format: non-ms");
            return;
        }
        translate(path, inFileName, moduleName);
    }
    /**
     * Run the program with given arg
     * @param args the givne args
     */
    public static void main(String[] args) {
        final String[] debugArgs=new String[] {"-p","D:\\work\\KingScholar\\MIT\\6.004 fa19\\lab6\\lab6","Processor.ms", "add"};
        run(args);
    }
}
