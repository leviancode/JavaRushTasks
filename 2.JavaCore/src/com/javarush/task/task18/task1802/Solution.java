package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int min;

        String name = reader.readLine();
        FileInputStream fis = new FileInputStream(name);

        min = fis.read();
        while (fis.available() > 0) {
            int data = fis.read();
            if (data < min)
                min = data;
        }
        reader.close();
        fis.close();
        System.out.println(min);

    }
}
