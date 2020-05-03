package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path dirPath = Paths.get(scanner.nextLine());

        if (Files.isDirectory(dirPath)){
            AtomicInteger dirCount = new AtomicInteger(-1);
            AtomicInteger filesCount = new AtomicInteger(0);
            AtomicInteger totalSize = new AtomicInteger(0);

            Files.walk(dirPath).forEach(path -> {
                if (Files.isDirectory(path))
                    dirCount.incrementAndGet();
                if (Files.isRegularFile(path)) {
                    filesCount.incrementAndGet();
                    try {
                        totalSize.addAndGet(Files.readAllBytes(path).length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            System.out.println("Всего папок - " + dirCount.get());
            System.out.println("Всего файлов - " + filesCount.get());
            System.out.println("Общий размер - " + totalSize.get());

        }else
            System.out.println(dirPath.toString() + " - не папка");
    }
}
