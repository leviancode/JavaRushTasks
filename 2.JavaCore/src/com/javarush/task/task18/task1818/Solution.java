package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        String name3 = reader.readLine();

        FileOutputStream fos1 = new FileOutputStream(name1,true);
        FileInputStream fis2 = new FileInputStream(name2);
        FileInputStream fis3 = new FileInputStream(name3);


        byte[] file2 = new byte [fis2.available()];
        fis2.read(file2);
        fos1.write(file2);

        byte[] file3 = new byte [fis3.available()];
        fis3.read(file3);
        fos1.write(file3);

        reader.close();
        fos1.close();
        fis2.close();
        fis3.close();

    }
}
