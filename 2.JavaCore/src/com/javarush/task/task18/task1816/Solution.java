package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        int count = 0;

        while (fis.available()>0){
            char c = (char) fis.read();
            String s = Character.toString(c);
            if (s.matches("[a-zA-Z]"))
                count++;
        }
        fis.close();
        System.out.println(count);
    }
}
