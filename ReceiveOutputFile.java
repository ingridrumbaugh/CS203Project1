package ProcessAssembly;
import ImportAssembly.HexBinConverter;
import ImportAssembly.DecHexConverter; 

import java.util.*;
import java.io.*; 

/**
 * Write a description of class ReceiveOutputFile here.
 * 
 * @author Ingrid Rumbaugh 
 * @version 1/6/2018
 */
public class ReceiveOutputFile {
    Scanner sc; 

    String commandHEX;
    String binarycommand;
    int[] currentcommandBIN;
    char[] allHEXcommands = new char[0]; 
    char[] currentcommandHEX = new char[8]; 
    List<Integer> binaryarguments = new ArrayList<Integer>(); 

    DecHexConverter dhc = new DecHexConverter(); 
    HexBinConverter hbc = new HexBinConverter(); 
    UnpackHex h = new UnpackHex(); 
    Execute exec = new Execute(); 
    
    public void readOFile(String dataFile) {
        try {
            sc = new Scanner(new FileReader(dataFile)); 

            while (sc.hasNext()) {
                // This is the whole hex command 
                commandHEX = sc.next(); 
                System.out.println("Command HEX: "+commandHEX);
                allHEXcommands = commandHEX.toCharArray();
                getCurrentHEXCommand(allHEXcommands);
            }
        } catch (Exception e) {
            System.out.println("Error Occurred in ReceiveOutputFile"); 
            System.out.println(e);
        }

    }

    public void getCurrentHEXCommand(char[] temp) {
        int numiterations = (temp.length)/8;
        // Dilly dilly 
        System.out.println("Number of commands to be parsed: "+numiterations); 
    
        for (int j = 0; j < numiterations; j ++) {
            for (int i = 0; i < 8; i ++) {
                currentcommandHEX[i] = temp[i+(j*8)];
            }
            //System.out.println("J = "+j); 
            System.out.println("Current HEX Command: "); 
            for (int k = 0; k < 8; k ++) { System.out.print(currentcommandHEX[k]); }
            System.out.println(""); 
            String tempcommand = new String(currentcommandHEX);
            //unpackHex(tempcommand);
            h.getCommand(tempcommand); 
            currentcommandBIN = h.getBinaryCommand(); 
            binarycommand = currentcommandBIN.toString();
            exec.determineCommand(currentcommandBIN); 
        }
    }

    public void unpackHex(String cmd) {
        h.getCommand(cmd); 
        currentcommandBIN = h.getBinaryCommand();
    }

}

