package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NThread extends Thread {
    BufferedReader reader;
    int sum;
    @Override
    public void run() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (true) {
            try {
                line = reader.readLine();
                if (!line.equals("N")){
                    sum += Integer.parseInt(line);
                }else{
                    System.out.println(sum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } {

            }
        }
    }
}
