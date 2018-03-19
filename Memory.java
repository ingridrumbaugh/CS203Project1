package ImportAssembly;
import java.util.*;

/**
 * The Memory class creates memory for the simulation by using a Stack. 
 * Reserves Memory for Registers, Flags, etc. 
 * This class can get the address of and store things to memory. 
 * 
 * @author Ingrid Rumbaugh  
 * @version 10/11/17
 */
public class Memory {
    int address;
    String hexaddress;

    String str = "String";
    String character = "Char";
    String integer = "Integer";
    String longs = "Long";
    String floats = "Float"; 

    int maxmemory; 
    // Number of BYTES 
    int strsize = 4; 
    int charsize = 1;
    int intsize = 4; 
    int longsize = 8;
    int floatsize = 8; 

    Byte[] memory; 
    Stack<Byte> memstack;

    /**
     * Memory constructor, given max memory location. 
     * This class creates addresses for each data type added. 
     * The correct amount of bytes are reserved for each data type as well. 
     */
    public Memory(int maxmem) {
        maxmemory = maxmem/8;
        memory = new Byte[maxmemory]; 
        // Current ptr is at the beginning of the array 
        address = java.util.Arrays.asList(memory).indexOf(0); 
        // index is pointer to current address in memory 
        // Create stack and put in array 
        // pick space in memory for stack --> at the end of memory 
        memstack = new Stack<>(); 
        //memory[maxmemory/4] = memstack.peek(); // top of stack starts @ middle of mem location
        for (int i = (63*maxmemory)/64; i < maxmemory-1; i ++) {
            Byte b = new Byte(0,0,0,0,0,0,0,0);
            memory[i] = b; 
            memory[i].reserveByte(); 
        }
        // ^ Reserve stack space in memory 
    }

    public void setMaxMemory(int max) {
        maxmemory = max/8; 
        memory = new Byte[maxmemory]; 
        //address = java.util.Arrays.asList(memory).indexOf(0); 
        address = 0; 
        //memory[maxmemory/4] = memstack.peek();
        // 1/64 of space at end of memory is reserved for the stack 
        for (int i = (63*maxmemory)/64; i < maxmemory-1; i ++) {
            Byte b = new Byte(0,0,0,0,0,0,0,0);
            memory[i] = b; 
            memory[i].reserveByte(); 
        }
    }

    public Memory() {
        memstack = new Stack<>();
    }

    /**
     * Returns the memory location of an object (type int address). 
     */
    public int getMemLocation() {
        return address;
    }

    public void writeStack(Byte b) {
        memstack.push(b); 
    }
    
    public void changeStack(Byte b, int index) {
        memstack.set(index, b); 
    }
    
    /**
     * Max: Writing a 2 Byte Integer to the stack. 
     */
    public void writeStack(int n) {
        String binaryn = Integer.toBinaryString(n);
        char[] ch = binaryn.toCharArray(); 

        Byte b1 = new Byte();
        Byte b2 = new Byte(); 
        for (int j = 0; j < 8; j ++) {
            b1.assignBit(j, Character.getNumericValue(ch[j]));
        }
        memstack.push(b1);
        if (ch.length > 8) {
            for (int k = 8; k < 16; k ++) {
                b2.assignBit(k, Character.getNumericValue(ch[k]));
            }
            memstack.push(b2);
        }
    }

    /**
     * Blank Method - Intended to write a Byte to the memory.
     * This byte would be given an address in memory and saved.
     */
    public void writeMemory(Byte b) {
        int startaddress = address;
        while (memory[startaddress] != null) {
            startaddress++;
        }
        memory[startaddress] = b;  
        // update address 
        address = startaddress; 
    }

    public void writeMemory(Byte b, int memaddress) {
        int startaddress = memaddress;
        while (memory[startaddress] != null) {
            startaddress++;
        }
        memory[startaddress] = b; 
        // don't update address!  this is for writing a specific, already reserved byte in mem
    }
    
    /**
     * Blank Method - Intended to write an array of bytes to memory.
     * These bytes are given an address range and saved into the memory stack. 
     */
    public void writeMemory(Byte[] b) {
        int stringlength = b.length;
        int startaddress = address;
        while (memory[startaddress] != null) {
            startaddress++; 
        }
        for (int i = 0; i < stringlength-1; i ++) {
            memory[startaddress+i] = b[i]; 
        }
        address = startaddress; 
    }

    public List<String> memToHex() {
        List<String> hexmem = new ArrayList<String>(); 
        String[] temparray = new String[4];
        String temphex = new String(); 
        
        for (int i = 0; i < maxmemory; i ++) {
            for (int j = 0; j < maxmemory; j = j*4) {
                temparray[j] = Integer.toString(memory[i+j].getintbit(j));
            }
            temphex = temparray.toString();
            hexmem.add(temphex); 
        }
        return hexmem;
    }
    
