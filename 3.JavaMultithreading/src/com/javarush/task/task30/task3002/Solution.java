package com.javarush.task.task30.task3002;

import java.math.BigInteger;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        String number = s;
        int system = 10;

        if (s.startsWith("0x")) {
            number = s.substring(2);
            system = 16;
        }
        else if (s.startsWith("0b")){
            number = s.substring(2);
            system = 2;
        }
        else if (s.startsWith("0")){
            number = s.substring(1);
            system = 8;
        }
        return String.valueOf(Integer.parseInt(number,system));
    }
}
