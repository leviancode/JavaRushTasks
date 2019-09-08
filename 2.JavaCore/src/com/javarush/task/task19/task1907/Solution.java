package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(fileName);
       // StringBuilder stringBuilder = new StringBuilder();
        String line = "";

        while (fileReader.ready()){
            char c = (char) fileReader.read();
            line += c;
        }
        fileReader.close();

        String[] stringArr = line.split("[^a-zA-Z0-9]");
        int count = 0;
        for (String s : stringArr){
            if (s.equals("world"))
                count++;
        }
        System.out.println(count);
    }
}
