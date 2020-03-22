package bmsc;

import java.io.IOException;
import java.util.*;


/**
 * Translate minispec files into synthesizable Bluespec
 * @author boomza654
 *
 */
public class BluespecTranslator {
    
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
        String fullInFileName = path+inFileName;
        String fullOutFileName= path+"out.bsv";
        try {
            Object x= (ParsedFile.parseAndSortAllFilesStartingAt(inFileName, path));
            System.out.println(x);
        } catch( IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Run the program with given arg
     * @param args the givne args
     */
    public static void main(String[] args) {
        final String[] debugArgs=new String[] {"-p","input_dir/part3","Processor.ms", "MultipliersDebug.ms"};
        run(debugArgs);
    }
}