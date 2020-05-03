package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String newFile = args[0];
        String zipPath = args[1];
        Map<String, ByteArrayOutputStream> zipEntryMap = new HashMap<>();

        try(ZipInputStream zipReader = new ZipInputStream(new FileInputStream(zipPath))){
            ZipEntry zipEntry;
            while ((zipEntry = zipReader.getNextEntry()) != null){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipReader.read(buffer)) != -1){
                    baos.write(buffer, 0, count);
                }
                zipEntryMap.put(zipEntry.getName(), baos);
            }
        }
        try(ZipOutputStream zipWriter = new ZipOutputStream(new FileOutputStream(zipPath))){
            for (Map.Entry<String, ByteArrayOutputStream> entry : zipEntryMap.entrySet()) {
                String key = entry.getKey();
                ByteArrayOutputStream value = entry.getValue();
                if (!key.endsWith(newFile)) {
                    zipWriter.putNextEntry(new ZipEntry(key));
                    zipWriter.write(value.toByteArray());
                }
            }
            zipWriter.putNextEntry(new ZipEntry("new/" + newFile));
            Files.copy(Paths.get(newFile), zipWriter);
        }
    }
}
