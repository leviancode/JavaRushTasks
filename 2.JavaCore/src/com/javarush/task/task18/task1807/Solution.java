package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        FileInputStream inputStream = new FileInputStream(name);

        byte[] buffer = null;
        if (inputStream.available()>0){
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
        }
        reader.close();
        inputStream.close();

        int count = 0;
        for (byte b : buffer){
            char c = (char) b;
            if (c == ',') count++;
        }
        System.out.println(count);


    }
}
