package ImportAssembly;

import java.util.*;
import java.io.*;

/**
 * Accepts an input file of type "*.as" and parses the arguments. 
 * (Assuming that the input file is the correct format, see Documentation.)
 * 
 * 
 * @author Ingrid Rumbaugh 
 * @version 10/20/17
 */
public class ReadFiles {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    Scanner sc;
    // Pre-defined directives in the input file 
    String wordsizecommand; // Convert to bits 
    String aligncommand; // should be an int --> for memory 
    String maxmemcommand;
    String regcountcommand; 

    String savedcurrent = new String();
    String savedfirst = new String();
    String savedsecond = new String();
    String savedthird = new String(); 
    // used to keep track of the current command being parsed 
    String currentposcommand, currentposcommand2, currentposcommand3;
    String currentcommand, stack, stackpos; 

    String doublecommand, singlecommand, halfcommand, bytecommand; 
    // keep track of certain arguments in a certain line / command
    String current, command, first, second, third, thirdfinal;

    int wordsize, regcount, maxmemory; // Convert this to hex type 
    int currentpos1, currentpos2;
    int align;
    int doublesize, singlesize, halfsize, bytesize; 

    boolean hasUpperCase; 

    List<String> arguments = new ArrayList<String>();
    String[] arrcmd = new String[0];
    String[] savedcmdarray;
    String[] datacontents = new String[0]; 

    DecHexConverter dhc = new DecHexConverter();  
    AssemblerFunctions functions = new AssemblerFunctions(); 
    Register r = new Register(); 
    WriteOutputFile wof = new WriteOutputFile(); 

