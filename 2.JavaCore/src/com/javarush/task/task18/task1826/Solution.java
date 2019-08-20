package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;

public class Solution {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[1]);
        byte[] data = new byte[fis.available()];
        byte[] finalData = null;
        fis.read(data);
        fis.close();
        switch (args[0]){
            case "-e":
                finalData = java.util.Base64.getEncoder().encode(data);
                break;
            case "-d":
                finalData = java.util.Base64.getDecoder().decode(data);
        }
        FileOutputStream fos = new FileOutputStream(args[2]);
        fos.write(finalData);
        fos.close();
    }

}
