package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(file1);
        List<Integer> list = new ArrayList<>();

        while (fileReader.ready()){
            list.add(fileReader.read());
        }
        fileReader.close();

        FileWriter fileWriter = new FileWriter(file2);
        for (int i = 1; i< list.size(); i+=2){
            fileWriter.write(list.get(i));
        }
        fileWriter.close();

    }
}
