package com.javarush.task.task20.task2027;

import java.util.*;

/* 
Кроссворд
*/
public class Solution {

    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };


        System.out.println(detectAllWords(crossword, "home", "same", "leo", "vok", "glp", "samo"));
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();

        int startX;
        int startY;
        int endX;
        int endY;

        char[] wordArray;
        for (String word : words){
            wordArray = word.toCharArray();
            int [] coordinate = findMatch(crossword, wordArray);
            if (coordinate != null){
                startX = coordinate[0];
                startY = coordinate[1];
                endX = coordinate[2];
                endY = coordinate[3];

                Word wordObject = new Word(word);
                wordObject.setStartPoint(startX, startY);
                wordObject.setEndPoint(endX, endY);
                wordList.add(wordObject);
            }else
                System.out.println("Упс! Слова \"" + word + "\" нет в массиве");
        }

        return wordList;
    }

    private static int[] findMatch (int[][] crossword, char [] wordArray){
        int startX;
        int startY;
        int [] endPoint;
        char firstChar = wordArray[0];

        for (int y = 0; y < crossword.length; y++){
            for (int x = 0; x < crossword[y].length; x++){
                if (crossword[y][x] == firstChar){
                    startX = x;
                    startY = y;
                    endPoint = findLast(crossword, wordArray, startX, startY);
                    if (endPoint != null)
                        return new int[]{startX, startY, endPoint[0], endPoint[1]};
                }
            }
        }
        return null;
    }

    private static int [] findLast (int[][] crossword, char [] wordArray, int startX, int startY){
        int endX;
        int endY;
        int length = wordArray.length-1;

        for (int y = 0; y < crossword.length; y++){
            for (int x = 0; x < crossword[y].length; x++){
                if (crossword[y][x] == wordArray[length]){
                    endX = x;
                    endY = y;
                    if ((startX-length == endX || startX+length == endX || startX == endX) &&
                            (startY-length == endY || startY+length == endY || startY == endY)){
                        if (checkOtherChar(crossword, wordArray, startX, startY, endX, endY))
                            return new int[] {endX, endY};
                    }
                }
            }
        }
        return null;
    }

    private static boolean checkOtherChar (int [][] crossword, char[] wordArray, int startX, int startY, int endX, int endY){
        int x = startX;
        int y = startY;
        int length = wordArray.length-2;

        for (int i = 1; i <wordArray.length-1; i++){
            if (startX > endX)
                x--;
            if (startX < endX)
                x++;
            if (startY > endY)
                y--;
            if (startY < endY)
                y++;
            if (crossword[y][x] == wordArray[i])
                length--;
        }
        if (length == 0)
            return true;
        else
            return false;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
