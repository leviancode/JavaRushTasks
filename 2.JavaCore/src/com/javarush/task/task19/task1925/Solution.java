package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String fileName2 = args[1];
      //  String fileName = "/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1925/Text.txt";
      //  String fileName2 = "/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1925/Text-2.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> listWords = new ArrayList<>();

        while (bufferedReader.ready()){
            String [] words = bufferedReader.readLine().split(" ");
            for (String word : words){
                if (word.length()>6)
                    listWords.add(word);
            }
        }
        bufferedReader.close();

        FileWriter fileWriter = new FileWriter(fileName2);
        for (int i = 0; i<listWords.size(); i++){
            if (i != listWords.size()-1)
                fileWriter.write(listWords.get(i) + ",");
            else
                fileWriter.write(listWords.get(i));
        }
        fileWriter.close();


    }
}
