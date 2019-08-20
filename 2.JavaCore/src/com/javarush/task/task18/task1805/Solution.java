package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new TreeSet<>();

        String name = reader.readLine();
        FileInputStream fis = new FileInputStream(name);

        while (fis.available() > 0){
            int i = fis.read();
            if (!set.contains(i)) set.add(i);
        }

        reader.close();
        fis.close();
        for (int i : set){
            System.out.print(i + " ");
        }

    }
}

