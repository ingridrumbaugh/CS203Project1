package ProcessAssembly;


/**
 * Write a description of class AssemblerOut here.
 * 
 * @author Ingrid Rumbaugh 
 * @version 1/12/18
 */
public class AssemblerOut {
    
    
    public static void main(String [] args) {
        ReceiveOutputFile rof = new ReceiveOutputFile(); 
        rof.readOFile("output.o"); 
    }
}
