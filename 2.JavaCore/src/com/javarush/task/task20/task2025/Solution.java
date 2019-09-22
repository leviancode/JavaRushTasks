package com.javarush.task.task20.task2025;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] armstrong = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084,
                548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208, 472335975,
                534494836, 912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L,
                49388550606L, 82693916578L, 94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L,
                21897142587612075L, 35641594208964132L, 35875699062250035L, 1517841543307505039L,
                3289582984443187032L, 4498128791164624869L, 4929273885928088826L, 9223372036854775807L};
        long[] result = null;
        if (N < 1)
            return null;
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; armstrong[i] < N; i++){
            set.add(armstrong[i]);
        }

        /*


        char[] arr;
        long num;
        for (int i = 1; i < N; i++) {
            arr = String.valueOf(i).toCharArray();
            num = 0;
            for (int j = 0; j < arr.length; j++)
                num += (long) Math.pow(Character.getNumericValue(arr[j]), arr.length);

            if (num == i)
                set.add(num);
        }
*/
        result = new long[set.size()];

        int itr = 0;
        for (long l : set) {
            result[itr] = l;
            itr++;
        }
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long[] num = getNumbers(Long.MAX_VALUE);
        long end = System.currentTimeMillis();
        System.out.println("Программа выполнилась за: " + (end-start)/1000 + " сек.");
        System.out.println("Заняло памяти: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");
        System.out.println();
        System.out.println(Arrays.toString(num));

    }
}
