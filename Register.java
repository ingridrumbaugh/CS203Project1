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
    int finalindex; // for the hashmap (keeping track of stack)
    String finalvarname; 
    Byte b1, b2, b3, b4; // MAX_LENGTH = 4 BYTES 
    Byte[] bytelist; 
    Memory m = new Memory(); 
    List<String> registermemlocations = new ArrayList<String>(); 
    HashMap<Integer, String> stackvals = new HashMap<Integer, String>(); 
    int maxmem;
    int tempaddress; 
    int address; 
    
    /**
     * Register Constructor:
     * Initialize Register given name (type), specified in class description.
     * Also input the bitlength (Max of 4)
     */
    public Register(String type, int bitlength) {
        regtype = type; 
        length = bitlength;
        bytelength = bitlength/8; 
        // put new registers (not the standard ones) on the stack 
        createNewRegisterStack(type, bitlength); 
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

    public void pushBack() {
        Set set      = stackvals.entrySet(); 
        Iterator itr = set.iterator(); 
        
        while (itr.hasNext()) {
            Map.Entry mapentry = (Map.Entry)itr.next(); 
            Object  varname    = mapentry.getKey(); 
            Object  index      = mapentry.getValue(); 
            
            finalvarname = varname.toString(); 
            finalindex   = Integer.valueOf((String) index);
            int newindex = finalindex + 1; 
            // push everything back one index to keep it on track w the stack 
            stackvals.put(newindex, finalvarname); 
        }
    }
    
    public String getVarName(int index) {
        String var = new String(); 
        var = stackvals.get(index); 
        return var; 
    }
    
    public int getIndex(String varname) {
        String tofind = varname; 
        int found = -5; 
        Set set = stackvals.entrySet(); 
        Iterator itr = set.iterator(); 
        
        while (itr.hasNext()) {
            Map.Entry mapentry = (Map.Entry)itr.next(); 
            Object stringname  = mapentry.getKey(); 
            Object index       = mapentry.getValue();
            
            String finalstringname = stringname.toString(); 
            int finalindex = Integer.valueOf((String) index); 
            if (finalstringname == tofind) {
                found = finalindex; 
                // find first occurrence of varname 
                break;
            }
        }
        
        return found;
    }
    
    public void createNewRegisterStack(String name, int bitlength) {
        regtype = name;
        length = bitlength;
        bytelength = bitlength/8; 
        
        if(bytelength == 1) { 
            b1 = new Byte(0,0,0,0,0,0,0,0);
            m.writeStack(b1);
            pushBack(); 
            stackvals.put(0, name); 
        }
        else if(bytelength == 2) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            m.writeStack(b2); 
            m.writeStack(b1); 
            pushBack(); 
            stackvals.put(0, name); 
            pushBack(); 
            stackvals.put(0, name); 
        }
        else if (bytelength == 3) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            b3 = new Byte(0,0,0,0,0,0,0,0);
            m.writeStack(b3);
            m.writeStack(b2);
            m.writeStack(b1); 
            pushBack(); 
            for (int i = 0; i < 3; i ++) {
                stackvals.put(0, name); 
                pushBack(); 
            }
        }
        // Ensures that max byte length is 4 
        else {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            b3 = new Byte(0,0,0,0,0,0,0,0);
            b4 = new Byte(0,0,0,0,0,0,0,0); 
            m.writeStack(b4); 
            m.writeStack(b3);
            m.writeStack(b2);
            m.writeStack(b1); 
            pushBack(); 
            for (int i = 0; i < 4; i ++) {
                stackvals.put(0, name); 
                pushBack(); 
            }
        }
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
            int tempadd1 = m.getMemLocation();
            registermemlocations.add(name+"1");
            registermemlocations.add(Integer.toString(tempadd1)); 
            m.writeMemory(b1);
        }
        else if(bytelength == 2) {
            b1 = new Byte(0,0,0,0,0,0,0,0);
            b2 = new Byte(0,0,0,0,0,0,0,0);
            bytelist = new Byte[2]; 
            bytelist[0] = b1;
            bytelist[1] = b2;
            int tempadd1 = m.getMemLocation();
            int tempadd2 = tempadd1+1;
            registermemlocations.add(name+"1");
            registermemlocations.add(Integer.toString(tempadd1)); 
            registermemlocations.add(name+"2");
            registermemlocations.add(Integer.toString(tempadd2)); 

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
            int tempadd1 = m.getMemLocation();
            int tempadd2 = tempadd1+1;
            int tempadd3 = tempadd2+1; 
            registermemlocations.add(name+"1");
            registermemlocations.add(Integer.toString(tempadd1)); 
            registermemlocations.add(name+"2");
            registermemlocations.add(Integer.toString(tempadd2)); 
            registermemlocations.add(name+"3");
            registermemlocations.add(Integer.toString(tempadd3)); 
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
            int tempadd1 = m.getMemLocation();
            int tempadd2 = tempadd1+1;
            int tempadd3 = tempadd2+1; 
            int tempadd4 = tempadd3+1; 
            registermemlocations.add(name+"1");
            registermemlocations.add(Integer.toString(tempadd1)); 
            registermemlocations.add(name+"2");
            registermemlocations.add(Integer.toString(tempadd2)); 
            registermemlocations.add(name+"3");
            registermemlocations.add(Integer.toString(tempadd3)); 
            registermemlocations.add(name+"4");
            registermemlocations.add(Integer.toString(tempadd4)); 
            m.writeMemory(bytelist); 
        }
        
    }

    /**
     * Writes values to the register. 
     * Given bit position, calculates which byte to write to. 
     * Writes 'value' to that bit. 
     * If out of range, return Arithmetic Exception. 
     */
    public void writeReg(String name, int bitpos, int value) {
        if(bitpos >= 0 || bitpos <= 7) {
            b1.assignBit(bitpos, value); 
            address = getIndex(name); 
            m.changeStack(b1, address); 
        } else if (bitpos >= 8 || bitpos <= 15) {
            b2.assignBit(bitpos, value);
            address = getIndex(name); 
            m.changeStack(b2, address+1); 
        } else if (bitpos >= 16 || bitpos <= 23) {
            b3.assignBit(bitpos, value);
            address = getIndex(name);
            m.changeStack(b3, address+2); 
        } else if (bitpos >= 24 || bitpos <= 31) {
            b4.assignBit(bitpos, value); 
            address = getIndex(name); 
            m.changeStack(b4, address+3); 
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
}

