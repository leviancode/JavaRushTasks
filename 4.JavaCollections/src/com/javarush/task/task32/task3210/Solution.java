package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        int size = text.length();
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        byte[] data = new byte[size];
        raf.seek(number);
        raf.read(data, 0,size);

        String result = new String(data);
        raf.seek(raf.length());
        raf.write((String.valueOf(result.equals(text))).getBytes());
        raf.close();
    }
}
