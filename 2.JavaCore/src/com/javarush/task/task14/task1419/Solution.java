package com.javarush.task.task14.task1419;

import java.io.FileReader;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код
        try{
            Object i = Integer.valueOf(42);
            String s = (String)i;

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            int[] array = new int[]{1,2,3,4,5};
            int x = array[6];

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            String s = null;
            s.indexOf(5);

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            String s = "abc";
            int i = Integer.parseInt(s);

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            int[] array2 = new int[-5];

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            Set sampleSet = new HashSet();
            sampleSet.iterator().next();


        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            String s = "abcdef";
            char a = s.charAt(7);

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            Object[] o = "a;b;c".split(";");
            o[0] = 42;

        }catch (Exception e){
            exceptions.add(e);
        }

        try{
            FileReader fr = new FileReader("Lololo");

        }catch (Exception e){
            exceptions.add(e);
        }

    }
}
