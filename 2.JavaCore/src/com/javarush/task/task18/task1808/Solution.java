package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(file2);
        FileOutputStream outputStream2 = new FileOutputStream(file3);

        byte [] array = new byte [inputStream.available()];
        inputStream.read(array);

        for (int i = 0; i < array.length; i++){
            if (array.length%2==0){
                if (i < (array.length/2)){
                    outputStream.write(array[i]);
                }else{
                    outputStream2.write(array[i]);
                }
            }else{
                if (i <= (array.length/2)){
                    outputStream.write(array[i]);
                }else{
                    outputStream2.write(array[i]);
                }
            }

        }

        reader.close();
        inputStream.close();
        outputStream.close();
        outputStream2.close();

    }
}
