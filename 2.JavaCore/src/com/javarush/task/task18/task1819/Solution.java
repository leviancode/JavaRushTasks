package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);


        List<Byte> list = new ArrayList<>();

        byte[] array1 = new byte[fis1.available()];
        byte[] array2 = new byte[fis2.available()];

        fis1.read(array1);
        fis2.read(array2);

        for (byte b : array2){
            list.add(b);
        }
        for (byte b : array1){
            list.add(b);
        }

        fis1.close();
        fis2.close();

        FileOutputStream fos = new FileOutputStream(file1);
        for (byte b : list){
            fos.write(b);
            System.out.print((char) b);
        }

        reader.close();
        fos.close();


    }
}
