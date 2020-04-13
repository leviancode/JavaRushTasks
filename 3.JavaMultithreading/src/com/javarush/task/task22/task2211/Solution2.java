package com.javarush.task.task22.task2211;

public class Solution2 {

    /*
    Проверка номера телефона

    +380501234567 - true +
    +38(050)1234567 - true +
    +38050123-45-67 - true
    050123-4567 - true +
    +38)050(1234567 - false
    +38(050)1-23-45-6-7 - false
    050ххх4567 - false
    050123456 - false
    (0)501234567 - false

    */

        public static boolean checkTelNumber(String telNumber) {
            if (telNumber == null) return false;

            return telNumber.matches("(\\+\\d{2})?((\\(\\d{3}\\))|(\\d{3}))\\d{3}((-?\\d{4})|(-\\d{2}-\\d{2}))");
        }

        public static void main(String[] args) {
           /* String s1 = "+380501234567";
            System.out.println(checkTelNumber(s1) + ", must be 'true'");
            String s2 = "+38(050)1234567";
            System.out.println(checkTelNumber(s2) + ", must be 'true'");
            String s3 = "+38050123-45-67";
            System.out.println(checkTelNumber(s3) + ", must be 'true'");
            String s4 = "050123-4567";
            System.out.println(checkTelNumber(s4) + ", must be 'true'");

            String s5 = "+38)050(1234567";
            System.out.println(checkTelNumber(s5) + ", must be 'false'");
            String s6 = "+38(050)1-23-45-6-7";
            System.out.println(checkTelNumber(s6) + ", must be 'false'");
            String s7 = "050ххх4567";
            System.out.println(checkTelNumber(s7) + ", must be 'false'");
            String s8 = "(0)501234567";
            System.out.println(checkTelNumber(s8) + ", must be 'false'");*/

        }
}
