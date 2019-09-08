package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
         String fileName = args[0];
         // String fileName = "/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1920/Text.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Map<String, Double> map = new TreeMap<>();

        while (bufferedReader.ready()){
            String line = bufferedReader.readLine();
            String[] arr = line.split(" ");
            String name = arr[0];
            double value = Double.parseDouble(arr[1]);
            if (map.containsKey(name)){
                double oldValue = map.get(name);
                map.replace(name, oldValue+value);
            }else{
                map.put(name, value);
            }
        }
        bufferedReader.close();

        double maxValue = Collections.max(map.values());

        for (Map.Entry<String, Double> entry : map.entrySet()){
            String key = entry.getKey();
            double value = entry.getValue();
            if (value==maxValue)
                System.out.println(key);
        }
    }
}
