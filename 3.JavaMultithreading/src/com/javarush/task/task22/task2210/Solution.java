package com.javarush.task.task22.task2210;

import java.util.*;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {



    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer stringTok = new StringTokenizer(query, delimiter);
        List<String> splitString = new ArrayList();

        while (stringTok.hasMoreTokens()){
            splitString.add(stringTok.nextToken());
        }
        String [] result = new String[splitString.size()];
        for (int i = 0; i < splitString.size(); i++)
            result[i] = splitString.get(i);

            return result;
    }
}
