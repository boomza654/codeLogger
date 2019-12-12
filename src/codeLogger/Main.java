package codeLogger;

import java.io.*;
import java.util.*;


public class Main {
    /**
     * main method
     * @param args args
     */
    public static void main(String[] args) throws Exception{
        Scanner fileReader = new Scanner(new File("src/codeLogger/test.ms"));
        StringBuffer currentBuf= new StringBuffer();
        while(fileReader.hasNextLine()) {
            currentBuf.append(fileReader.nextLine()+"\n");
            //System.out.println("Buf:\n"+currentBuf);
            boolean found=true;
            do {
                found=false;
                for(Token t: Token.values()) {
                    int index = t.endOfMatch(currentBuf);
                    if(index!=-1) {
                        String token = currentBuf.substring(0, index);
                        System.out.print(t.toString()+":"+token+", ");
                        currentBuf.delete(0,index);
                        found=true;
                        break;
                    }
                }
            }while(found);
            
        }
        fileReader.close();
        if(currentBuf.length()!=0)
            System.out.print("Unmatched:"+currentBuf);
    }
}
