package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        testString.printSomething();
        String line = outputStream.toString();
        String[] arr = line.split(" ");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[2]);
        int result = 0;
        if (arr[1].equals("+"))
            result = x + y;
        else if (arr[1].equals("-"))
            result = x - y;
        else if (arr[1].equals("*"))
            result = x * y;
        line += String.valueOf(result);

        System.setOut(consoleStream);
        System.out.print(line);
    }

    public static class TestString {
        public void printSomething() {
            System.out.print("3 + 6 = ");
        }
    }
}