    /**
     * Reads the file, given a string of the file name. 
     * Outputs the parsing process to show how each command was broken down. 
     */
    public void readAsFile(String dataFile) {
        try {
            sc = new Scanner(new FileReader(dataFile)); 

            parseDirectives();
            
            // WHILE LINE DOESN'T CONTAIN HALT 
            // if 1st command == ADDI go to addi method etc 
            while (sc.hasNext("HALT") == false) {
                //sc.nextLine(); 
                currentcommand = sc.nextLine(); 
                String comdelims = "[ , ]";
                String maindelim = "['main:', ]";
                arrcmd = currentcommand.split(comdelims);
                // Get rid of unnecessary white spaces 
                for (int j = 0; j < arrcmd.length; j ++) { 
                    arrcmd[j] = arrcmd[j].trim(); 
                }
                // Save the trimmed down version without white spaces 
                List<String> cutcommands = new ArrayList<>(); 
                Collections.addAll(cutcommands, arrcmd); 
                cutcommands.removeAll(Arrays.asList("")); 
                arrcmd = cutcommands.toArray(EMPTY_STRING_ARRAY);
                saveCommandString(arrcmd); 

                // Print trimmed command 
                for (int k = 0; k < arrcmd.length; k ++) {
                    System.out.println(arrcmd[k]);
                }
                // While the trimmed command still has a next character.
                for (int n = 0; n < arrcmd.length; n ++) {
                    //System.out.println("Current is: "+arrcmd[n]); 

                    // Determine if the command starts with an upper case 
                    hasUpperCase = !arrcmd[n].equals(arrcmd[n].toLowerCase()); 
                    if(hasUpperCase) {
                        // then this is a command 
                        // convert to lower case to call function 
                        arrcmd[n] = arrcmd[n].toLowerCase(); 
                        System.out.println("Calling "+arrcmd[n]+" Function now");
                        current = arrcmd[n].toString(); 
                        command = current;

                        //System.out.println("Command: "+command);

                        // call the function in AssemFunct class + function name as string
                        //callByName(functions, current); 
                    } else {
                        // then this is an argument 
                        if(arrcmd[n].contains("x") && !arrcmd[n].contains("[")) {
                            // then this is a register 
                            if(arrcmd[n-1] == command) {
                                // this is the 1st argument 
                                first = arrcmd[n]; 
                                // if register doesn't already exist 
                                if(r.checkRegisterExists(first) == false) {
                                    r.createNewRegister(first, 4);
                                }
                                //System.out.println("First Arg: "+first);
                            }
                            else if(arrcmd[n-2] == command){
                                // this is the 2nd argument 
                                second = arrcmd[n]; 
                                // if register doesn't already exist 
                                if(r.checkRegisterExists(second) == false) {
                                    r.createNewRegister(second, 4);
                                }
                                third = arrcmd[n+1]; 
                                //System.out.println("Third Arg: "+third);
                                if (third.contains("#")) {
                                    third = third.replace("#","");
                                    thirdfinal = third;  
                                    // if register doesn't already exist 
                                    if(r.checkRegisterExists(thirdfinal) == false) {
                                        r.createNewRegister(thirdfinal, 4);
                                    }
                                    functions.setVariablesImm(first,second,Integer.valueOf(thirdfinal));
                                }else {
                                    // if register doesn't already exist 
                                    if(r.checkRegisterExists(third) == false) {
                                        r.createNewRegister(third, 4);
                                    }
                                    functions.setVariables(first,second,third);
                                }
                                //saveArgs();
                                callByName(functions, current); 
                            }
                            // do the shit below in the AssemFunct class: 
                            // check if predefined register 
                            // check if this register has been used before
                        }
                        else if(arrcmd[n].contains("[")) {
                            
                            if(arrcmd[n-1] == command) {
                                // this is the 1st argument 
                                String temp2 = arrcmd[n]; 
                                temp2 = temp2.replace("[", "").replace("]", "");
                                first = temp2; 
                                System.out.println("First Arg: "+first);
                                if(r.checkRegisterExists(first) == false) {
                                    r.createNewRegister(first, 4);
                                }
                            }
                            else if(arrcmd[n-2] == command){
                                String temp3 = arrcmd[n]; 
                                temp3 = temp3.replace("[", "").replace("]", "");
                                second = temp3; 
                                System.out.println("Second Arg: "+second);
                                if(r.checkRegisterExists(second) == false) {
                                    r.createNewRegister(second, 4);
                                }
                                String temp4 = arrcmd[n+1]; 
                                temp4 = temp4.replace("[", "").replace("]", "");
                                third = temp4;
                                System.out.println("Third Arg: "+third); 
                                if (third.contains("#")) {
                                    third = third.replace("#","");
                                    thirdfinal = third;
                                    functions.setVariablesImm(first,second,Integer.valueOf(thirdfinal)); 
                                    if(r.checkRegisterExists(thirdfinal) == false) {
                                        r.createNewRegister(thirdfinal, 4);
                                    }
                                } else {
                                    functions.setVariables(first,second,thirdfinal);
                                    if(r.checkRegisterExists(third) == false) {
                                        r.createNewRegister(third, 4);
                                    }
                                }
                                //saveArgs();
                                callByName(functions, current);
                            }
                        } else if (arrcmd[n].contains("data")) {
                            second = Integer.toString(doublesize); 
                            third = null; 
                            callByName(functions, current); 
                        }
                    }                                
                }
            } 
            // final hex code is here! Now you can write to output file 

            saveFinalHex(functions.finalHex()); 
            parseEndDirectives();
        }
        catch (Exception e) {
            System.out.println(e); 
        }
    }

    /**
     * Saves the final Hex output and sends it to the writeOutputFile class
     */
    public void saveFinalHex(List<String> hex) {
        wof.setFinalHex(hex); 
        wof.createFile("output.o");
    }

