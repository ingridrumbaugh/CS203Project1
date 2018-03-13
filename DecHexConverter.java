package ImportAssembly;
import java.util.*; 

/**
 * This class is used to convert inputs from decimal to hex and vice versa.
 * Code used to write this class was taken from:
 * Source: http://introcs.cs.princeton.edu/java/31datatype/Hex2Decimal.java.html
 * 
 * @author Ingrid Rumbaugh
 * @version 10/13/17
 */
public class DecHexConverter {
    /**
     * Given an input String (a hexadecimal value), return the corresponding int. 
     */
    public static int hex2Decimal(String s) {
        // Define range of hex input, 0-F
        String digits = "0123456789ABCDEF";
        // Convert all to upper case so the input is uniform. 
        s = s.toUpperCase();
        int val = 0;
        // Iterates through length of hex string 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // determine where in range of hex input c falls. 
            int d = digits.indexOf(c);
            // conver to hex and save as val.
            val = 16*val + d;
        }
        return val;
    }

    /**
     * Given an input decimal, return the corresponding hexadecimal value. 
     * Precondition: input is a nonnegative integer. 
     */
    public static String decimal2Hex(int d) {
        // Define range of hex input, 0-F
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
    }
}
