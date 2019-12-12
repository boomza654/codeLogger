package codeLogger;

import java.io.*;
import java.util.*;


public class Main {
    /**
     * main method
     * @param args args
     */
    public static void main(String[] args) throws Exception{
        
        Token t1 = Token.BIT_AND_OP;
        System.out.println(t1.endOfMatch("&"));
        
        Scanner fileReader = new Scanner(new File("src/codeLogger/test2.ms"));
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
                        if(t.toString().endsWith("KEYWORD")) token +="&";
                        System.out.print("-->"+token+"<--");
                        currentBuf.delete(0,index);
                        found=true;
                        break;
                    }
                }
            }while(found);
            
        }
        fileReader.close();
        if(currentBuf.length()!=0)
            System.out.print("-->Unmatched:"+currentBuf+"<--");
    }
}
