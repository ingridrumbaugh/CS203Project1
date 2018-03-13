package ProcessAssembly;
import ImportAssembly.Register;
import ImportAssembly.HexBinConverter;
import ImportAssembly.DecHexConverter; 

/**
 * Write a description of class Commands here.
 * 
 * @author Ingrid Rumbaugh 
 * @version 1/9/2018
 */
public class Commands {
    String command;
    int immediate;
    int rdNumber; 
    int rnNumber; 
    int rmNumber;
    int rtNumber;
    int dtNumber;
    int opNumber; 
    int movNumber;

    String rd;
    String rn; 
    String rm; 
    String imm;
    String mov;

    String rt; 
    String dt;
    String op; 

    /**
     * Save the binary string that corresponds to a command. 
     * This string will be 32 bits.
     */
    public void getWholeCommand(String comm) {
        command = comm;
    }

    public void unpackIformat() {
        char tempRD[] = new char[5]; 
        char tempRN[] = new char[5];
        char tempIM[] = new char[12];
        char temp[] = new char[32]; 
        // Bit 0 -4  RD
        // Bit 5 -9  RN
        // Bit 10-21 IMM
        // Bit 22-31 OP <-- Don't worry about these bits 
        temp = command.toCharArray(); 
        for (int i = 0; i < 5; i ++) {
            tempRD[i] = temp[i]; 
        }
        int m = 0;
        for (int j = 5; j < 10; j ++) {   
            tempRN[m] = temp[j]; 
            m ++;
        }
        int n = 0; 
        for (int k = 10; k < 22; k ++) {
            tempIM[n] = temp[k]; 
            n++;
        }

        rd = String.valueOf(tempRD); 
        rn = String.valueOf(tempRN); 
        imm = String.valueOf(tempIM); 
        rd = reverseString(rd); 
        rd += "0"; 
        rn = reverseString(rn); 
        rn += "0";
        imm = reverseString(imm); 
        imm += "0"; 
        
        rdNumber = Integer.parseInt(rd, 2); 
        rnNumber = Integer.parseInt(rn, 2); 
        immediate = Integer.parseInt(imm, 2); 

        rd = Integer.toString(rdNumber); 
        rn = Integer.toString(rnNumber); 
        imm = Integer.toString(immediate); 
    }
    
    public String reverseString(String str) {
        StringBuilder in1 = new StringBuilder(); 
        in1.append(str); 
        in1 = in1.reverse(); 
        return in1.toString();
    }
    
    public void unpackRformat() {
        char tempRD[] = new char[5]; 
        char tempRN[] = new char[5];
        char tempRM[] = new char[5];
        char temp[] = new char[32]; 
        // Bit 0 -4  RD
        // Bit 5 -9  RN
        // Bit 16-20 RM
        // Bit 21-31 OP <-- Don't worry about these bits 
        temp = command.toCharArray(); 
        for (int i = 0; i < 5; i ++) {
            tempRD[i] = temp[i]; 
        }
        int m = 0; 
        for (int j = 5; j < 10; j ++) {
            tempRN[m] = temp[j]; 
            m ++;
        }
        int n = 0; 
        for (int k = 16; k < 21; k ++) {
            tempRM[n] = temp[k]; 
            n ++;
        }

        rd = String.valueOf(tempRD); 
        rd = reverseString(rd); 
        rd += "0"; 
        rn = String.valueOf(tempRN); 
        rn = reverseString(rn);
        rn += "0"; 
        rm = String.valueOf(tempRM); 
        rm = reverseString(rm); 
        rm += "0"; 

        rdNumber = Integer.parseInt(rd, 2); 
        rnNumber = Integer.parseInt(rn, 2); 
        rmNumber = Integer.parseInt(rm, 2); 

        rd = Integer.toString(rdNumber); 
        rn = Integer.toString(rnNumber); 
        rm = Integer.toString(rmNumber); 
    }

    public void unpackDformat() {
        char tempRT[] = new char[5]; 
        char tempRN[] = new char[5];
        char tempOP[] = new char[2];
        char tempDT[] = new char[8];
        char temp[] = new char[32]; 
        // Bit 0-4 RT
        // Bit 5-9 RN
        // Bit 10-11 OP
        // Bit 12-20 DT 
        // Bit 21-31 OP <-- Don't worry about these bits 
        temp = command.toCharArray(); 
        for (int i = 0; i < 5; i ++) {
            tempRT[i] = temp[i]; 
        }
        int m = 0; 
        for (int j = 5; j < 10; j ++) {
            tempRN[m] = temp[j]; 
            m ++;
        }
        int n = 0; 
        for (int l = 10; l < 12; l++) {
            tempOP[n] = temp[l]; 
            n ++;
        }
        int u = 0; 
        for (int k = 12; k < 21; k ++) {
            tempDT[u] = temp[k]; 
            u ++;
        }

        rt = String.valueOf(tempRT); 
        rn = String.valueOf(tempRN); 
        op = String.valueOf(tempOP); 
        dt = String.valueOf(tempDT); 
        
        rt = reverseString(rt); 
        rt += "0";
        rn = reverseString(rn); 
        rn += "0";
        op = reverseString(op); 
        op += "0"; 
        dt = reverseString(dt); 
        dt += "0"; 
        
        rtNumber = Integer.parseInt(rt, 2); 
        rnNumber = Integer.parseInt(rn, 2); 
        opNumber = Integer.parseInt(op, 2); 
        dtNumber = Integer.parseInt(dt, 2); 

        rt = Integer.toString(rtNumber); 
        rn = Integer.toString(rnNumber);
        op = Integer.toString(opNumber);
        dt = Integer.toString(dtNumber); 
    }

    public void unpackIWformat() {
        char tempRD[] = new char[5]; 
        char tempMOV[] = new char[16];
        char temp[] = new char[32]; 
        // Bit 0 -4  RD
        // Bit 5 -20 RN
        // Bit 21-31 OP <-- Don't worry about these bits 
        temp = command.toCharArray(); 
        for (int i = 0; i < 5; i ++) {
            tempRD[i] = temp[i]; 
        }
        int m = 0; 
        for (int j = 5; j < 21; j ++) {
            tempMOV[m] = temp[j]; 
            m ++;
        }
        
        rd = String.valueOf(tempRD); 
        mov = String.valueOf(tempMOV); 

        rd = reverseString(rd); 
        rd += "0"; 
        mov = reverseString(mov); 
        mov += "0"; 
        
        rdNumber = Integer.parseInt(rd, 2); 
        movNumber = Integer.parseInt(mov, 2);  

        rd = Integer.toString(rdNumber); 
        mov = Integer.toString(movNumber);
    }   

    
    public void addi(){ 
        System.out.println("ADDI Command Successfully Called"); 
        unpackIformat();
    }

    public void add() { 
        System.out.println("ADD Called"); 
        unpackRformat();
    } 

    public void sub() { 
        unpackRformat();
    }

    public void subi(){ 
        unpackIformat();
    }

    public void mul() {  
        unpackRformat();
    }

    public void movz(){ 
        System.out.println("MOVZ Called"); 
        unpackIWformat(); 
    }

    public void ldur(){
        System.out.println("LDUR Called"); 
        unpackDformat();
    }

    public void stur(){  
        unpackDformat(); 
    }

    public void lsl() { 
        unpackRformat();
    }

    public void lsr() { 
        unpackRformat();
    }
}