    public void parseEndDirectives() {
        // parse the rest of the directives 
        sc.nextLine();
        sc.nextLine();
        currentposcommand2 = sc.nextLine(); 
        String delims2 = "[ .x]";
        String delims =  "[ .]";
        String[] arraypos2 = currentposcommand2.split(delims2); 
        currentpos1 = Integer.parseInt(arraypos2[2]);
        currentpos2 = Integer.parseInt(arraypos2[3]); 
        System.out.println("Current Pos: "+currentpos1+"x"+currentpos2); 

        aligncommand = sc.nextLine();  
        String[] arrayalign = aligncommand.split(delims); 
        align = Integer.parseInt(arrayalign[2]); 
        System.out.println("Align: "+align); 
        sc.nextLine(); 
        sc.nextLine(); // data: 

        doublecommand = sc.nextLine(); 
        singlecommand = sc.nextLine();
        halfcommand = sc.nextLine(); 
        bytecommand = sc.nextLine(); 
        //System.out.println("Double comd "+doublecommand); 
        String[] arraydouble = doublecommand.split(delims2);
        String[] arraysingle = singlecommand.split(delims2); 
        String[] arrayhalf = halfcommand.split(delims2); 
        String[] arraybyte = bytecommand.split(delims2); 
        // NOT SIZE OF DOUBLE 

        // double 8bytes 
        // single 4bytes
        // half 2bytes
        // byte 1byte

        // Defining a .double --> # of bytes 
        // with the value 0x0AB 
        doublesize = dhc.hex2Decimal(arraydouble[3]); 
        singlesize = dhc.hex2Decimal(arraysingle[3]); 
        halfsize = dhc.hex2Decimal(arrayhalf[2]); 
        bytesize = dhc.hex2Decimal(arraybyte[2]); 

        String hexdouble = dhc.decimal2Hex(doublesize); 
        String hexsingle = dhc.decimal2Hex(singlesize);
        String hexhalf = dhc.decimal2Hex(halfsize); 
        String hexbyte = dhc.decimal2Hex(bytesize); 
        System.out.println("------------------------------------");
        System.out.println("Double Size: "+doublesize); 
        System.out.println("Double Size Hex: 0x0"+hexdouble); 
        System.out.println("Single Size: "+singlesize);
        System.out.println("Single Size Hex: 0x0"+hexsingle); 
        System.out.println("Half Size: "+halfsize); 
        System.out.println("Half Size Hex: 0x0"+hexhalf); 
        System.out.println("Byte Size: "+bytesize);
        System.out.println("Byte Size Hex: 0x0"+hexbyte); 
        System.out.println("------------------------------------");
        // halt counts as a command
        // .pos skips in memory to that number 
        sc.nextLine();
        currentposcommand3 = sc.nextLine(); 
        String[] arraypos3 = currentposcommand3.split(delims2); 
        currentpos1 = Integer.parseInt(arraypos2[2]);
        currentpos2 = Integer.parseInt(arraypos2[3]); 
        System.out.println("Current Pos: "+currentpos1+"x"+currentpos2); 

        stack = sc.nextLine(); 
        String[] arraystack = stack.split(delims2); 
        stackpos = arraystack[3]; 
        System.out.println("Stack Pos: "+arraystack[1]+" 0x"+stackpos);
    }

    public void parseDirectives() {
        // parse directives 
        wordsizecommand = sc.nextLine(); 
        String delims = "[ .]";
        String[] arraywordsize = wordsizecommand.split(delims);
        wordsize = Integer.parseInt(arraywordsize[2]);
        System.out.println("Word Size Int: "+wordsize); 

        regcountcommand = sc.nextLine(); 
        String[] arrayregcount = regcountcommand.split(delims); 
        regcount = Integer.parseInt(arrayregcount[2]); 
        System.out.println("Reg Count: "+regcount); 

        maxmemcommand = sc.nextLine(); 
        String[] arraymaxmem = maxmemcommand.split(delims); 
        maxmemory = dhc.hex2Decimal(arraymaxmem[2]); 
        String hexmemory = dhc.decimal2Hex(maxmemory); 
        System.out.println("Max Mem: "+maxmemory); 
        System.out.println("Max Mem Hex: 0x"+hexmemory); 
        r.setMaxMem(maxmemory); 

        sc.nextLine();
        currentposcommand = sc.nextLine(); 
        String delims2 = "[ .x]";
        String[] arraypos = currentposcommand.split(delims2); 
        currentpos1 = Integer.parseInt(arraypos[2]);
        currentpos2 = Integer.parseInt(arraypos[3]); 
        System.out.println("Current Pos: "+currentpos1+"x"+currentpos2); 

    }

    public void saveArgs() {
        System.out.println("SAVE ARGS");
        System.out.println("Current "+current); 
        System.out.println("First "+first); 
        System.out.println("Second "+second); 
        System.out.println("Third "+thirdfinal); 

        savedcurrent = current; 
        savedfirst = first;
        savedsecond = second;
        savedthird = thirdfinal; 

        arguments.add(savedcurrent); 
        arguments.add(savedfirst);
        arguments.add(savedsecond); 
        arguments.add(savedthird); 
    }

    public List<String> getArgs() {
        return arguments;
    }

    /**
     * Given an array of type String, (this is the command)
     * Save the string to be used / returned later. 
     */
    public void saveCommandString(String[] command) {
        savedcmdarray = command;
    }

    /**
     * Returns the saved command string as an array.
     */
    public String[] getCommandString() {
        return savedcmdarray; 
    }

    /**
     * This method allows the parsed command to call a function with the same name
     * in the AssemblerFunctions class. 
     */
    public void callByName(Object obj, String name) throws Exception {
        obj.getClass().getDeclaredMethod(name).invoke(obj); 
    }
}
