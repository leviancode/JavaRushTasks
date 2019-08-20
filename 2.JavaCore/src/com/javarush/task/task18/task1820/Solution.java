package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileInputStream fis = new FileInputStream(file1);


        byte[] array = new byte [fis.available()];
        fis.read(array);

        List<Character> list = new ArrayList<>();

        String line = "";
        for (byte b : array){
            line += (char) b;
        }

        String [] numbers = line.split(" ");
        FileWriter writer = new FileWriter(file2);

        for (String s : numbers){
            double d = Double.parseDouble(s);
            writer.write(Math.round(d) + " ");
        }

        reader.close();
        fis.close();
        writer.close();

    }
}
