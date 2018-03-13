package ImportAssembly;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BitTest {
    @Test
    public void testbit() {
        int b1 = 1;
        int b2 = 0; 
 
        Bit bit1 = new Bit(b1); 
        Bit bit2 = new Bit(b2);
        
        assertTrue(bit1.getbit() == 1);
        assertTrue(bit2.getbit() == 0); 
    }
    
}
