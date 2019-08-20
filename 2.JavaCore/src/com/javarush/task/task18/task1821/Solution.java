package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String name = args[0];
        FileInputStream fis = new FileInputStream(name);
        byte [] byteArray = new byte [fis.available()];
        Map<Integer, Integer> map = new TreeMap();

        fis.read(byteArray);

        //подсчет повторений
        for (int i = 0; i < byteArray.length; i++){
            int symbol = byteArray[i];
            int count = 0;
            for (int j = 0; j<byteArray.length; j++){
                int symbol2 = byteArray[j];
                if (symbol==symbol2) count++;
            }
            map.put(symbol,count);
        }

        //вывод
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            System.out.println((char) key + " " + value);
        }
        fis.close();
    }
}
