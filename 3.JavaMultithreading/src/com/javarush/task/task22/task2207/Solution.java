package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        List<String> wordsList = new ArrayList<>();
        Map<String, String> wordsMap = new LinkedHashMap<>();


        try (BufferedReader bufferedFileReader = new BufferedReader(
                new FileReader(consoleReader.readLine()))){

            while (bufferedFileReader.ready()) {
                String[] words = bufferedFileReader.readLine().split(" ");
                wordsList.addAll(Arrays.asList(words));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // search matches for word - reverse word and put their into map
        StringBuilder sb;
        for (String firstWord : wordsList){
            int count = 0;
            for (String secondWord : wordsList){
                sb = new StringBuilder();
                String reverseWord = sb.append(secondWord).reverse().toString();
                if (!firstWord.equals(secondWord)) {
                    if (firstWord.equals(reverseWord)) {
                        wordsMap.put(firstWord, secondWord);
                        break;
                    }
                }else count++;
            }
            if (count > 1)
                wordsMap.put(firstWord, firstWord);
        }

        addPairsToResult(wordsMap);

        // debugging
        for (Pair p : result)
            System.out.println(p);
    }

    private static void addPairsToResult (Map<String, String> map){
        Pair pair = new Pair();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String first = entry.getKey();
            String second = entry.getValue();
            if (!first.equals(pair.first) && !first.equals(pair.second)) {
                pair = new Pair();
                pair.first = first;
                pair.second = second;
                result.add(pair);
            }
        }
    }

    public static class Pair {
        String first;
        String second;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
