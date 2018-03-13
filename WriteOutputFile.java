package ImportAssembly;
import java.util.*; 
import java.io.PrintWriter; 

/**
 * Write a description of class WriteOutputFile here.
 * 
 * @author Ingrid Rumbaugh 
 * @version 1/5/2018
 */
public class WriteOutputFile {
    PrintWriter writer;
    List<String> finalhex = new ArrayList<String>(); 
    
    /**
     * Name must include .os extension 
     */
    public void createFile(String name) {
        try {
            writer = new PrintWriter(name, "UTF-8"); 
            //writer.println("Assembly Output"); 
            for (int i = 0; i < finalhex.size(); i ++) {
                writer.print(finalhex.get(i)); 
            }
            System.out.println("Outputfile: "+name+" Completed!"); 
            writer.close(); 
        }
        catch (Exception e) {
            System.out.println("Error Occurred in Output File"); 
        }
    }
    
    public void setFinalHex(List<String> hex) {
        finalhex = hex; 
    }
}
