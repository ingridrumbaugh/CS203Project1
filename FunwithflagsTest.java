package ImportAssembly;
import java.util.*; 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FunwithflagsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FunwithflagsTest {
    @Test
    public void testFlags() {
        Funwithflags sheldon = new Funwithflags(); 
        sheldon.nflag(); 
        sheldon.vflag(); 
        assertTrue(sheldon.getflag("n") == 1); 
        assertTrue(sheldon.getflag("v") == 1); 
        assertTrue(sheldon.getflag("c") == 0); 
        assertTrue(sheldon.getflag("z") == 0); 
    }
}
