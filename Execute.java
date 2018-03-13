package ProcessAssembly;

/**
 * Write a description of class Execute here.
 * 
 * @author Ingrid Rumbaugh
 * @version 1/14/2018
 */
public class Execute {
    Opcodes op = new Opcodes(); 
    String type; 
    String opcode; 
    String tempcommand;

    Commands comm = new Commands(); 

    public void determineCommand(int[] binary) {
        type = null; 
        op.determineType(binary); 
        type = op.getType(); 
        opcode = op.getOpcode(); 
        
        tempcommand = "";
        for (int i = 0; i < binary.length; i ++) {
            tempcommand += binary[i]; 
        }
        try {
            comm.getWholeCommand(tempcommand); 
            // Use call by name to call the appropriate command in Class: Commands 
            callByName(comm, type); 
            System.out.println("Called "+type+" command"); 
        } catch (Exception e) {
            System.out.println(e+" in Execute class");
        }
    }

    public void callByName(Object obj, String name) throws Exception {
        obj.getClass().getDeclaredMethod(name).invoke(obj); 
    }
}
