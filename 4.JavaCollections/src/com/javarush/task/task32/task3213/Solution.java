package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader buffReader = new BufferedReader(reader);
            String encryptedLine = buffReader.readLine();

            char[] encryptedArr = encryptedLine.toCharArray();
            for (char c : encryptedArr) {
                int decryptedChar = c + key;
                sb.append((char) decryptedChar);
            }
        }catch (Exception e){
            return "";
        }

        return sb.toString();
    }
}
