package ProcessAssembly;
import ImportAssembly.DecHexConverter;
import ImportAssembly.HexBinConverter; 

/**
 * Write a description of class UnpackHex here.
 * 
 * @author Ingrid Rumbaugh
 * @version 1/6/2018
 */
public class UnpackHex {
    DecHexConverter dhc = new DecHexConverter(); 
    HexBinConverter hbc = new HexBinConverter(); 
    int[] command;
    
    public void getCommand(String c) {
        // Convert hex command string to a binary string 
        String binary = hbc.hexToBinary(c);
        String bintest = hbc.hexToBinTEST(c); 
        // initialize the command array
        command = new int[32];
        // convert binary command string to a char array to parse 
        char[] chstring = bintest.toCharArray(); 
        
        for (int i = 0; i < chstring.length; i++) {
            command[i] = Character.getNumericValue(chstring[i]);
        }
    }
    
    public int[] getBinaryCommand() {
        System.out.println("Command to be parsed: "); 
        for (int k = 0; k < 32; k ++) {
            System.out.print(command[k]);
        }
        System.out.println(""); 
        
        return command;
    }
    
}
