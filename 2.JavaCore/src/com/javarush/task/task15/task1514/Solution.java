package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static{
        labels.put(1.0, "I");
        labels.put(2.2, "love");
        labels.put(3.4, "Java");
        labels.put(4.6, "Rush");
        labels.put(5.8, "dot");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
