package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        List<Integer> repeatList = new ArrayList<>();
        List<Integer> finalList = new ArrayList<>();



        String name = reader.readLine();
        FileInputStream fis = new FileInputStream(name);

        while (fis.available() > 0){
            list.add (fis.read());
        }

        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            for (int j : list) {
                if (j == (list.get(i))) count++;
            }
            repeatList.add(count);
        }

        reader.close();
        fis.close();

        int max = 0;
        for (int i : repeatList){
            if (i>max) max = i;
        }
        for (int i = 0; i < repeatList.size(); i++){
            if (repeatList.get(i)==max){
                if(!finalList.contains(list.get(i)))
                    finalList.add(list.get(i));
            }
        }
        for (int i : finalList){
            System.out.print(i + " ");
        }
    }
}
