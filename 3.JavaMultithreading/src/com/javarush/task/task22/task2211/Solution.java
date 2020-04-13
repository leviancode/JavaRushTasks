package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream(args[0]);
        OutputStream fos = new FileOutputStream(args[1]);

        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");

        byte [] inputFile = new byte[fis.available()];
        fis.read(inputFile);
        String lineInWinCode = new String(inputFile, windows1251);
        inputFile = lineInWinCode.getBytes(utf8);
        fos.write(inputFile);

    }
}
