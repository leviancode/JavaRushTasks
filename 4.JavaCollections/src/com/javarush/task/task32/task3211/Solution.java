package com.javarush.task.task32.task3211;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.stream.Stream;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("test string");
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] msgBytes = byteArrayOutputStream.toByteArray();
        messageDigest.update(msgBytes);
        byte[] hash = messageDigest.digest();
        BigInteger bi = new BigInteger(1, hash);
        StringBuilder md5HexHash = new StringBuilder(bi.toString(16));
        while( md5HexHash.length() < 32 )
            md5HexHash.insert(0, "0");

        return md5HexHash.toString().equals(md5);
    }
}
