package ImportAssembly;

import java.util.*; 

/**
 * Bit is a data type used throughout the program. 
 * Represented as an int: 1 or 0  
 * 
 * @author Ingrid Rumbaugh
 * @version 10/4/17
 */
public class Bit {
    private int bit; 
    private boolean occupied; 
    
    /**
     * Bit data type constructor 
     * Creates a new bit with the assigned value.
     * If the value is not 0 or 1, an exception is thrown. 
     * Uses boolean 'occupied' to indicate that the bit has been assigned a value.
     */
    public Bit(int b) {
        if (b != 0 && b != 1) {
            throw new ArithmeticException("Error Writing Bit: Not 1 or 0");
        } else {
            bit = b; 
            occupied = true;
        }
    }

    /**
     * Assigns a new value to an already existing bit.
     * If the value is not 0 or 1, an exception is thrown. 
     * Uses boolean 'occupied' to indicate that the bit has been assigned a value.
     */
    public void assignBit(int newbit) {
        if (newbit != 0 && newbit != 1) {
            throw new ArithmeticException("Error Writing Bit: Not 1 or 0"); 
        } else {
            bit = newbit; 
            occupied = true;
        }
    }

    /**
     * Sets the value of the bit = 0.
     * Sets boolean 'occupied' to false. 
     */
    public void deleteBit() {
        bit = 0; 
        occupied = false;
    }
    
    /**
     * Only sets boolean 'occupied' to true, but does not assign a value. 
     */
    public void reserveBit() {
        occupied = true; 
    }
    
    /**
     * Returns the value of a bit.
     */
    public int getbit() {
        return bit; 
    }
}
