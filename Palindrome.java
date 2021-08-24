
		import java.io.*;
		import java.util.Scanner;

		/*
		 * Feb,20,2021
		 * Author @Jasmine Tian
		 * Assignment 1 Part 3 - Palindrome
		 * Ms. Wong
		 * ICS4U
		 */
public class Palindrome {
	int var1;
    int var2;
    Palindrome (int i1) {
        var1=i1;
        var2=i1;
    }

  //main method to ....
    public static void main(String[] args) {

        try {
            BufferedReader inFile = new BufferedReader(new FileReader("src\\JasminAss1\\input3.txt"));
            String line = " ";
            while (line != null) {
                line = inFile.readLine();
                if (line != null) {
                    System.out.println(getMaxPalindrome(line));
                }
            }
            inFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
  //Description:
  	//Input:
  	//Return:
      private static boolean checkPalindrome(String inStr) {
          //abba, abcba
          int len = inStr.length();
          boolean bRet = true;
          int iMid;

          iMid = len / 2;
          //System.out.println("Len is:" + len + ", Mid point is:" + iMid);

          for (int i = 0; i < iMid; i++) {
              //System.out.printf("front:%c, end:%c\n",inStr.charAt(i), inStr.charAt(len-1-i));
              if (inStr.charAt(i) != inStr.charAt(len - 1 - i)) {
                  bRet = false;
                  break;
              }
          }
          return bRet;
      }

      //check the longest palindrome in a line
      public static String getMaxPalindrome(String str) {
          int startIndex = 0;
          int maxSize = 0;
          String tempStr;
          String maxStr = "";
          int lastIndex = str.length();

  		//for loop to ....
          for (int i = 0; (str.length() - i) > maxSize; i++) {
              lastIndex = str.lastIndexOf(str.charAt(i));
              //System.out.printf("At index %d for char %c, lastIndex = %d\n", i, str.charAt(i), lastIndex);
              //compare lastIndex to i
              if (lastIndex <= i) {
                  //System.out.printf("Break at index %d, lastIndex = %d\n", i, lastIndex);
                  continue;
              }
              //compare the length of string between i and lastIndex to maxSize
              if (lastIndex - i + 1 <= maxSize) {
                  //System.out.printf("Break at length %d, maxSize = %d\n", (lastIndex-i+1), maxSize);
                  continue;
              }
              //go through all substring start with char at index i
              do {
                  tempStr = str.substring(i, lastIndex + 1);
                  //System.out.printf("Process string = %s, index=%d, maxSize=%d\n", tempStr, i, maxSize);

                  if (checkPalindrome(tempStr) == true) {
                      if (tempStr.length() > maxSize) {
                          startIndex = i;
                          maxSize = tempStr.length();
                          maxStr = tempStr;
                          //System.out.printf("Save maxSize, string = %s, StartIndex=%d, New_MaxSize=%d, NewStr=%s\n", tempStr, i, maxSize, maxStr);
                      }
                      break;
                  } else {
                      lastIndex = str.lastIndexOf(str.charAt(i), lastIndex - 1);
                  }
              } while (lastIndex > i && (lastIndex - i + 1) > maxSize);
          }
          if (maxSize == 0) {
              System.out.println("Cannot find any palindrome!");
              maxStr = str.substring(0,1);
          }
          return maxStr;
      }
  }
