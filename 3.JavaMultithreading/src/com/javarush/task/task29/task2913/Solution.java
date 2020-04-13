package com.javarush.task.task29.task2913;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder sb = new StringBuilder();

        if (a < b) {
            for (int i = a; i <= b; i++){
                sb.append(i).append(" ");
            }
        } else {
            if (a == b) {
                return Integer.toString(a);
            }
            for (int i = a; i >= b; i--){
                sb.append(i).append(" ");
            }
        }
        return sb.toString().trim();

    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}