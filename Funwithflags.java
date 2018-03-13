package ImportAssembly;
import java.util.*; 

/**
 * For creating and manipulating N, Z, C, V flags.
 * Each flag is one bit.
 * 
 * @author Ingrid Rumbaugh
 * @version 10/4/17
 */
public class Funwithflags {
    Bit nbit; 
    Bit zbit;
    Bit cbit;
    Bit vbit; 
    
    /**
     * Flag constructor: Creates a new bit for each flag, setting them all to 0 initially. 
     */
    public Funwithflags() {
        nbit = new Bit(0);
        zbit = new Bit(0);
        cbit = new Bit(0);
        vbit = new Bit(0); 
    }
    
    /**
     * Set all flags to 0 by assigning each bit to 0. 
     */
    public void unflagall() {
        nbit.assignBit(0);
        zbit.assignBit(0);
        cbit.assignBit(0);
        vbit.assignBit(0); 
    }
    
    /**
     * Given a string input defining flag type, 
     * return the value of the bit associated with that flag.
     */
    public int getflag(String s) {
        if (s == "n" || s == "N") { return nbit.getbit(); }
        if (s == "z" || s == "Z") { return zbit.getbit(); }
        if (s == "c" || s == "C") { return cbit.getbit(); } 
        if (s == "v" || s == "V") { return vbit.getbit(); } 
        else {
            throw new IllegalArgumentException("Illegal String Input"); 
        }
    }
    
    /**
     * Flag the "N" flag (Set the bit = 1). 
     */
    public void nflag() {
        nbit.assignBit(1);
    }
    
    /**
     * Un-Flag the "N" flag (Set the bit = 0). 
     */
    public void nunflag() {
        nbit.assignBit(0);
    }
    
    /**
     * Flag the "Z" flag (Set the bit = 1).
     */
    public void zflag() {
        zbit.assignBit(1); 
    }
    
    /**
     * Un-Flag the "Z" flag (Set the bit = 0). 
     */
    public void zunflag() {
        zbit.assignBit(0); 
    }
    
    /**
     * Flag the "C" flag (Set the bit = 1). 
     */
    public void cflag() {
        cbit.assignBit(1); 
    }
    
    /**
     * Un-Flag the "C" flag (Set the bit = 0). 
     */
    public void cunflag() {
        cbit.assignBit(0);
    }
    
    /**
     * Flag the "V" flag (Set the bit = 1). 
     */
    public void vflag() {
        vbit.assignBit(1); 
    }
    
    /**
     * Un-Flag the "V" flag (Set the bit = 0). 
     */
    public void vunflag() {
        vbit.assignBit(0);
    }
}
