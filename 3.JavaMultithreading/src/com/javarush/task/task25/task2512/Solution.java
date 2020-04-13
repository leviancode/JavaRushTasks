    package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        Throwable cause = e.getCause();
        if (cause != null)
            uncaughtException(t, cause);

        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));

    }
}
