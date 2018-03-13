package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

/**
 * Write a description of class GUISetup here.
 * 
 * @author Ingrid Rumbaugh  
 * @version 1/8/2018
 */
public class GUISetup extends JPanel implements ActionListener {
    private final static String newline = "\n"; 
    
    Panel mainwindow = new Panel(); 
    TextField indicator  = new TextField("--v--",6);
    TextField fdeu       = new TextField("Fetch   Decode   Execute   Update",35); 
    TextField memLabel   = new TextField("Main Memory Address",20); 
    TextField valLabel   = new TextField("Value",5); 
    TextField stackLabel = new TextField("Stack Address",15); 

    TextField n  = new TextField("N: " ,5); 
    TextField z  = new TextField("Z: ", 5); 
    TextField c  = new TextField("C: ", 5); 
    TextField v  = new TextField("V: ", 5); 
    JTextArea registers_flags; 
    JTextArea memoryaddresses; 
    JTextArea memoryvalues; 

    Button continuebutton = new Button("Continue"); 
    JFrame guiFrame;
    Font font1 = new Font("SansSerif", Font.BOLD, 20); 

    public static void main(String [] args) {
        GUISetup g = new GUISetup(); 
    }

    public GUISetup() {
        guiFrame = new JFrame(); 
        // Make sure the program exits when the frame closes 
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        guiFrame.setTitle("Assembly Instruction Updates");
        guiFrame.setSize(1200, 1000);
        // This centers the JFrame in the middle of the screen 
        guiFrame.setLocationRelativeTo(null);
        addText(); 
        guiFrame.setVisible(true);
    }

    public void addText() {
        mainwindow.add(continuebutton);
        mainwindow.setFont(font1); 
        guiFrame.add(mainwindow,BorderLayout.SOUTH);

        fdeu.setEditable(false);
        fdeu.setFont(font1); 
        guiFrame.add(fdeu);

        registers_flags = new JTextArea(5,30); 
        registers_flags.setEditable(false); 
        registers_flags.append("X0"+newline+"X1"+newline+"X2"+newline+"X3"+newline+"PC");
        registers_flags.setFont(font1);  
        guiFrame.add(registers_flags, JTextField.LEFT); 
        
        memoryaddresses = new JTextArea(10,100); 
        memoryaddresses.setEditable(false); 
        
        memoryvalues = new JTextArea(10, 100); 
        memoryvalues.setEditable(false); 
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
