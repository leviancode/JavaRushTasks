package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();

        FileInputStream inputStream = new FileInputStream(name1);
        FileOutputStream outputStream = new FileOutputStream(name2);

        byte[] array = new byte[inputStream.available()];
        inputStream.read(array);

        for (int i = array.length-1; i>=0; i--){
            outputStream.write(array[i]);
        }

        reader.close();
        inputStream.close();
        outputStream.close();

    }
}
