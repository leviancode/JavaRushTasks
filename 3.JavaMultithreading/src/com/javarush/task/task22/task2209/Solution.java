package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
Составить цепочку слов
Киев Нью-Йорк Амстердам Вена Мельбурн

*/
public class Solution {
    public static void main(String[] args) {
        String fileName = new Scanner(System.in).nextLine();
        String[] words = null;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            words = fileReader.readLine().split(" ");
        }catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        if (words.length == 0)
            return sb;

        List<String> wordsList ;
        String word;

        for (int i = 0; i < words.length; i++){
            sb.append(words[i]).append(" ");
            word = words[i];
            wordsList = new LinkedList<>(Arrays.asList(words));
            while (wordsList.size() > 1){
                String nextWord = searchNextWord(word, wordsList);
                if (nextWord != null){
                    sb.append(nextWord).append(" ");
                    wordsList.remove(word);
                    word = nextWord;
                } else break;
            }
            if (wordsList.size() == 1) break;
            else sb.setLength(0);
        }
        sb.setLength(sb.length()-1);

        return sb;
    }

    private static String searchNextWord (String preWord, List<String> words){
        String nextWord = null;
        char lastChar = preWord.toLowerCase().charAt(preWord.length()-1);
        for (int j = 0; j < words.size();j++){
            String word = words.get(j).toLowerCase();
            char firstChar = word.charAt(0);
            if (lastChar == firstChar) {
                nextWord = words.get(j);
                break;
            }
        }
        return nextWord;
    }
}
