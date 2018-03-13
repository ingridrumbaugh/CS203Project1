package ImportAssembly;
import java.util.*; 

/**
 * Register class used for storing values and helping manipulate data. 
 * Registers:
 * WZR - (4 BYTE) Zero Register
 * XZR - (8 BYTE) Zero Register
 * WSP - (4 BYTE) Current Stack Pointer 
 * SP  - (8 BYTE) Current Stack Pointer 
 * PC  - (8 BYTE) Program Counter
 * 
 * @author Ingrid Rumbaugh
 * @version 10/4/17
 */
public class Register {
    String regtype;
    int length; 
    int bytelength; 
    Byte b1, b2, b3, b4; // MAX_LENGTH = 4 BYTES 
    Byte[] bytelist; 
    Memory m = new Memory(); 
    List<String> registermemlocations = new ArrayList<String>(); 
    int maxmem;
    int tempaddress; 
    
    /**
     * Register Constructor:
     * Initialize Register given name (type), specified in class description.
     * Also input the bitlength (Max of 4)
     */
    public Register(String type, int bitlength) {
        regtype = type; 
        length = bitlength;
        bytelength = bitlength/8; 
        // if bytelength = 4,3,2,1...
        if(bytelength == 1) { 
            b1 = new Byte(0,0,0,0,0,0,0,0);
            tempaddress = m.getMemLocation(); 
            m.writeMemory(b1);
            registermemlocations.add(type+Integer.toString(tempaddress)); 
        }
        else if(bytelength == 2) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            bytelist = new Byte[2]; 
            bytelist[0] = b1;
            bytelist[1] = b2;
            //registermemlocations.add(type+"1"+Integer.toString(tempadd1));
            //registermemlocations.add(type+"
            m.writeMemory(bytelist); 
        }
        else if (bytelength == 3) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            b3 = new Byte(0,0,0,0,0,0,0,0);
            bytelist = new Byte[3]; 
            bytelist[0] = b1;
            bytelist[1] = b2;
            bytelist[2] = b3; 
            m.writeMemory(bytelist); 
        }
        // Ensures that max byte length is 4 
        else {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            b3 = new Byte(0,0,0,0,0,0,0,0);
            b4 = new Byte(0,0,0,0,0,0,0,0); 
            bytelist = new Byte[4];
            bytelist[0] = b1; 
            bytelist[1] = b2;
            bytelist[2] = b3;
            bytelist[3] = b4; 
            m.writeMemory(bytelist); 
        }
        
        initializeRegisters(); 
    }

    public Register() {
        // don't do anything yet 
        initializeRegisters(); 
    }

    public void setMaxMem(int max) {
        maxmem = max; 
        m.setMaxMemory(maxmem); 
    }

    /**
     * Creates the following Standard Registers
     * WZR  - (2 BYTE) Zero Register
     * XZR  - (4 BYTE) Zero Register
     * WSP  - (2 BYTE) Current Stack Pointer 
     * SP   - (4 BYTE) Current Stack Pointer 
     * PC   - (4 BYTE) Program Counter
     * SPSR - (2 BYTE) Saved Program Status Register (Flags, NZCV)
     */
    public void initializeRegisters() {
        createNewRegister("WZR", 16); 
        createNewRegister("XZR", 32); 
        createNewRegister("WSP", 16); 
        createNewRegister("SP" , 32); 
        createNewRegister("PC" , 32); 
        createNewRegister("SPSR", 16); // Store Flags -- NZCV 
    }

    /**
     * Function that checks if a Register of a certain name has already been created. 
     * Returns TRUE if it does exist, FALSE if it does not. 
     */
    public boolean checkRegisterExists(String name) {
        if(name == regtype) return true;
        else return false;
    }

    /**
     * Creates a new Register, Input name & Number of Bytes. 
     * Initializes the max number of bytes, all assigned to 0.
     */
    public void createNewRegister(String name, int bitlength) {
        regtype = name;
        length = bitlength;
        bytelength = bitlength/8; 
        
        if(bytelength == 1) { 
            b1 = new Byte(0,0,0,0,0,0,0,0);
            m.writeMemory(b1);
        }
        else if(bytelength == 2) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            bytelist = new Byte[2]; 
            bytelist[0] = b1;
            bytelist[1] = b2;
            m.writeMemory(bytelist); 
        }
        else if (bytelength == 3) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            b3 = new Byte(0,0,0,0,0,0,0,0);
            bytelist = new Byte[3]; 
            bytelist[0] = b1;
            bytelist[1] = b2;
            bytelist[2] = b3; 
            m.writeMemory(bytelist); 
        }
        // Ensures that max byte length is 4 
        else {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            b3 = new Byte(0,0,0,0,0,0,0,0);
            b4 = new Byte(0,0,0,0,0,0,0,0); 
            bytelist = new Byte[4];
            bytelist[0] = b1; 
            bytelist[1] = b2;
            bytelist[2] = b3;
            bytelist[3] = b4; 
            m.writeMemory(bytelist); 
        }
        
    }

    /**
     * Writes values to the register. 
     * Given bit position, calculates which byte to write to. 
     * Writes 'value' to that bit. 
     * If out of range, return Arithmetic Exception. 
     */
    public void writeReg(int bitpos, int value) {
        if(bitpos >= 0 || bitpos <= 7) {
            b1.assignBit(bitpos, value); 
            
        } else if (bitpos >= 8 || bitpos <= 15) {
            b2.assignBit(bitpos, value);
        } else if (bitpos >= 16 || bitpos <= 23) {
            b3.assignBit(bitpos, value);
        } else if (bitpos >= 24 || bitpos <= 31) {
            b4.assignBit(bitpos, value); 
        } else {
            throw new ArithmeticException("Invalid Bit Position");   
        } 
    }

    /**
     * Given a certain bit position, 
     * finds the correct byte and returns the value at the given pos.
     */
    public int getReg(int bitpos) {
        if(bitpos >= 0 || bitpos <= 7) {
            return b1.getintbit(bitpos); 
        } else if (bitpos >= 8 || bitpos <= 15) {
            return b2.getintbit(bitpos);
        } else if (bitpos >= 16 || bitpos <= 23) {
            return b3.getintbit(bitpos);
        } else if (bitpos >= 24 || bitpos <= 31) {
            return b4.getintbit(bitpos); 
        } else {
            throw new ArithmeticException("Invalid Bit Position");   
        } 
    }

    //     public String getReg(String name) {
    //         
    //     }
}

