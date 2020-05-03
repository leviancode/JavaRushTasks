package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] pass = new byte[8];
        int countDigit = 0;
        int countUpperCase = 0;
        int countLowerCase = 0;

        for (int i = 0; i < 8; i++){
            // what kind of symbol: 1 - digit, 2 - CHAR, 3 - char
            int k = ThreadLocalRandom.current().nextInt(1,4);
            switch (k){
                case 1:
                    pass[i] = (byte) ThreadLocalRandom.current().nextInt(48,58);
                    countDigit++;
                    break;
                case 2:
                    pass[i] = (byte) ThreadLocalRandom.current().nextInt(65,91);
                    countUpperCase++;
                    break;
                case 3:
                    pass[i] = (byte) ThreadLocalRandom.current().nextInt(97,123);
                    countLowerCase++;
            }
            // if generated not all kind of symbols - start loop again
            if (i == 7 && (countDigit == 0 || countUpperCase == 0 || countLowerCase == 0)) {
                i = 0;
                countDigit = 0;
                countUpperCase = 0;
                countLowerCase = 0;
            }
        }
        try {
            baos.write(pass);
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }
}