package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {

        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
    public static String printMatrix(long m, int n, String value) {
        return value;
    }
    public static int printMatrix(short m, int n) {
        int a = m+n;
        return a;

    }
    public static String printMatrix (String value) {
                return value;
    }
    public static void printMatrix(){
        System.out.println("Ahaha");

    }
    public static int printMatrix(int m, int n, int i) {
        return i+n+i;

    }
    public static double printMatrix(int m, int n) {
        double d = (double)(m+n);
        return d;

    }
    public static short printMatrix(short m) {
        return m;

    }
    public static Integer printMatrix(Integer n) {
        return n;

    }
}
