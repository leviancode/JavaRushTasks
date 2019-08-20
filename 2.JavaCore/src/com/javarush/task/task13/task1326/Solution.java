package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(br.readLine())));

        int a;
        while (fileReader.ready()) {
            a = Integer.parseInt(fileReader.readLine());
            if (a % 2 == 0) {
                list.add(a);
            }
        }
        Collections.sort(list);
        for (int i : list) {
            System.out.println(i);
        }
        br.close();
        fileReader.close();
    }
}
