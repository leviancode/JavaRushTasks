package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String text = "";

        while (fileReader.ready()){
            char c = (char) fileReader.read();
            String s = String.valueOf(c);
            if (!s.matches("[\\p{Punct}\n]")) {
                text +=s;
            }
        }
        fileReader.close();

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2, true));
        fileWriter.write(text);
        fileWriter.close();
    }
}
