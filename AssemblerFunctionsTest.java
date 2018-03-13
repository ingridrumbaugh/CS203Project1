package ImportAssembly;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random; 

/**
 * The test class AssemblerFunctionsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AssemblerFunctionsTest {
    AssemblerFunctions f = new AssemblerFunctions(); 
    Bit[] test = new Bit[32]; 
    String xd = new String("x17"); 
    String xn = new String("x2");
    String xt = new String("x13");
    int immediate = 30; 

    private static int getRandomBit() {
        Random rand = new Random(); 
        return rand.nextInt((1-0)+1)+0;
    }
    //     
    //     public void fillBitString() {
    //         for (int i = 0; i < test.length; i ++) {
    //             Bit n = new Bit(getRandomBit()); 
    //             test[i] = n;
    //         }
    //     }

    @Test
    public void testbinarystring() {
        f.subi(xd, xn, immediate); 
        f.add(xd, xn, xt);
        //f.binarytohex(test); 
    }

}
