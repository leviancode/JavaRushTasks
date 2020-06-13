package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    private static final Map<Character, Integer> romanNumeralsMap;

    static {
        romanNumeralsMap = new HashMap<>();
        romanNumeralsMap.put('I', 1);
        romanNumeralsMap.put('V', 5);
        romanNumeralsMap.put('X', 10);
        romanNumeralsMap.put('L', 50);
        romanNumeralsMap.put('C', 100);
        romanNumeralsMap.put('D', 500);
        romanNumeralsMap.put('M', 1000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int result = 0;
        int lastDigit = 0;

        for (int i = s.length()-1; i >= 0; i--) {
            char c = Character.toUpperCase(s.charAt(i));
            int digit = romanNumeralsMap.get(c);
            if (digit >= lastDigit)
                result += digit;
            else
                result -= digit;
            lastDigit = digit;
        }
        return result;
    }
}