    /**
     * Blank Method - Intended to write to a specific address.
     * Given the type (String), and the contents (String), the correct amount of memory will be allocated
     * and stored as well. 
     */
    public void writeMem(String type, String contents) {
        Byte b1 = new Byte();
        Byte b2 = new Byte(); 
        Byte b3 = new Byte();
        Byte b4 = new Byte(); 
        Byte b5 = new Byte(); 
        Byte b6 = new Byte(); 
        Byte b7 = new Byte(); 
        Byte b8 = new Byte(); 
        int startaddress = address;
        while (memory[startaddress] != null) {
            startaddress++;
        }

        if (type == "String" || type == "Char") {
            String temp = contents; 
            // convert string to array of chars 
            char[] chstring = temp.toCharArray(); 
            int ascii;
            int[] intstring = null; // contains ascii value of each character 
            // save each char as an ascii value 
            for (int n = 0; n < chstring.length; n ++) {
                ascii = (int)chstring[n];
                intstring[n] = ascii;
            }
            // Create array of type string to store binary ascii of char 
            String[] stringbinaryascii = new String[intstring.length]; 
            for (int a = 0; a < intstring.length; a ++) {
                // copy contents of ascii array into binary ascii array
                stringbinaryascii[a] = Integer.toBinaryString(intstring[a]);
            }
            // A string is 4 bytes 
            for (int i = 0; i < 4; i ++) {
                for (int p = 0; p < 8; p ++) {
                    if (i == 0) {
                        b1.assignBit(p, Integer.parseInt(stringbinaryascii[p]));
                    } else if (i == 1) {
                        b2.assignBit(p, Integer.parseInt(stringbinaryascii[p]));
                    } else if (i == 2) {
                        b3.assignBit(p, Integer.parseInt(stringbinaryascii[p]));
                    } else if (i == 3) {
                        b4.assignBit(p, Integer.parseInt(stringbinaryascii[p])); 
                    }
                }
                if (i == 0) memory[startaddress] = b1; 
                if (i == 1) memory[startaddress+1] = b2; 
                if (i == 2) memory[startaddress+2] = b3; 
                if (i == 3) {
                    memory[startaddress+3] = b4; 
                    address = startaddress+3; 
                }
            }

        } else if (type == "Int") {
            int temp = Integer.parseInt(contents);
            String bincontents = Integer.toBinaryString(temp); 
            char[] chint = bincontents.toCharArray(); 
            for (int k = 0; k < 4; k ++) {
                for (int l = 0; l < 8; l ++) {
                    if (k == 0) {
                        b1.assignBit(l, Character.getNumericValue(chint[l])); 
                    } else if (k == 1) {
                        b2.assignBit(l, Character.getNumericValue(chint[l])); 
                    } else if (k == 2) {
                        b3.assignBit(l, Character.getNumericValue(chint[l])); 
                    } else {
                        b4.assignBit(l, Character.getNumericValue(chint[l])); 
                    }
                }
                if (k == 0) memory[startaddress] = b1; 
                if (k == 1) memory[startaddress+1] = b2; 
                if (k == 2) memory[startaddress+2] = b3;
                if (k == 3){
                    memory[startaddress+3] = b4; 
                    address = startaddress+3; 
                }
            }
        } else if (type == "Byte") {
            int temp = Integer.parseInt(contents); 
            String bytestring = Integer.toString(temp); 
            char[] chbyte = bytestring.toCharArray();
            for (int m = 0; m < 8; m ++) {
                b1.assignBit(m, Character.getNumericValue(chbyte[m])); 
            }
            memory[startaddress] = b1; 
            address = startaddress; 
        } else if (type == "Long" || type == "Float") {
            int temp2 = Integer.parseInt(contents);
            String bincontents2 = Integer.toBinaryString(temp2); 
            char[] chint2 = bincontents2.toCharArray(); 
            for (int c = 0; c < 8; c ++) {
                for (int d = 0; d < 8; d ++) {
                    if (c == 0) {
                        b1.assignBit(d, Character.getNumericValue(chint2[d])); 
                    } else if (c == 1) {
                        b2.assignBit(d, Character.getNumericValue(chint2[d])); 
                    } else if (c == 2) {
                        b3.assignBit(d, Character.getNumericValue(chint2[d])); 
                    } else if (c == 3) {
                        b4.assignBit(d, Character.getNumericValue(chint2[d])); 
                    } else if (c == 4) {
                        b5.assignBit(d, Character.getNumericValue(chint2[d]));
                    } else if (c == 5) {
                        b6.assignBit(d, Character.getNumericValue(chint2[d]));
                    } else if (c == 6) {
                        b7.assignBit(d, Character.getNumericValue(chint2[d]));
                    } else {
                        b8.assignBit(d, Character.getNumericValue(chint2[d]));
                    }
                }
                if (c == 0) memory[startaddress] = b1; 
                if (c == 1) memory[startaddress+1] = b2; 
                if (c == 2) memory[startaddress+2] = b3;
                if (c == 3) memory[startaddress+3] = b4; 
                if (c == 4) memory[startaddress+4] = b5; 
                if (c == 5) memory[startaddress+5] = b6; 
                if (c == 6) memory[startaddress+6] = b7;
                if (c == 7){
                    memory[startaddress+7] = b8; 
                    address = startaddress+7; 
                }
            }
        } 
    }
}
