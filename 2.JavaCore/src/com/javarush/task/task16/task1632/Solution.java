package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new InfinityThread());
        threads.add(new ExceptionThread());
        threads.add(new UhuThread());
        threads.add(new MessageThread());
        threads.add(new NThread());
    }

    public static void main(String[] args) {
    }
}