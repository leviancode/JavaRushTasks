package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String fileName;
        while (!(fileName = reader.readLine()).equals("exit")){
            ReadThread readThread = new ReadThread(fileName);
            readThread.start();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;
        private FileInputStream fis;
        private byte[] array;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }
        @Override
        public void run() {
            try {
                fis = new FileInputStream(fileName);
                array = new byte[fis.available()];
                fis.read(array);
                fis.close();

                int preCount = 0;
                int max = 0;

                for (int i = 0; i<array.length; i ++){
                    int count = 0;
                    for (byte b : array){
                        if (array[i] == b)
                            count++;
                    }
                    if (count>preCount){
                        preCount = count;
                        max = array[i];
                    }
                }
                resultMap.put(fileName, max);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
