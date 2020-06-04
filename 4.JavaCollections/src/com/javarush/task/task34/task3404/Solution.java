package com.javarush.task.task34.task3404;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        Pattern pattern = Pattern.compile("(sin|cos|tan)(.*)");
        Matcher matcher = pattern.matcher(expression);

        if (matcher.find()){
            System.out.println(matcher.group(2));
        }
    }

    public Solution() {
        //don't delete
    }
}
