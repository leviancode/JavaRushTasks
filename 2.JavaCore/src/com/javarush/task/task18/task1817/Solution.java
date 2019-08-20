package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
       FileInputStream fis = new FileInputStream(args[0]);
        int symbols = 0;
        int spaces = 0;

        while (fis.available()>0){
            char i = (char) fis.read();
            String s = Character.toString(i);
            if (s.matches("."))
                symbols++;
            if (s.matches("\\s"))
                spaces++;
        }
        fis.close();
        double d = (double) spaces/ (double) symbols*100.00;
        System.out.printf("%.2f", d);

    }
}
