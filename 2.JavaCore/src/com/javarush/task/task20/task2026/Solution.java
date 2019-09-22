package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        byte[][] a3 = new byte[][]{
                {1, 0, 0, 1},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
        int count3 = getRectangleCount(a3);
        System.out.println("count = " + count3 + ". Должно быть 5");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;

        for (int i=0; i < a.length; i++){
            byte [] out = a[i];
            for (int j = 0; j < out.length; j++){
                if (out[j] == 1){
                    count++;
                    if ((j != out.length-1 && out[j+1] == 1) || (i != a.length-1 && a[i+1][j] == 1) )
                        count--;
                }
            }
        }


        return count;
    }
}
