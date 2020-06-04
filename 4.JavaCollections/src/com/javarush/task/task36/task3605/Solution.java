package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> treeSet = new TreeSet<>();
        Path filePath = Paths.get(args[0]);
        Files.lines(filePath)
                .map(String::chars)
                .forEach(intStream -> intStream
                        .filter(Character::isLetter)
                        .map(Character::toLowerCase)
                        .forEach(c -> treeSet.add((char)c)));

        treeSet.stream().limit(5).forEach(System.out::print);
    }
}
