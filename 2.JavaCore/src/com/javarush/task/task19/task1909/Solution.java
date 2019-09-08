package com.javarush.task.task19.task1909;

/* 
Замена знаков
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
        String newText;

        while (fileReader.ready()){
            char c = (char) fileReader.read();
            text += c;
        }
        fileReader.close();

        newText = text.replaceAll("\\.", "!");

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2, true));
        fileWriter.write(newText);
        fileWriter.close();
    }
}
