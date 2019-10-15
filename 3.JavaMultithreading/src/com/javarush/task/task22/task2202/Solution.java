package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();
        StringBuilder output;
        String [] words;

        try {
            words = string.split(" ");
            output = new StringBuilder();

            for (int i = 1; i <= 4; i++)
                output.append(words[i]).append(" ");
        }catch (RuntimeException e){
            throw new TooShortStringException();
        }

        return output.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
