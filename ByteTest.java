package ImportAssembly;


import java.util.*; 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ByteTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ByteTest {
    @Test
    public void testByteConstructor() {
        Byte b = new Byte(1,0,0,1,1,0,1,1); 
        int testarr[] = new int[8]; 
        testarr[0] = 1;
        testarr[1] = 0; 
        testarr[2] = 0; 
        testarr[3] = 1; 
        testarr[4] = 1;
        testarr[5] = 0;
        testarr[6] = 1;
        testarr[7] = 1; 
        
        for (int i = 0; i < 7; i ++) {
            assertTrue(testarr[i] == b.getByte(i)); 
        }
    }
    
}
