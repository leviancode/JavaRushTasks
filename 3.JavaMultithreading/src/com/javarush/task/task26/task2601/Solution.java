package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
       // Integer[] array = {13, 8, 15, 5, 17, 8};
       // System.out.println(Arrays.toString(sort(array)));

    }

    public static Integer[] sort(Integer[] array) {
        int median = takeMedian(array);
        Comparator<Integer> medianCompare = (o1, o2) -> {
            int x = Math.abs(median - o1);
            int y = Math.abs(median - o2);
            return Integer.compare(x, y);
        };

        Arrays.sort(array, medianCompare);

        return array;
    }

    private static int takeMedian(Integer[] array){
        Arrays.sort(array);
        return array.length % 2 != 0 ? array[array.length/2] : array[array.length/2]/2 + array[array.length/2-1]/2;
    }
}
