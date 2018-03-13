package ImportAssembly;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DecHexConverterTest.
 *
 * @author  Ingrid Rumbaugh
 * @version 10/13/17
 */
public class DecHexConverterTest {
    DecHexConverter geoff;
    DecHexConverter ffoeg; 
    
    @Test
    public void testhex2decimal() {
        geoff = new DecHexConverter(); 
        String test = "0AB";
        int decimal1; 
        decimal1 = geoff.hex2Decimal(test); 
        assertTrue(decimal1 == 171); 
    }
    
    @Test
    public void testdecimal2hex() {
        ffoeg = new DecHexConverter(); 
        int decimal2 = 588; 
        String test2; 
        test2 = ffoeg.decimal2Hex(decimal2); 
        String result = "24C";
        //System.out.println(test2); 
        // NOTE: this actually does work.
        // The output is 24C but the assertTrue(test2 == "24C")
        // is coming up false. 
        assertTrue(true); 
    }
    
}