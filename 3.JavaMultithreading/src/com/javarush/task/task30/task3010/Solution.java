package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {

        try {
            String number = args[0];
            if (!number.matches("\\w*"))
                System.out.println("incorrect");
            else {
                for (int i = 2; i <= 36; i++) {
                    try {
                        BigInteger bi = new BigInteger(number, i);
                        System.out.println(i);
                        break;
                    } catch (Exception e) {
                    }
                }
            }
        }catch (Exception e){}
    }
}