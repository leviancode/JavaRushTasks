package com.javarush.task.task13.task1319;

import javax.imageio.IIOException;
import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            String fileName = br.readLine();
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String s;
            while(true){
                s = br.readLine();
                if (!s.equals("exit")) {
                    bw.write(s+"\n");
                }
                else {
                    bw.write(s);
                    break;
                }
            }
            bw.close();
            br.close();


        }catch(IOException e){
            e.printStackTrace();
        }



    }
}
