package ProcessAssembly;
import ImportAssembly.HexBinConverter;
import ImportAssembly.DecHexConverter;  
import java.util.Arrays; 

/**
 * Stores Hex and Binary opcodes to compare them to the binary input arrays. 
 * 
 * @author Ingrid Rumbaugh 
 * @version (a version number or a date)
 */
public class Opcodes {
    String addiOPCODE = "488"; // these are in hex --> InstructionFormat method 
    String addOPCODE = "458";
    String subOPCODE = "658";
    String subiOPCODE = "688";
    String mulOPCODE = "4D8"; 
    String movzOPCODE = "694"; 
    String ldurOPCODE = "7C2";
    String sturOPCODE = "7C0";
    String lslOPCODE = "69B"; 
    String lsrOPCODE = "69A"; 

    String addiTYPE = "I";
    String subiTYPE = "I";

    String addTYPE = "R";
    String subTYPE = "R";
    String mulTYPE = "R";
    String lslTYPE = "R";
    String lsrTYPE = "R";

    String movzTYPE = "IW";
    String ldurTYPE = "D";
    String sturTYPE = "D";

    String type; // Will either be I, R, D, IW type 
    HexBinConverter hbc = new HexBinConverter(); 
    String opcode;

    public void determineType(int[] binary) {
        int start = binary.length-1; 
        int[] tempOPcode = new int[11]; 
        opcode = ""; 
        int j = 0; 
        for (int i = start; i > start-11; i --) {
            tempOPcode[j] = binary[i]; 
            opcode += tempOPcode[j];
            j ++; 
        }
        opcode = reverseString(opcode); 
        //opcode += "0";
        // Save as a string and then convert to hex
        opcode = hbc.convertBinaryToHexadecimal(opcode); 

        // Check against MOVZ, IW type 
        if (opcode.contains("694")) {
            type = "movz";
            // This is for D type commands 
        } else if (opcode.contains("7c2")) {
            type = "ldur";
        } else if (opcode.contains("7c0")) {
            type = "stur";
            // This is for an R type command (below)
        } else if (opcode.contains("458")) {
            type = "add";
        } else if (opcode.contains("658")) {
            type = "sub";
        } else if (opcode.contains("4d8")) {
            type = "mul";
        } else if (opcode.contains("69b")) {
            type = "lsl";
        } else if (opcode.contains("69a")) {
            type = "lsr"; 
        }
        // ELSE: then this is a type I command 
        else {
            opcode = "";
            // only save first 10 bits for opcode because it is type I 
            int k = 0; 
            for (int i = start; i > start-10; i --) {
                tempOPcode[k] = binary[i];
                opcode += tempOPcode[k];
                k++;
            }
            opcode = reverseString(opcode); 
            opcode += "0";
            opcode = hbc.convertBinaryToHexadecimal(opcode); // In case I want to check the opcodes later 

            if (opcode.contains("488")) {
                type = "addi";
            } else if (opcode.contains("688")) {
                type = "subi"; 
            }
        }
    }

    public String reverseString(String str) {
        StringBuilder in1 = new StringBuilder(); 
        in1.append(str); 
        in1 = in1.reverse(); 
        return in1.toString();
    }

    public String getOpcode() {
        return opcode; 
    }

    public String getType() {
        return type;   
    }
}
