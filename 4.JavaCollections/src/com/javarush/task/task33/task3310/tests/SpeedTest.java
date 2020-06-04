package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long startTime = new Date().getTime();
        ids = strings.stream().map(shortener::getId).collect(Collectors.toSet());
        return new Date().getTime() - startTime;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long startTime = new Date().getTime();
        strings = ids.stream().map(shortener::getString).collect(Collectors.toSet());
        return new Date().getTime() - startTime;
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long timeToGetIds1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long timeToGetIds2 = getTimeToGetIds(shortener2, origStrings, ids2);

        assertTrue(timeToGetIds1 > timeToGetIds2);

        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        long timeToGetStrings1 = getTimeToGetStrings(shortener1, ids1, strings1);
        long timeToGetStrings2 = getTimeToGetStrings(shortener2, ids2, strings2);

        assertEquals(timeToGetStrings1, timeToGetStrings2, 30);
    }
}
