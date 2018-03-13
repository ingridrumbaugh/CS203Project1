package ImportAssembly;
import java.util.*; 

/**
 * Creates instance of ReadFiles. 
 * Takes parsed output from the readfiles class. 
 * Translates instructions like ADD and SUB into OpCodes. 
 * Translates the whole instruction, using OpCodes, into binary. 
 * 
 * @author Ingrid Rumbaugh  
 * @version 10/15/17
 */
public class AssemblerFunctions {
    String[] command; 
    List<String> finalHex = new ArrayList<String>();   
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

    Bit[] rformat; 
    Bit[] iformat; 
    Bit[] dformat;
    Bit[] iwformat; 

    // Create instances of instructionformat and hexbinconverter. 
    InstructionFormat formats = new InstructionFormat(); 
    HexBinConverter hbc = new HexBinConverter(); 

    String str1 = new String(); // D 
    String str2 = new String(); // N
    String str3 = new String(); // M 
    int immediate; 

    public void setVariables(String f, String s, String t) {
        str1 = f;
        str2 = s;
        str3 = t;
    }

    public void setVariablesImm(String f, String s, int imm) {
        str1 = f;
        str2 = s;
        str3 = null;
        immediate = imm; 
    }

    /**
     * Used to test that a parsed argument in the readfiles class 
     * can directly call a function. 
     */
    public void add() {
        add(str1, str2, str3); 
    }

    /**
     * Empty Function - 
     * To be called directly from the ReadFiles class instantiation. 
     * Will translate this instruction to binary. 
     */
    public void add(String rdrt, String rn, String rf) {
        System.out.println("Add method called!"); 
        // TYPE: R       OPCODE: 458
        // System.out.println("Type: "+addTYPE);
        // System.out.println("OpCode: "+addOPCODE);
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = rdrt.split(delim); 
        String[] temprn = new String[2];
        temprn = rn.split(delim); 
        String[] temprf = new String[2]; 
        temprf = rf.split(delim); 
        int rdfinal;
        int rnfinal;
        int rffinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        if (temprf[1] == null) rffinal = 0; 
        else rffinal = Integer.parseInt(temprf[1]); 

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("RF: "+rffinal); 
        writeRFormat(addOPCODE, rdfinal, rnfinal, rffinal);
    }

    /**
     * Empty Function - 
     * To be called directly from the ReadFiles class instantiation. 
     * Will call the addi(int x1, int x2, Register r, int imm) function. 
     */
    public void addi() {
        addi(str1, str2, immediate); 
    }

    /**
     * Empty Function -  
     * Will translate this instruction to binary. 
     */
    public void addi(String x1, String x2, int imm) {
        // Use x1, x2, imm 
        System.out.println("Addi method called!"); 
        // TYPE: R       OPCODE: 458
        // System.out.println("Type: "+addTYPE);
        // System.out.println("OpCode: "+addOPCODE);
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = x1.split(delim); 
        String[] temprn = new String[2];
        temprn = x2.split(delim); 

        int rdfinal;
        int rnfinal;
        int immfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        immfinal = imm;

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("IMM: "+immfinal); 
        writeIFormat(addiOPCODE, immfinal, rnfinal, rdfinal); 
    }

    /**
     * Empty Function - 
     * To be called directly from the ReadFiles class instantiation. 
     * Will call the sub(int x1, int x2, Register r, int num) function. 
     */
    public void sub() {
        sub(str1, str2, str3); 
    }

    /**
     * Empty Function - 
     * To be called directly from the ReadFiles class instantiation. 
     * Will translate this instruction to binary. 
     */
    public void sub(String xd, String xn, String xm) {
        System.out.println("Sub Method successfully called!"); 
        String delim = "[x]";

        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 
        String[] temprm = new String[2]; 
        temprm = xm.split(delim); 

        int rdfinal;
        int rnfinal;
        int rmfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        if (temprm[1] == null) rmfinal = 0; 
        else rmfinal = Integer.parseInt(temprm[1]); 

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("RM: "+rmfinal); 
        writeRFormat(addiOPCODE, rmfinal, rnfinal, rdfinal); 
    }

    public void subi() {
        subi(str1, str2, immediate); 
    }

