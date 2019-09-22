package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
*/
// /Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2003/javarush.properties

public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        sc.close();
        try (InputStream fis = new FileInputStream(fileName)) {
            load(fis);
//            OutputStream outputStream = new FileOutputStream("/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2003/javarush.properties2");
//            save(outputStream);
//            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties property = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet())
            property.setProperty(entry.getKey(), entry.getValue());
        property.store(outputStream, "Properties");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties property = new Properties();
        property.load(inputStream);
        for (Map.Entry entry : property.entrySet()){
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            properties.put(key,value);
        }
        for (Map.Entry<String, String> entry : properties.entrySet()){
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            System.out.println(key + " : " + value);
        }

    }

    public static void main(String[] args) {
//        Solution sol = new Solution();
//        sol.fillInPropertiesMap();

    }
}
