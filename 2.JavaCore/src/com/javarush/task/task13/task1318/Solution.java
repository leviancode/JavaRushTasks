package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.FileInputStream;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String name = br.readLine();
        FileInputStream fis = new FileInputStream(name);

        while (fis.available()>0){
            System.out.print((char)fis.read());
        }
        br.close();
        fis.close();

    }
}