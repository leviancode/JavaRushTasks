package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        FileInputStream inputStream;
        while (true){
                name = reader.readLine();
                inputStream = new FileInputStream(name);
                if (inputStream.available()<1000){
                    reader.close();
                    inputStream.close();
                    throw new DownloadException();
                }
        }

    }

    public static class DownloadException extends Exception {

    }
}
