package com.javarush.task.task22.task2203;

import java.util.Arrays;

/*
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string==null)
            throw new TooShortStringException();

        String [] words;
        String output;

        try{
            words = string.split("\t");
            if (words.length > 2)
                if (words[0].equals("\t"))
                    output = words [0];
                else
                    output = words [1];
            else
                throw new TooShortStringException();

        }catch (RuntimeException e){
            throw new TooShortStringException();
        }

        return output;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
