package ImportAssembly;

/**
 * Defines the instruction code formats to be used in the Assembler Functions. 
 * Creates an array of type Byte for each format length. 
 * 
 * @author Ingrid Rumbaugh 
 * @version 10/16/17
 */
public class InstructionFormat {
    String rtype = "R";
    String itype = "I";
    String dtype = "D";
    String iwtype = "IW"; 
    // Main program instatiates this and assigns memory from there
    // can't instatiate more than one memory class 
    /**
     * Instruction Format Constructor: 
     * Creates an array of type Byte for each format length.
     * Formats for OpCodes are: "R", "I", "D", and "IW" type. 
     */
    public InstructionFormat() {
        /*
         * R FORMAT --> 32 Bits (4 BYTE)
         * 11 bit (21-31): Opcode
         * 5 bit (16-20): Rm
         * 6 bit (10-15): Shamt
         * 5 bit (5-9): Rn
         * 5 bit (0-4): Rd
         */
        Byte[] rformat = new Byte[16];
       
        /*
         * I FORMAT
         * 10 bit (22-31): Opcode
         * 12 bit (10-21): ALU_Immediate 
         * 5 bit (5-9): Rn
         * 5 bit (0-4): Rd
         */
        Byte[] iformat = new Byte[16]; 
        /*
         * D FORMAT
         * 11 bit(21-31): Opcode
         * 9 bit (12-20): DT_Address
         * 2 bit (10-11): Op
         * 5 bit (5-9): Rn
         * 5 bit (0-4): Rt
         */
        Byte[] dformat = new Byte[16]; 
        /*
         * IW FORMAT 
         * 11 bit (31-21): Opcode
         * 16 bit (5-20): MOV_Immediate 
         * 5 bit (0-4): Rd
         */
        Byte[] iwformat = new Byte[16]; 
    }
}




