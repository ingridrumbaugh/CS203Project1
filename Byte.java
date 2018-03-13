package ImportAssembly;

import java.util.*; 

/**
 * This class is used to create and manipulate Bytes made of type Bit. 
 * Bytes are implemented in multiple places throughout the program. 
 * 
 * @author Ingrid Rumbaugh
 * @version 10/4/17
 */
public class Byte {
    private Bit b1;
    private Bit b2; 
    private Bit b3;
    private Bit b4; 
    private Bit b5;
    private Bit b6;
    private Bit b7;
    private Bit b8; 
    private int array[] = new int[8];

    /**
     * Constructor for type Byte.
     * Creates 8 new bits and assigns them the given value.
     */
    public Byte(int a, int b, int c, int d, int e, int f, int g, int h) {
        b1 = new Bit(a);
        array[0] = b1.getbit(); 
        b2 = new Bit(b);
        array[1] = b2.getbit();
        b3 = new Bit(c); 
        array[2] = b3.getbit();
        b4 = new Bit(d); 
        array[3] = b4.getbit(); 
        b5 = new Bit(e);  
        array[4] = b5.getbit();
        b6 = new Bit(f);  
        array[5] = b6.getbit(); 
        b7 = new Bit(g);  
        array[6] = b7.getbit(); 
        b8 = new Bit(h); 
        array[7] = b8.getbit(); 
    }

    public Byte() {
        
    }
    
    /**
     * Reserves 8 bits - setting boolean 'occupied' to true. 
     */
    public void reserveByte() {
        b1.reserveBit(); 
        b2.reserveBit();
        b3.reserveBit(); 
        b4.reserveBit();
        b5.reserveBit();
        b6.reserveBit();
        b7.reserveBit();
        b8.reserveBit(); 
    }
    
    /**
     * Returns the bit value of the specified position (0-7). 
     */
    public int getByte(int bitpos) {
        return array[bitpos]; 
    }

    /**
     * Given a certain position within a Byte, returns type Bit.
     * Returns the object, Bit, not the int associated with that object. 
     * If value is outside of range 0-7 bits, return NULL. 
     */
    public Bit getbitbit(int bitpos) {
        if (bitpos == 0) return b1;
        if (bitpos == 1) return b2;
        if (bitpos == 2) return b3;
        if (bitpos == 3) return b4; 
        if (bitpos == 4) return b5;
        if (bitpos == 5) return b6;
        if (bitpos == 6) return b7; 
        if (bitpos == 7) return b8; 
        else { return null; }
    }

    /**
     * Calls deleteBit() for each bit within the Byte. 
     */
    public void deleteByte() {
        b1.deleteBit();
        b2.deleteBit(); 
        b3.deleteBit();
        b4.deleteBit();
        b5.deleteBit(); 
        b6.deleteBit();
        b7.deleteBit();
        b8.deleteBit();
    }
    
    /**
     * Only deletes a certain bit within the Byte, given the bit position (0-7). 
     * See documentation on bit class for more information. 
     */
    public void deleteByte(int bitpos) {
        if (bitpos == 0) {
            b1.deleteBit();
        }
        else if (bitpos == 1) {
            b2.deleteBit();
        }
        else if (bitpos == 2) {
            b3.deleteBit();
        }
        else if (bitpos == 3) { 
            b4.deleteBit();
        }
        else if (bitpos == 4) {
            b5.deleteBit();
        }
        else if (bitpos == 5) {
            b6.deleteBit();
        }
        else if (bitpos == 6) {
            b7.deleteBit();
        }
        else if (bitpos == 7) {
            b8.deleteBit(); 
        }
    } 

    /**
     * Assigns a certain bit position a value of 0 or 1. 
     * If the value is not 0 or 1 an error will be thrown from the Bit class. 
     */
    public void assignBit(int bitpos, int val) {
        if (bitpos == 0) {
            b1.assignBit(val);
        }
        else if (bitpos == 1) {
            b2.assignBit(val);
        }
        else if (bitpos == 2) {
            b3.assignBit(val);
        }
        else if (bitpos == 3) {
            b4.assignBit(val); 
        }
        else if (bitpos == 4) {
            b5.assignBit(val);
        }
        else if (bitpos == 5) {
            b6.assignBit(val);
        }
        else if (bitpos == 6) {
            b7.assignBit(val); 
        }
        else if (bitpos == 7) {
            b8.assignBit(val); 
        }   
    }

    /**
     * Creates a new object of type Byte. 
     * All 8 bit values (in order) are passed through the function. 
     */
    public void writeByte(int a, int b, int c, int d, int e, int f, int g, int h) {
        b1.assignBit(a); 
        b2.assignBit(b);
        b3.assignBit(c);
        b4.assignBit(d);
        b5.assignBit(e);
        b6.assignBit(f);
        b7.assignBit(g);
        b8.assignBit(h);
    }
    
    /**
     * Given a certain position within a Byte, returns the int value associated with that Bit.
     * Returns the value of the object, Bit, not the object itself. 
     * If value is outside of range 0-7 bits, return -1. 
     */
    public int getintbit(int bitpos) {
        if (bitpos == 0) return b1.getbit();
        else if (bitpos == 1) return b2.getbit(); 
        else if (bitpos == 2) return b3.getbit();
        else if (bitpos == 3) return b4.getbit(); 
        else if (bitpos == 4) return b5.getbit(); 
        else if (bitpos == 5) return b6.getbit(); 
        else if (bitpos == 6) return b7.getbit(); 
        else if (bitpos == 7) return b8.getbit(); 
        else { return -1; } 
    }
}

