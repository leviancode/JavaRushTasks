package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        if (FileUtils.isExist(resultFileAbsolutePath))
            FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try(FileOutputStream writer = new FileOutputStream(allFilesContent, true)){
            Files.walk(Paths.get(path.getAbsolutePath()))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(f -> f.length() <= 50)
                    .sorted(Comparator.comparing(File::getName))
                    .forEach(file -> {
                        try {
                            byte[] data = Files.readAllBytes(file.toPath());
                            writer.write(data);
                            writer.write(System.lineSeparator().getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
