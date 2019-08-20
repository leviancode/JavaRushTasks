package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        int x = Integer.parseInt(reader.readLine());
        if(x<1) throw new IllegalArgumentException("Необходимо целое, положительное число");
        int y = Integer.parseInt(reader.readLine());
        if(y<1) throw new IllegalArgumentException("Необходимо целое, положительное число");



        for (int i = x; i>=1; i--){
            if(x%i==0) list1.add(i);
            else continue;
        }

        for (int i = y; i>=1; i--){
            if(y%i==0) list2.add(i);
            else continue;
        }

        if (x<y){
            for (int i : list1){
                if (list2.contains(i)) {
                    System.out.println(i);
                    break;
                }
            }
        }else{
            for (int i : list2){
                if (list1.contains(i)) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
