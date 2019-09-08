package com.javarush.task.task19.task1908;

/* 
Выделяем числа
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
            text += c;
        }
        fileReader.close();

        String[] textArr = text.split(" ");

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));

        for (String s : textArr){
            try {
                int x = Integer.parseInt(s);
                fileWriter.write(x + " ");
            }catch (NumberFormatException e){
                continue;
            }
        }
        fileWriter.close();
    }
}
