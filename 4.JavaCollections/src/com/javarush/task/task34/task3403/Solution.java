package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        if (n > 1) {
            for (int i = 2; i < n / 2; i++) {
                if (n % i == 0) {
                    System.out.print(i + " ");
                    recurse(n / i);
                    return;
                }
            }
            System.out.print(n);
        }
    }

    public static void main(String[] args) {
      //  new Solution().recurse(Integer.MAX_VALUE-1);
    }
}
