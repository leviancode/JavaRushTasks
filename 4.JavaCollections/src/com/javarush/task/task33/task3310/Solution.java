package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        long startTime = new Date().getTime();
        Set<Long> ids = getIds(shortener, strings);
        long taskTime = new Date().getTime() - startTime;
        Helper.printMessage(String.valueOf(taskTime));

        long startTime2 = new Date().getTime();
        Set<String> values = getStrings(shortener, ids);
        long taskTime2 = new Date().getTime() - startTime2;
        Helper.printMessage(String.valueOf(taskTime2));

        if (values.size() == strings.size())
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");

    }
}
