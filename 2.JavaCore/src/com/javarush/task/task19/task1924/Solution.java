package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        consoleReader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        List<String> listWords = new ArrayList<>();
        while (fileReader.ready()){
            String line = fileReader.readLine();
            listWords.add(line);
        }
        fileReader.close();

        Pattern pattern = Pattern.compile("(^|\\s|[^a-zA-Zа-яА-Я0-9])+(\\d+)([^a-zA-Zа-яА-Я0-9]|\\s|$)+");
        Matcher matcher;
        for (int i = 0; i < listWords.size(); i ++){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(listWords.get(i));
            matcher = pattern.matcher(stringBuilder);

            while (matcher.find()) {
                String found = matcher.group(2);
                int key = Integer.parseInt(found);

                if (map.containsKey(key)) {
                    String value = map.get(key);
                    stringBuilder.replace(matcher.start(2), matcher.end(2), value);
                    matcher.reset();
                }
            }
            listWords.set(i, stringBuilder.toString());
        }

        for (String line : listWords){
            System.out.println(line);
        }
    }
}
