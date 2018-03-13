package ImportAssembly;
import java.util.*; 
import java.io.*; 

/**
 * Contains the Main method to run the program. 
 * Creates an instance of the ReadFiles class.
 * Calls the function readAsFile to implement the program. 
 * 
 * @author Ingrid Rumbaugh
 * @version 10/4/17
 */
public class Assembler {
     
    /**
     * Main method of the program that creates an instance of the ReadFiles class. 
     */
    public static void main(String [] args) {
        ReadFiles r = new ReadFiles(); 
        r.readAsFile("test.as"); 
    }
}










