package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        String id = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while (reader.ready()){
            line = reader.readLine();
            String [] splitLine = line.split(" ");
            if (splitLine[0].equals(id)){
                System.out.println(line);
                break;
            }
        }
        consoleReader.close();
        reader.close();
    }
}
