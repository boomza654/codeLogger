package bmsc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import api.antlr4.MinispecParser;
import parser.ParserResult;

import static api.antlr4.MinispecParser.*;
import org.json.simple.*;
import org.json.simple.parser.*;

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
            "// The following code is translated using bmsc, Boomza654's version of msc that support\n"+
            "// better synthesization of module\n"+
            "// Please Visit https://github.com/boomza654/codeLogger for github repo\n";
    public static void showHelp() {
        final String helpMessage = "usage: [option] input_filename [topmodule, topfunction] \n\n" + 
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
     * Run command cmd
     * @param args to run
     * 
     * @verbose to show on screen or not
     * @return the text from running such Command
     */
    public static String runCmd(List<String> args, boolean verbose) {
        ProcessBuilder pb = new ProcessBuilder(args);
        Process p;
        String out="";
        System.out.println("Try Running: "+String.join(" ", args));
        try {
            p = pb.start();

            BufferedReader reader=new BufferedReader(new  InputStreamReader(p.getInputStream()));
            BufferedReader errReader=new BufferedReader(new  InputStreamReader(p.getErrorStream()));
            
            String text = reader.readLine();
            while(text!=null){
                if(verbose)
                System.out.println(text);
                out+=text+"\n";
                text=reader.readLine();
            }
            boolean hasError= false;
            String errText = errReader.readLine();
            String errTextTotal="";
            while(errText!=null){
                errTextTotal+=errText;
                hasError=true;
                errText=errReader.readLine();
            }
            reader.close();
            errReader.close();
            if(hasError) {throw new RuntimeException("Process terminated with error : "+errTextTotal);}
        } catch (IOException e) {
            throw new RuntimeException("Cant run Command " +args ,e);
        } 
        System.out.println("Finish run Cmd");
        return out;
    }
    /**
     * Parse JSON file and retain only this module" module
     * @param fullInFileName the input JSON
     * @param fullOutFileName the output JSON
     * @param module the module to retain
     */
    public static void stripJSON(String fullInFileName, String fullOutFileName, String module) {
        JSONObject jo;
        try {
            jo=(JSONObject) new JSONParser().parse(new FileReader(fullInFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not Found",e);
        } catch (IOException e) {
            throw new RuntimeException("Cant open file",e);
        } catch (ParseException e) {
            throw new RuntimeException("cant parse JSON",e);
        } 
        JSONObject modules = ((JSONObject)jo.get("modules"));
        Set<String> moduleSet= new HashSet<>();
        for(Object obj:modules.keySet()) {
            moduleSet.add((String)obj);
        }
        for(String module_name:moduleSet) {
            if(module_name.equals(module)) continue;
            modules.remove(module_name);
        }
        try(PrintWriter pw = new PrintWriter(fullOutFileName);){
           pw.println(jo.toJSONString());
           pw.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not Found",e);
        }

        
    }
    /**
     * The core of trasnaltion
     * @param path
     * @param inFileName
     * @param moduleName
     * @return the  top level translated module name
     */
    public static String translate(String path, String inFileName,String moduleName) {

        String fullInFileName = path+inFileName;
        String fullOutFileName= path+"out.bsv";
        GeneralizedIdentifier gid=null;
        String outModuleName=null;
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
        Translator translator = new Translator(gidManager);
        // register our current module / func
        if(topLevelModule!=null) {
            gid=GidExtracter.extractGidAndRegisterType((MinispecParser.TypeContext)topLevelModule.parseTree(), gidManager);
            outModuleName= gid.toProperModuleString(gidManager);
        }
        if(topLevelFunc!=null) {
            gid=GidExtracter.extractGidAndRegisterFunc((MinispecParser.VarExprContext)topLevelFunc.parseTree(), gidManager);
            synthQueue.add(translator.translateToToplevelFunc(gid));
            outModuleName="module_"+gid.toStringEscapeParametric();
        }
        Utility.println("Finish Registering Variables/Types/functions/Parametrics");
        Utility.println(gidManager);
        Utility.println("SynthQueue:");
        for(Object e: synthQueue)
            Utility.println("\t"+e);
        
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
                    else if (toSynth instanceof String) code= (String)toSynth;
                    else continue;
                    p.println(code); // emit code
                    //Utility.println(code);
            }
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Translation complete at :"+ fullOutFileName);
        System.out.println("Top Level Module is : "+ outModuleName);
        return outModuleName;
        
    }
    /**
     * run this thing with given arg (perserve main for Testing)
     * @param args the fileName and the topModule
     */
    public static void run(String[] args) {
        System.out.println("Input argument : "+Arrays.toString(args));
        String path="";
        String moduleName=null;
        String inFileName=null;

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
                path=path.replace('\\', '/');
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
        if(inFileName==null) {
            System.out.println("No given input file");
            return;
        }
        if(moduleName==null) {
            moduleName="";
            System.out.println("Warning: No top level module /function is given");
        }
        if(!inFileName.endsWith(".ms")) {
            System.out.println("Unknown file format: non-ms");
            return;
        }
        String outModuleName=translate(path, inFileName, moduleName); // write to out.bsv file

        runCmd(List.of("bash","-c", "cd "+ path+"; bsc -verilog -g "+outModuleName+" -u out.bsv"),true);
        runCmd(List.of("bash","-c", "cd "+ path+"; yosys -p \"read_verilog *.v; proc; opt; wreduce; opt; wreduce; opt; wreduce; opt; write_json out.json\""),false);
        stripJSON(path+"out.json", path+"out1.json", outModuleName);
        runCmd(List.of("bash","-c", "cd "+ path+"; netlistsvg out1.json"),true);
        System.out.println("Finish visualizing "+ outModuleName+"\nOutput files are at "+path+"out.svg");
        
    }
    /**
     * Run the program with given arg
     * @param args the givne args
     */
    public static void main(String[] args) {
        final String[] debugArgs=new String[] {"-p","D:\\work\\KingScholar\\MIT\\6.004 fa19\\minispec_debugger\\codeLogger\\input_dir\\part3","Processor.ms", "selAdd#(16)"};
        run(args);
    }
}
