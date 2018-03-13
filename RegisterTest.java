package ImportAssembly;
import java.util.*; 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RegistersTest.
 *
 * @author  Ingrid Rumbaugh 
 * @version 10/10/17
 */
public class RegisterTest {
    @Test
    public void testRegConstructor() {
        Register newReg = new Register("WZR", 32); 
        newReg.writeReg(0, 1); 
        newReg.writeReg(1, 1);
        newReg.writeReg(2, 1); 
        assertTrue(newReg.getReg(0) == 1); 
        assertTrue(newReg.getReg(1) == 1); 
        assertTrue(newReg.getReg(2) == 1); 
        assertTrue(newReg.getReg(3) == 0); 
    }
}




