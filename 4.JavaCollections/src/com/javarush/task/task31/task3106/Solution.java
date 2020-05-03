package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        List<String> fileNameParts = Arrays.stream(args).skip(1).sorted().collect(Collectors.toList());
        List<FileInputStream> fisList = new ArrayList<>();

        for (String part : fileNameParts)
            fisList.add(new FileInputStream(part));

        try(ZipInputStream zipReader = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fisList)));
            FileOutputStream fileWriter = new FileOutputStream(resultFileName)){

            byte[] buf = new byte[1024 * 1024];
            while(zipReader.getNextEntry() != null){
                int count;
                while ((count = zipReader.read(buf)) != -1){
                    fileWriter.write(buf, 0, count);
                }
            }

        }
    }
}