    public void subi(String xd, String xn, int num) {
        System.out.println("SUBI Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 

        int rdfinal;
        int rnfinal;
        int immfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        immfinal = num;

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("IMM: "+immfinal); 
        writeIFormat(addiOPCODE, immfinal, rnfinal, rdfinal); 
    }

    public void movz() {
        System.out.println("MOVZ FIRST METHOD CALLED"); 
        movz(str1, str2); 
    }
    
    public void movz(String xd, String xs) {
        // Copies the source (xs) into the destination (xd)
        System.out.println("MOVZ Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprs = new String[2];
        temprs = xs.split(delim); 

        int rdfinal;
        int rsfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprs[1] == null) rsfinal = 0; 
        else rsfinal = Integer.parseInt(temprs[1]); 

        System.out.println("RD: "+rdfinal); 
        System.out.println("RS: "+rsfinal); 
        writeIWFormat(movzOPCODE, rsfinal, rdfinal); 
    }
    
    public void mul() {
        mul(str1, str2, str3); 
    }

    public void mul(String xd, String xn, String xm) {
        System.out.println("MUL Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 
        String[] temprm = new String[2];
        temprm = xm.split(delim); 

        int rdfinal;
        int rnfinal;
        int rmfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        if (temprm[1] == null) rmfinal = 0; 
        else rmfinal = Integer.parseInt(temprm[1]); 

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("RM: "+rmfinal); 
        writeIFormat(addiOPCODE, rmfinal, rnfinal, rdfinal); 
    }

    public void ldur() {
        ldur(str1, str2, immediate); 
    }

    public void ldur(String xd, String xn, int imm) { 
        System.out.println("LDUR Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 
        // xn --> Array 
        // imm --> Index of array 

        int rdfinal;
        int rnfinal;
        int immfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        immfinal = imm;

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("IMM: "+immfinal); 
        writeDFormat(ldurOPCODE, immfinal, rnfinal, rdfinal); 
    }

    public void stur() {
        stur(str1, str2, immediate); 
    }

    public void stur(String xd, String xn, int imm) {  
        System.out.println("STUR Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 
        // xn --> Array 
        // imm --> Index of array 

        int rdfinal;
        int rnfinal;
        int immfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        immfinal = imm;

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("IMM: "+immfinal); 
        writeDFormat(addiOPCODE, immfinal, rnfinal, rdfinal); 
    }

    public void lsl() {
        lsl(str1, str2, immediate);
    }

    public void lsl(String xd, String xn, int shift) {
        System.out.println("LSL Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 
        // xn --> Array 
        // imm --> Index of array 

        int rdfinal;
        int rnfinal;
        int immfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        immfinal = shift;

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("SHIFT: "+immfinal); 
        writeRFormat(addiOPCODE, immfinal, rnfinal, rdfinal); 
    }

    public void lsr() {
        lsr(str1, str2, immediate); 
    }

    public void lsr(String xd, String xn, int shift) { 
        System.out.println("LSR Method successfully called"); 
        String delim = "[x]";
        // Parse strings and convert to int, then convert to binary 
        String[] temprd = new String[2]; 
        temprd = xd.split(delim); 
        String[] temprn = new String[2];
        temprn = xn.split(delim); 
        // xn --> Array 
        // imm --> Index of array 

        int rdfinal;
        int rnfinal;
        int immfinal; 
        if (temprd[1] == null) rdfinal = 0;
        else rdfinal = Integer.parseInt(temprd[1]); 
        if (temprn[1] == null) rnfinal = 0; 
        else rnfinal = Integer.parseInt(temprn[1]); 
        immfinal = shift;

        System.out.println("RD: "+rdfinal); 
        System.out.println("RN: "+rnfinal); 
        System.out.println("SHIFT: "+immfinal); 
        writeRFormat(addiOPCODE, immfinal, rnfinal, rdfinal); 
    }

    public void binarytohex(Bit[] s) {
        String tohex = new String(); 
        String otherstring = new String();  
        for (int j = 0; j < 8; j ++) {
            int tempindex = j*4;
            //System.out.println("Temp Index: "+tempindex+"  "+"J: "+j); 
            for (int i = 0; i < 4; i ++) {
                otherstring = Integer.toString(s[i+tempindex].getbit());
                tohex += otherstring;
            }
            if (hbc.convertBinaryToHexadecimal(tohex) == "") {
                finalHex.add("0");
            }
            else {
                finalHex.add(hbc.convertBinaryToHexadecimal(tohex));
            }
            tohex = "";
        }
        System.out.println("Final Hex Command: "); 
        for (int k = 0; k < finalHex.size(); k ++) {
            System.out.print(finalHex.get(k)); 
        }
        System.out.println(""); 
        System.out.println("");
    }

    public List<String> finalHex() {
        return finalHex;
    }

    public void writeIFormat(String opcode, int imm, int regn, int regd) {
        iformat = new Bit[32]; 
        String opcodeBin = hbc.hexToBinary(opcode); 
        String immBin = Integer.toBinaryString(imm); 
        String regnBin = Integer.toBinaryString(regn);
        String regdBin = Integer.toBinaryString(regd); 
        int immLength = immBin.length(); 
        int regnLength = regnBin.length();
        int regdLength = regdBin.length(); 
        System.out.println("Binary OPcode: "+opcodeBin);

        Bit opcode1 = new Bit(0); 
        Bit opcode2 = new Bit(0);
        Bit opcode3 = new Bit(0);
        Bit opcode4 = new Bit(0);
        Bit opcode5 = new Bit(0);
        Bit opcode6 = new Bit(0); 
        Bit opcode7 = new Bit(0);
        Bit opcode8 = new Bit(0); 
        Bit opcode9 = new Bit(0);
        Bit opcode10 = new Bit(0);

        Bit imm1 = new Bit(0);
        Bit imm2 = new Bit(0);
        Bit imm3 = new Bit(0);
        Bit imm4 = new Bit(0); 
        Bit imm5 = new Bit(0);
        Bit imm6 = new Bit(0); 
        Bit imm7 = new Bit(0); 
        Bit imm8 = new Bit(0);
        Bit imm9 = new Bit(0); 
        Bit imm10 = new Bit(0); 
        Bit imm11 = new Bit(0); 
        Bit imm12 = new Bit(0);

        Bit regn1 = new Bit(0);
        Bit regn2 = new Bit(0);
        Bit regn3 = new Bit(0); 
        Bit regn4 = new Bit(0); 
        Bit regn5 = new Bit(0);

        Bit rd1 = new Bit(0);
        Bit rd2 = new Bit(0); 
        Bit rd3 = new Bit(0); 
        Bit rd4 = new Bit(0); 
        Bit rd5 = new Bit(0); 

        //int temp = opcodeBin.length() - 8; 
        //int startwriting = 8 - temp;
        /*
         * Write opcode --> R format: Opcode is 11 bits from 21-31
         */
        opcode1.assignBit(Character.getNumericValue(opcodeBin.charAt(0)));
        //System.out.println("Bit 1: "+opcode1.getbit()); 
        opcode2.assignBit(Character.getNumericValue(opcodeBin.charAt(1)));
        //System.out.println("Bit 2: "+opcode2.getbit());
        opcode3.assignBit(Character.getNumericValue(opcodeBin.charAt(2))); 
        //System.out.println("Bit 3: "+opcode3.getbit());
        opcode4.assignBit(Character.getNumericValue(opcodeBin.charAt(3)));
        //System.out.println("Bit 4: "+opcode4.getbit());
        opcode5.assignBit(Character.getNumericValue(opcodeBin.charAt(4)));
        //System.out.println("Bit 5: "+opcode5.getbit());
        opcode6.assignBit(Character.getNumericValue(opcodeBin.charAt(5)));
        //System.out.println("Bit 6: "+opcode6.getbit()); 
        opcode7.assignBit(Character.getNumericValue(opcodeBin.charAt(6)));
        //System.out.println("Bit 7: "+opcode7.getbit()); 
        opcode8.assignBit(Character.getNumericValue(opcodeBin.charAt(7)));
        //System.out.println("Bit 8: "+opcode8.getbit()); 
        opcode9.assignBit(Character.getNumericValue(opcodeBin.charAt(8)));
        //System.out.println("Bit 9: "+opcode9.getbit());
        opcode10.assignBit(Character.getNumericValue(opcodeBin.charAt(9)));
        //System.out.println("Bit 10: "+opcode10.getbit());

        if (regnLength >= 1) {
            regn1.assignBit(Character.getNumericValue(regnBin.charAt(0))); 
            //System.out.println("RN Bit 1: "+regn1.getbit());
        }
        if (regnLength >= 2) {
            regn2.assignBit(Character.getNumericValue(regnBin.charAt(1))); 
            //System.out.println("RN Bit 2: "+regn2.getbit());
        }
        if (regnLength >= 3) {
            regn3.assignBit(Character.getNumericValue(regnBin.charAt(2))); 
            //System.out.println("RN Bit 3: "+regn3.getbit()); 
        }
        if (regnLength >= 4) {
            regn4.assignBit(Character.getNumericValue(regnBin.charAt(3)));
            //System.out.println("RN Bit 4: "+regn4.getbit());
        }
        if (regnLength == 5) {
            regn5.assignBit(Character.getNumericValue(regnBin.charAt(4))); 
            //System.out.println("RN Bit 5: "+regn5.getbit()); 
        }

        if (regdLength >= 1) {
            rd1.assignBit(Character.getNumericValue(regdBin.charAt(0)));
            //System.out.println("RD Bit 1: "+rd1.getbit()); 
        }
        if (regdLength >= 2) {
            rd2.assignBit(Character.getNumericValue(regdBin.charAt(1)));
            //System.out.println("RD Bit 2: "+rd2.getbit()); 
        }
        if (regdLength >= 3) {
            rd3.assignBit(Character.getNumericValue(regdBin.charAt(2)));
            //System.out.println("RD Bit 3: "+rd3.getbit());
        }
        if (regdLength >= 4) {
            rd4.assignBit(Character.getNumericValue(regdBin.charAt(3)));
            //System.out.println("RD Bit 4: "+rd4.getbit());
        }
        if (regdLength == 5) {
            rd5.assignBit(Character.getNumericValue(regdBin.charAt(4)));
            //System.out.println("RD Bit 5: "+rd5.getbit()); 
        }

        if (immLength >= 1) {
            imm1.assignBit(Character.getNumericValue(immBin.charAt(0)));
            //System.out.println("IMM Bit 1: "+imm1.getbit());
        }
        if (immLength >= 2) {
            imm2.assignBit(Character.getNumericValue(immBin.charAt(1)));
            //System.out.println("IMM Bit 2: "+imm2.getbit());
        }
        if (immLength >= 3) {
            imm3.assignBit(Character.getNumericValue(immBin.charAt(2)));
            //System.out.println("IMM Bit 3: "+imm3.getbit());
        }
        if (immLength >= 4) {
            imm4.assignBit(Character.getNumericValue(immBin.charAt(3)));
            //System.out.println("IMM Bit 4: "+imm4.getbit());
        }
        if (immLength >= 5) {
            imm5.assignBit(Character.getNumericValue(immBin.charAt(4)));
            //System.out.println("IMM Bit 5: "+imm5.getbit()); 
        }
        if (immLength >= 6) {
            imm6.assignBit(Character.getNumericValue(immBin.charAt(5)));
            //System.out.println("IMM Bit 6: "+imm6.getbit());
        }
        if (immLength >= 7) {
            imm7.assignBit(Character.getNumericValue(immBin.charAt(6)));
            //System.out.println("IMM Bit 7: "+imm7.getbit());
        }
        if (immLength >= 8) {
            imm8.assignBit(Character.getNumericValue(immBin.charAt(7)));
            //System.out.println("IMM Bit 8: "+imm8.getbit());
        }
        if (immLength >= 9) {
            imm9.assignBit(Character.getNumericValue(immBin.charAt(8)));
            //System.out.println("IMM Bit 9: "+imm9.getbit()); 
        }
        if (immLength >= 10) {
            imm10.assignBit(Character.getNumericValue(immBin.charAt(9)));
            //System.out.println("IMM Bit 10: "+imm10.getbit()); 
        }
        if (immLength >= 11) {
            imm11.assignBit(Character.getNumericValue(immBin.charAt(10)));
            //System.out.println("IMM Bit 11: "+imm11.getbit());
        }
        if (immLength == 12) {
            imm12.assignBit(Character.getNumericValue(immBin.charAt(11)));
            //System.out.println("IMM Bit 12: "+imm12.getbit());
        }

        iformat[0] = rd1;
        iformat[1] = rd2; 
        iformat[2] = rd3; 
        iformat[3] = rd4; 
        iformat[4] = rd5; 

        iformat[5] = regn1; 
        iformat[6] = regn2; 
        iformat[7] = regn3;
        iformat[8] = regn4;
        iformat[9] = regn5; 

        iformat[10] = imm1;
        iformat[11] = imm2;
        iformat[12] = imm3; 
        iformat[13] = imm4;
        iformat[14] = imm5; 
        iformat[15] = imm6; 
        iformat[16] = imm7; 
        iformat[17] = imm8;
        iformat[18] = imm9;
        iformat[19] = imm10; 
        iformat[20] = imm11;
        iformat[21] = imm12;

        iformat[22] = opcode1;
        iformat[23] = opcode2;
        iformat[24] = opcode3; 
        iformat[25] = opcode4; 
        iformat[26] = opcode5;
        iformat[27] = opcode6;
        iformat[28] = opcode7;
        iformat[29] = opcode8;
        iformat[30] = opcode9;
        iformat[31] = opcode10;

        binarytohex(iformat);
    }

    public void writeRFormat(String opcode, int regm, int regn, int regd) {
        rformat = new Bit[32]; 
        String opcodeBin = hbc.hexToBinary(opcode); 
        String regmBin = Integer.toBinaryString(regm); 
        String regnBin = Integer.toBinaryString(regn);
        String regdBin = Integer.toBinaryString(regd); 
        int regmLength = regmBin.length(); 
        int regnLength = regnBin.length();
        int regdLength = regdBin.length(); 
        System.out.println("Binary OPcode: "+opcodeBin);

        Bit opcode1 = new Bit(0); // rformat[31]
        Bit opcode2 = new Bit(0);
        Bit opcode3 = new Bit(0);
        Bit opcode4 = new Bit(0);
        Bit opcode5 = new Bit(0);
        Bit opcode6 = new Bit(0); 
        Bit opcode7 = new Bit(0);
        Bit opcode8 = new Bit(0); 
        Bit opcode9 = new Bit(0);
        Bit opcode10 = new Bit(0);
        Bit opcode11 = new Bit(0); 

        Bit rm1 = new Bit(0); 
        Bit rm2 = new Bit(0);
        Bit rm3 = new Bit(0);
        Bit rm4 = new Bit(0);
        Bit rm5 = new Bit(0); 

        Bit shamt1 = new Bit(0);
        Bit shamt2 = new Bit(0);
        Bit shamt3 = new Bit(0);
        Bit shamt4 = new Bit(0);
        Bit shamt5 = new Bit(0);
        Bit shamt6 = new Bit(0); 

        Bit regn1 = new Bit(0);
        Bit regn2 = new Bit(0);
        Bit regn3 = new Bit(0); 
        Bit regn4 = new Bit(0); 
        Bit regn5 = new Bit(0);

        Bit rd1 = new Bit(0);
        Bit rd2 = new Bit(0); 
        Bit rd3 = new Bit(0); 
        Bit rd4 = new Bit(0); 
        Bit rd5 = new Bit(0); 

        //int temp = opcodeBin.length() - 8; 
        //int startwriting = 8 - temp;
        /*
         * Write opcode --> R format: Opcode is 11 bits from 21-31
         */
        opcode1.assignBit(Character.getNumericValue(opcodeBin.charAt(0)));
        //System.out.println("Bit 1: "+opcode1.getbit()); 
        opcode2.assignBit(Character.getNumericValue(opcodeBin.charAt(1)));
        //System.out.println("Bit 2: "+opcode2.getbit());
        opcode3.assignBit(Character.getNumericValue(opcodeBin.charAt(2))); 
        //System.out.println("Bit 3: "+opcode3.getbit());
        opcode4.assignBit(Character.getNumericValue(opcodeBin.charAt(3)));
        //System.out.println("Bit 4: "+opcode4.getbit());
        opcode5.assignBit(Character.getNumericValue(opcodeBin.charAt(4)));
        //System.out.println("Bit 5: "+opcode5.getbit());
        opcode6.assignBit(Character.getNumericValue(opcodeBin.charAt(5)));
        //System.out.println("Bit 6: "+opcode6.getbit()); 
        opcode7.assignBit(Character.getNumericValue(opcodeBin.charAt(6)));
        //System.out.println("Bit 7: "+opcode7.getbit()); 
        opcode8.assignBit(Character.getNumericValue(opcodeBin.charAt(7)));
        //System.out.println("Bit 8: "+opcode8.getbit()); 
        opcode9.assignBit(Character.getNumericValue(opcodeBin.charAt(8)));
        //System.out.println("Bit 9: "+opcode9.getbit());
        opcode10.assignBit(Character.getNumericValue(opcodeBin.charAt(9)));
        //System.out.println("Bit 10: "+opcode10.getbit());
        opcode11.assignBit(Character.getNumericValue(opcodeBin.charAt(10)));
        //System.out.println("Bit 11: "+opcode11.getbit());
        if (regmLength >= 1) {
            rm1.assignBit(Character.getNumericValue(regmBin.charAt(0)));
            //System.out.println("RM Bit 1: "+rm1.getbit()); 
        }   
        if (regmLength >= 2) {
            rm2.assignBit(Character.getNumericValue(regmBin.charAt(1))); 
            //System.out.println("RM Bit 2: "+rm2.getbit()); 
        }
        if (regmLength >= 3) {
            rm3.assignBit(Character.getNumericValue(regmBin.charAt(2))); 
            //System.out.println("RM Bit 3: "+rm3.getbit());
        } 
        if (regmLength >= 4) {
            rm4.assignBit(Character.getNumericValue(regmBin.charAt(3))); 
            //System.out.println("RM Bit 4: "+rm4.getbit()); 
        }
        if (regmLength == 5) {
            rm5.assignBit(Character.getNumericValue(regmBin.charAt(4))); 
            //System.out.println("RM Bit 5: "+rm5.getbit()); 
        }

        if (regnLength >= 1) {
            regn1.assignBit(Character.getNumericValue(regnBin.charAt(0))); 
            //System.out.println("RN Bit 1: "+regn1.getbit());
        }
        if (regnLength >= 2) {
            regn2.assignBit(Character.getNumericValue(regnBin.charAt(1))); 
            //System.out.println("RN Bit 2: "+regn2.getbit());
        }
        if (regnLength >= 3) {
            regn3.assignBit(Character.getNumericValue(regnBin.charAt(2))); 
            //System.out.println("RN Bit 3: "+regn3.getbit()); 
        }
        if (regnLength >= 4) {
            regn4.assignBit(Character.getNumericValue(regnBin.charAt(3)));
            //System.out.println("RN Bit 4: "+regn4.getbit());
        }
        if (regnLength == 5) {
            regn5.assignBit(Character.getNumericValue(regnBin.charAt(4))); 
            //System.out.println("RN Bit 5: "+regn5.getbit()); 
        }

        if (regdLength >= 1) {
            rd1.assignBit(Character.getNumericValue(regdBin.charAt(0)));
            //System.out.println("RD Bit 1: "+rd1.getbit()); 
        }
        if (regdLength >= 2) {
            rd2.assignBit(Character.getNumericValue(regdBin.charAt(1)));
            //System.out.println("RD Bit 2: "+rd2.getbit()); 
        }
        if (regdLength >= 3) {
            rd3.assignBit(Character.getNumericValue(regdBin.charAt(2)));
            //System.out.println("RD Bit 3: "+rd3.getbit());
        }
        if (regdLength >= 4) {
            rd4.assignBit(Character.getNumericValue(regdBin.charAt(3)));
            //System.out.println("RD Bit 4: "+rd4.getbit());
        }
        if (regdLength == 5) {
            rd5.assignBit(Character.getNumericValue(regdBin.charAt(4)));
            //System.out.println("RD Bit 5: "+rd5.getbit()); 
        }

        rformat[0] = rd1; 
        rformat[1] = rd2; 
        rformat[2] = rd3; 
        rformat[3] = rd4; 
        rformat[4] = rd5; 
        
        rformat[5] = regn1; 
        rformat[6] = regn2; 
        rformat[7] = regn3; 
        rformat[8] = regn4; 
        rformat[9] = regn5; 
        
        rformat[10] = shamt1; 
        rformat[11] = shamt2;
        rformat[12] = shamt3;
        rformat[13] = shamt4;
        rformat[14] = shamt5;
        rformat[15] = shamt6;
        
        rformat[16] = rm1; 
        rformat[17] = rm2; 
        rformat[18] = rm3; 
        rformat[19] = rm4;
        rformat[20] = rm5; 
        
        rformat[21] = opcode1;
        rformat[22] = opcode2; 
        rformat[23] = opcode3;
        rformat[24] = opcode4;
        rformat[25] = opcode5;
        rformat[26] = opcode6;
        rformat[27] = opcode7;
        rformat[28] = opcode8;
        rformat[29] = opcode9;
        rformat[30] = opcode10;
        rformat[31] = opcode11; 
        
        binarytohex(rformat);
    }

    public void writeDFormat(String opcode, int dtAdd, int regn, int regt) {
        dformat = new Bit[32]; 
        String opcodeBin = hbc.hexToBinary(opcode); 
        String dtBin = Integer.toBinaryString(dtAdd); 
        String regnBin = Integer.toBinaryString(regn);
        String regtBin = Integer.toBinaryString(regt); 
        int dtLength = dtBin.length(); 
        int regnLength = regnBin.length(); 
        int regtLength = regtBin.length(); 
        System.out.println("Binary OPcode: "+opcodeBin);

        Bit opcode1 = new Bit(0); 
        Bit opcode2 = new Bit(0);
        Bit opcode3 = new Bit(0);
        Bit opcode4 = new Bit(0);
        Bit opcode5 = new Bit(0);
        Bit opcode6 = new Bit(0); 
        Bit opcode7 = new Bit(0);
        Bit opcode8 = new Bit(0); 
        Bit opcode9 = new Bit(0);
        Bit opcode10 = new Bit(0);
        Bit opcode11 = new Bit(0); 

        Bit dt1 = new Bit(0);
        Bit dt2 = new Bit(0);
        Bit dt3 = new Bit(0); 
        Bit dt4 = new Bit(0); 
        Bit dt5 = new Bit(0); 
        Bit dt6 = new Bit(0);
        Bit dt7 = new Bit(0); 
        Bit dt8 = new Bit(0);
        Bit dt9 = new Bit(0); 

        Bit op1 = new Bit(0);
        Bit op2 = new Bit(0); 

        Bit regn1 = new Bit(0);
        Bit regn2 = new Bit(0);
        Bit regn3 = new Bit(0); 
        Bit regn4 = new Bit(0); 
        Bit regn5 = new Bit(0);

        Bit rt1 = new Bit(0);
        Bit rt2 = new Bit(0); 
        Bit rt3 = new Bit(0); 
        Bit rt4 = new Bit(0); 
        Bit rt5 = new Bit(0); 

        //int temp = opcodeBin.length() - 8; 
        //int startwriting = 8 - temp;
        /*
         * Write opcode --> R format: Opcode is 11 bits from 21-31
         */
        opcode1.assignBit(Character.getNumericValue(opcodeBin.charAt(0)));
        //System.out.println("Bit 1: "+opcode1.getbit()); 
        opcode2.assignBit(Character.getNumericValue(opcodeBin.charAt(1)));
        //System.out.println("Bit 2: "+opcode2.getbit());
        opcode3.assignBit(Character.getNumericValue(opcodeBin.charAt(2))); 
        //System.out.println("Bit 3: "+opcode3.getbit());
        opcode4.assignBit(Character.getNumericValue(opcodeBin.charAt(3)));
        //System.out.println("Bit 4: "+opcode4.getbit());
        opcode5.assignBit(Character.getNumericValue(opcodeBin.charAt(4)));
        //System.out.println("Bit 5: "+opcode5.getbit());
        opcode6.assignBit(Character.getNumericValue(opcodeBin.charAt(5)));
        //System.out.println("Bit 6: "+opcode6.getbit()); 
        opcode7.assignBit(Character.getNumericValue(opcodeBin.charAt(6)));
        //System.out.println("Bit 7: "+opcode7.getbit()); 
        opcode8.assignBit(Character.getNumericValue(opcodeBin.charAt(7)));
        //System.out.println("Bit 8: "+opcode8.getbit()); 
        opcode9.assignBit(Character.getNumericValue(opcodeBin.charAt(8)));
        //System.out.println("Bit 9: "+opcode9.getbit());
        opcode10.assignBit(Character.getNumericValue(opcodeBin.charAt(9)));
        //System.out.println("Bit 10: "+opcode10.getbit());
        opcode11.assignBit(Character.getNumericValue(opcodeBin.charAt(10)));
        //System.out.println("Bit 11: "+opcode11.getbit()); 

        if (dtLength >= 1) {
            dt1.assignBit(Character.getNumericValue(dtBin.charAt(0)));
            //System.out.println("DT Bit 1: "+dt1.getbit()); 
        }
        if (dtLength >= 2) {
            dt2.assignBit(Character.getNumericValue(dtBin.charAt(1)));
            //System.out.println("DT Bit 2: "+dt2.getbit());
        }
        if (dtLength >= 3) {
            dt3.assignBit(Character.getNumericValue(dtBin.charAt(2)));
            //System.out.println("DT Bit 3: "+dt3.getbit());
        }
        if (dtLength >= 4) {
            dt4.assignBit(Character.getNumericValue(dtBin.charAt(3)));
            //System.out.println("DT Bit 4: "+dt4.getbit());
        }
        if (dtLength >= 5) {
            dt5.assignBit(Character.getNumericValue(dtBin.charAt(4)));
            //System.out.println("DT Bit 5: "+dt5.getbit());
        }
        if (dtLength >= 6) {
            dt6.assignBit(Character.getNumericValue(dtBin.charAt(5)));
            //System.out.println("DT Bit 6: "+dt6.getbit());
        }
        if (dtLength >= 7) {
            dt7.assignBit(Character.getNumericValue(dtBin.charAt(6)));
            //System.out.println("DT Bit 7: "+dt7.getbit());
        }
        if (dtLength >= 8) {
            dt8.assignBit(Character.getNumericValue(dtBin.charAt(7)));
            //System.out.println("DT Bit 8: "+dt8.getbit());
        }
        if (dtLength == 9) {
            dt9.assignBit(Character.getNumericValue(dtBin.charAt(8)));
            //System.out.println("DT Bit 9: "+dt9.getbit());
        }

        if (regnLength >= 1) {
            regn1.assignBit(Character.getNumericValue(regnBin.charAt(0))); 
            //System.out.println("RN Bit 1: "+regn1.getbit());
        }
        if (regnLength >= 2) {
            regn2.assignBit(Character.getNumericValue(regnBin.charAt(1))); 
            //System.out.println("RN Bit 2: "+regn2.getbit());
        }
        if (regnLength >= 3) {
            regn3.assignBit(Character.getNumericValue(regnBin.charAt(2))); 
            //System.out.println("RN Bit 3: "+regn3.getbit()); 
        }
        if (regnLength >= 4) {
            regn4.assignBit(Character.getNumericValue(regnBin.charAt(3)));
            //System.out.println("RN Bit 4: "+regn4.getbit());
        }
        if (regnLength == 5) {
            regn5.assignBit(Character.getNumericValue(regnBin.charAt(4))); 
            //System.out.println("RN Bit 5: "+regn5.getbit()); 
        }

        if (regtLength >= 1) {
            rt1.assignBit(Character.getNumericValue(regtBin.charAt(0)));
            //System.out.println("RD Bit 1: "+rt1.getbit()); 
        }
        if (regtLength >= 2) {
            rt2.assignBit(Character.getNumericValue(regtBin.charAt(1)));
            //System.out.println("RD Bit 2: "+rt2.getbit()); 
        }
        if (regtLength >= 3) {
            rt3.assignBit(Character.getNumericValue(regtBin.charAt(2)));
            //System.out.println("RD Bit 3: "+rt3.getbit());
        }
        if (regtLength >= 4) {
            rt4.assignBit(Character.getNumericValue(regtBin.charAt(3)));
            //System.out.println("RD Bit 4: "+rt4.getbit());
        }
        if (regtLength == 5) {
            rt5.assignBit(Character.getNumericValue(regtBin.charAt(4)));
            //System.out.println("RD Bit 5: "+rt5.getbit()); 
        }

        dformat[0] = rt1;
        dformat[1] = rt2; 
        dformat[2] = rt3;
        dformat[3] = rt4;
        dformat[4] = rt5; 

        dformat[5] = regn1;
        dformat[6] = regn2;
        dformat[7] = regn3;
        dformat[8] = regn4;
        dformat[9] = regn5;

        dformat[10] = op1;
        dformat[11] = op2;

        dformat[12] = dt1; 
        dformat[13] = dt2; 
        dformat[14] = dt3;
        dformat[15] = dt4;
        dformat[16] = dt5;
        dformat[17] = dt6;
        dformat[18] = dt7;
        dformat[19] = dt8;
        dformat[20] = dt9;

        dformat[21] = opcode1;
        dformat[22] = opcode2; 
        dformat[23] = opcode3;
        dformat[24] = opcode4;
        dformat[25] = opcode5; 
        dformat[26] = opcode6; 
        dformat[27] = opcode7;
        dformat[28] = opcode8;
        dformat[29] = opcode9;
        dformat[30] = opcode10; 
        dformat[31] = opcode11;
        
        binarytohex(dformat);
    }

    public void writeIWFormat(String opcode, int movImm, int regd) {
        iwformat = new Bit[32]; 
        String opcodeBin = hbc.hexToBinary(opcode); 
        String movBin = Integer.toBinaryString(movImm);
        String regdBin = Integer.toBinaryString(regd);
        int movLength = movBin.length();
        int regdLength = regdBin.length();
        System.out.println("Binary OPcode: "+opcodeBin);

        Bit opcode1 = new Bit(0); 
        Bit opcode2 = new Bit(0);
        Bit opcode3 = new Bit(0);
        Bit opcode4 = new Bit(0);
        Bit opcode5 = new Bit(0);
        Bit opcode6 = new Bit(0); 
        Bit opcode7 = new Bit(0);
        Bit opcode8 = new Bit(0); 
        Bit opcode9 = new Bit(0);
        Bit opcode10 = new Bit(0);
        Bit opcode11 = new Bit(0); 

        Bit mov1 = new Bit(0);
        Bit mov2 = new Bit(0);
        Bit mov3 = new Bit(0);
        Bit mov4 = new Bit(0);
        Bit mov5 = new Bit(0); 
        Bit mov6 = new Bit(0); 
        Bit mov7 = new Bit(0);
        Bit mov8 = new Bit(0);
        Bit mov9 = new Bit(0);
        Bit mov10 = new Bit(0);
        Bit mov11 = new Bit(0);
        Bit mov12 = new Bit(0);
        Bit mov13 = new Bit(0);
        Bit mov14 = new Bit(0);
        Bit mov15 = new Bit(0);
        Bit mov16 = new Bit(0); 

        Bit rd1 = new Bit(0);
        Bit rd2 = new Bit(0); 
        Bit rd3 = new Bit(0); 
        Bit rd4 = new Bit(0); 
        Bit rd5 = new Bit(0); 

        //int temp = opcodeBin.length() - 8; 
        //int startwriting = 8 - temp;
        /*
         * Write opcode --> R format: Opcode is 11 bits from 21-31
         */
        opcode1.assignBit(Character.getNumericValue(opcodeBin.charAt(0)));
        //System.out.println("Bit 1: "+opcode1.getbit()); 
        opcode2.assignBit(Character.getNumericValue(opcodeBin.charAt(1)));
        //System.out.println("Bit 2: "+opcode2.getbit());
        opcode3.assignBit(Character.getNumericValue(opcodeBin.charAt(2))); 
        //System.out.println("Bit 3: "+opcode3.getbit());
        opcode4.assignBit(Character.getNumericValue(opcodeBin.charAt(3)));
        //System.out.println("Bit 4: "+opcode4.getbit());
        opcode5.assignBit(Character.getNumericValue(opcodeBin.charAt(4)));
        //System.out.println("Bit 5: "+opcode5.getbit());
        opcode6.assignBit(Character.getNumericValue(opcodeBin.charAt(5)));
        //System.out.println("Bit 6: "+opcode6.getbit()); 
        opcode7.assignBit(Character.getNumericValue(opcodeBin.charAt(6)));
        //System.out.println("Bit 7: "+opcode7.getbit()); 
        opcode8.assignBit(Character.getNumericValue(opcodeBin.charAt(7)));
        //System.out.println("Bit 8: "+opcode8.getbit()); 
        opcode9.assignBit(Character.getNumericValue(opcodeBin.charAt(8)));
        //System.out.println("Bit 9: "+opcode9.getbit());
        opcode10.assignBit(Character.getNumericValue(opcodeBin.charAt(9)));
        //System.out.println("Bit 10: "+opcode10.getbit());
        opcode11.assignBit(Character.getNumericValue(opcodeBin.charAt(10)));
        //System.out.println("Bit 11: "+opcode11.getbit()); 

        if (movLength >= 1) {
            mov1.assignBit(Character.getNumericValue(movBin.charAt(0)));
            //System.out.println("MOV Bit 1: "+mov1.getbit()); 
        }
        if (movLength >= 2) {
            mov2.assignBit(Character.getNumericValue(movBin.charAt(1)));
            //System.out.println("MOV Bit 2: "+mov2.getbit());
        }
        if (movLength >= 3) {
            mov3.assignBit(Character.getNumericValue(movBin.charAt(2)));
            //System.out.println("MOV Bit 3: "+mov3.getbit());
        }
        if (movLength >= 4) {
            mov4.assignBit(Character.getNumericValue(movBin.charAt(3)));
            //System.out.println("MOV Bit 4: "+mov4.getbit());
        }
        if (movLength >= 5) {
            mov5.assignBit(Character.getNumericValue(movBin.charAt(4)));
            //System.out.println("MOV Bit 5: "+mov5.getbit());
        }
        if (movLength >= 6) {
            mov6.assignBit(Character.getNumericValue(movBin.charAt(5)));
            //System.out.println("MOV Bit 6: "+mov6.getbit());
        }
        if (movLength >= 7) {
            mov7.assignBit(Character.getNumericValue(movBin.charAt(6)));
            //System.out.println("MOV Bit 7: "+mov7.getbit());
        }
        if (movLength >= 8) {
            mov8.assignBit(Character.getNumericValue(movBin.charAt(7)));
            //System.out.println("MOV Bit 8: "+mov8.getbit());
        }
        if (movLength >= 9) {
            mov9.assignBit(Character.getNumericValue(movBin.charAt(8)));
            //System.out.println("MOV Bit 9: "+mov9.getbit());
        }
        if (movLength >= 10) {
            mov10.assignBit(Character.getNumericValue(movBin.charAt(9)));
            //System.out.println("MOV Bit 10: "+mov10.getbit());
        }
        if (movLength >= 11) {
            mov11.assignBit(Character.getNumericValue(movBin.charAt(10)));
            //System.out.println("MOV Bit 11: "+mov11.getbit());
        }
        if (movLength >= 12) {
            mov12.assignBit(Character.getNumericValue(movBin.charAt(11)));
            //System.out.println("MOV Bit 12: "+mov12.getbit());
        }
        if (movLength >= 13) {
            mov13.assignBit(Character.getNumericValue(movBin.charAt(12)));
            //System.out.println("MOV Bit 13: "+mov13.getbit());
        }
        if (movLength >= 14) {
            mov14.assignBit(Character.getNumericValue(movBin.charAt(13)));
            //System.out.println("MOV Bit 14: "+mov14.getbit());
        }
        if (movLength >= 15) {
            mov15.assignBit(Character.getNumericValue(movBin.charAt(14)));
            //System.out.println("MOV Bit 15: "+mov15.getbit());
        }
        if (movLength == 16) {
            mov16.assignBit(Character.getNumericValue(movBin.charAt(15)));
            //System.out.println("MOV Bit 16: "+mov16.getbit());
        }

        if (regdLength >= 1) {
            rd1.assignBit(Character.getNumericValue(regdBin.charAt(0)));
            //System.out.println("RD Bit 1: "+rd1.getbit()); 
        }
        if (regdLength >= 2) {
            rd2.assignBit(Character.getNumericValue(regdBin.charAt(1)));
            //System.out.println("RD Bit 2: "+rd2.getbit()); 
        }
        if (regdLength >= 3) {
            rd3.assignBit(Character.getNumericValue(regdBin.charAt(2)));
            //System.out.println("RD Bit 3: "+rd3.getbit());
        }
        if (regdLength >= 4) {
            rd4.assignBit(Character.getNumericValue(regdBin.charAt(3)));
            //System.out.println("RD Bit 4: "+rd4.getbit());
        }
        if (regdLength == 5) {
            rd5.assignBit(Character.getNumericValue(regdBin.charAt(4)));
            //System.out.println("RD Bit 5: "+rd5.getbit()); 
        }

        iwformat[0] = rd1;
        iwformat[1] = rd2;
        iwformat[2] = rd3;
        iwformat[3] = rd4;
        iwformat[4] = rd5;

        iwformat[5] = mov1;
        iwformat[6] = mov2;
        iwformat[7] = mov3; 
        iwformat[8] = mov4;
        iwformat[9] = mov5;
        iwformat[10] = mov6;
        iwformat[11] = mov7;
        iwformat[12] = mov8;
        iwformat[13] = mov9; 
        iwformat[14] = mov10;
        iwformat[15] = mov11;
        iwformat[16] = mov12;
        iwformat[17] = mov13;
        iwformat[18] = mov14;
        iwformat[19] = mov15;
        iwformat[20] = mov16;

        iwformat[21] = opcode1;
        iwformat[22] = opcode2; 
        iwformat[23] = opcode3; 
        iwformat[24] = opcode4; 
        iwformat[25] = opcode5; 
        iwformat[26] = opcode6; 
        iwformat[27] = opcode7; 
        iwformat[28] = opcode8; 
        iwformat[29] = opcode9; 
        iwformat[30] = opcode10; 
        iwformat[31] = opcode11;
        
        binarytohex(iwformat);
    }
}
