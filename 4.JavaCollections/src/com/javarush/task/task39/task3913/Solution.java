package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        Date after = formatter.parse("13.09.2013 5:04:50");
        Date before = formatter.parse("14.10.2021 11:38:21");

        LogParser parser = new LogParser(Paths.get("/Users/daniel/Google Drive/Projects/Java/IDEA/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs/"));

        Set<Object> execute = parser.execute("get event for user = \"Amigo\"");
        Set<Object> execute1 = parser.execute("get event");
        Set<Object> execute2 = parser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        Set<Object> execute3 = parser.execute("get user for ip = \"127.0.0.1\" and date between \"11.12.2013 0:00:00\" and \"14.11.2015 07:08:01\"");
        Set<Object> execute4 = parser.execute("get ip for date = \"30.01.2014 12:56:22\" and date between \"11.12.2013 0:00:00\" and \"14.11.2015 07:08:01\"");
        Set<Object> execute5 = parser.execute("get user for event = \"SOLVE_TASK\"");

        System.out.println(execute);
        System.out.println(execute1);
        System.out.println(execute2);
        System.out.println(execute3);
        System.out.println(execute4);
        System.out.println(execute5);
    }
}