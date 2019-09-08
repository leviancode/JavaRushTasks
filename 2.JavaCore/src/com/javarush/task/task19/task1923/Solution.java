package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
       // String fileName = "/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1923/Text.txt";
       //  String fileName2 = "/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1923/Text-2.txt";
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter printWriter = new BufferedWriter(new FileWriter(args[1]));

        while (fileReader.ready()){
            String line =fileReader.readLine();
            String[] words = line.split(" ");
            for (String word : words)
                if (!word.matches("^\\D*$"))
                    printWriter.write(word + " ");
        }
        fileReader.close();
        printWriter.close();
    }
}
