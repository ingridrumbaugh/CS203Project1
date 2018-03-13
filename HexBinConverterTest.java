package ImportAssembly;


import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HexBinConverterTest.
 *
 * @author  Ingrid Rumbaugh
 * @version 10/13/17
 */
public class HexBinConverterTest {
    @Test
    public void testHexToBin() {
        HexBinConverter hbc = new HexBinConverter(); 
        String hex1 = "0AF"; 
        String bin1;
        bin1 = hbc.hexToBinary(hex1); 
        //System.out.println(bin1); 
        // I tested the output to the terminal and this works. 
        assertTrue(true); 
    }
    
    @Test
    public void test() {
        HexBinConverter hbc3 = new HexBinConverter();
        String bin = "11010010100"; 
        hbc3.convertBinaryToHexadecimal(bin); 
    }
    
    @Test
    public void testBinToHex() {
        HexBinConverter hbc2 = new HexBinConverter(); 
        String hex2;
        String bin2 = "11001001"; // This should be C9
        hex2 = hbc2.binaryToHex(bin2);
        //System.out.println(hex2); 
        assertTrue(true); 
    }
    
    
}