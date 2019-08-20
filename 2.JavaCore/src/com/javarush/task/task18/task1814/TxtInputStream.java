package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream  {
    private String fileName;
    private FileInputStream fileInputStream;

    public TxtInputStream(String fileName) throws UnsupportedFileNameException, IOException {
       super(fileName);

        if (fileName.matches(".+\\.txt")){
            fileInputStream = new FileInputStream(fileName);
        }else{
            super.close();
            throw new UnsupportedFileNameException ();
        }

    }


    public static void main(String[] args) {
    }
}

