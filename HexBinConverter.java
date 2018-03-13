package ImportAssembly;
import java.math.*;
import java.util.*; 

/**
 * This class is used to convert inputs from hexadecimal to binary and vice versa.
 * Code used to write this class was taken from: 
 * Source: http://introcs.cs.princeton.edu/java/31datatype/Hex2Decimal.java.html
 * 
 * @author Ingrid Rumbaugh
 * @version 10/13/17
 */
public class HexBinConverter {
    
    /**
     * Input string of type binary, outputs hex string in "0X..." format. 
     */
    public static String binaryToHex(String bin) {
        return String.format("%0X", Long.parseLong(bin,2)) ;
    }
    
    public static String convertBinaryToHexadecimal(String number) {
        String hexa = "";
        char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f' };
        if (number != null && !number.isEmpty()) {
            int decimal = convertBinaryToDecimal(number);
            while (decimal > 0) {
                hexa = hex[decimal % 16] + hexa;
                decimal /= 16;
            }
            //System.out.println("The hexa decimal number is: " + hexa);
        }
 
        return hexa;
    }
    
    public static int convertBinaryToDecimal(String number) {
        int length = number.length() - 1;
        int decimal = 0;
        if (isBinary(number)) {
            char[] digits = number.toCharArray();
            for (char digit : digits) {
                if (String.valueOf(digit).equals("1")) {
                    decimal += Math.pow(2, length);
                }
                --length;
            }
            //System.out.println("The decimal number is : " + decimal);
        }
        return decimal;
    }
    
    public static boolean isBinary(String number) {
 
        boolean isBinary = false;
 
        if (number != null && !number.isEmpty()) {
            long num = Long.parseLong(number);
            while (num > 0) {
                if (num % 10 <= 1) {
                    isBinary = true;
                } else {
                    isBinary = false;
                    break;
                }
                num /= 10;
            }
        }
 
        return isBinary;
    }
    
    public String hexToBinTEST(String s) {
        return new BigInteger(s, 16).toString(2); 
    }
    
    /**
     * Input a string of type hex, output binary string. 
     */
    public String hexToBinary(String hex) {
        long i = Long.parseLong(hex, 16);
        String bin = Long.toBinaryString(i);
        return bin;
    }
}
