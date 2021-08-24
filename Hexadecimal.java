		import java.io.*;
		import java.util.Scanner;
		/*
	 * Feb,20,2021
	 * Author @Jasmine Tian
	 * Assignment 1 Part 2 - Hexadecimal
	 * Ms. Wong
	 * ICS4U
	 */

public class Hexadecimal {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            // the input file path and file name need to be changed for your testing.
	            BufferedReader inFile = new BufferedReader(new FileReader("input.txt"));
	            String line = " ";
	            while (line != null) {
					//read one line at a time
	                line = inFile.readLine();
	                if (line != null) {
						//if the line is a string, then call translateString method to get the decoded string and print out
	                    System.out.println(translateString(line));
	                }
	            }
				//close the file after the process completes
	            inFile.close();
	        } catch (IOException e) {
				//try to catch any IO exceptions
	            System.out.println(e.getMessage());
	        }
	}
	
	 private int addition(int a, int b){
	        return (a+b);
	    }
	    private static String translateString (String inLine)
	    {
	        String newLine;
	        String code;
	        newLine = "";
	        char ch;
	        for(int i=0; i<inLine.length();i++){
	            if(inLine.charAt(i)=='%'){
	                code = inLine.substring(i+1, i+3);
	                ch = (char)Integer.parseInt(code,16);
	                //System.out.println(code);
	                //System.out.println(ch);
	                newLine = newLine + Character.toString(ch);
	                i = i + 2;
	            } else {
	            	newLine = newLine + Character.toString(inLine.charAt(i));
	            }
	        }
	        //System.out.println(newStr);
	        return newLine;
	        
	        
	    }

}
