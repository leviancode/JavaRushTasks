package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedInputStream bufferedInputStream;
        String fileName =  null;
        Map <Integer, byte[]> map = new TreeMap<>();
        String [] lines = null;
        boolean flag = true;

        while (!((fileName = reader.readLine()).equals("end"))){
            bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));

            if(flag){
                 lines = fileName.split("\\.part\\d+");
                 flag = false;
            }

            String [] line = fileName.split("part");
            int part = Integer.parseInt(line[line.length-1]);

            byte [] byteArray = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(byteArray);
            bufferedInputStream.close();

            map.put(part, byteArray);
        }
        reader.close();

        FileOutputStream fos = new FileOutputStream(lines[0],true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);

        for (Map.Entry<Integer, byte[]> entry : map.entrySet()){
            byte [] bytes = entry.getValue();
            bufferedOutputStream.write(bytes);
        }
        bufferedOutputStream.close();
    }
}
