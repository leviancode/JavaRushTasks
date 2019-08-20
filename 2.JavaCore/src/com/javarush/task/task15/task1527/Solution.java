package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String url = br.readLine();
        int index;
        String general;
        String [] lines = null;
        boolean flag = false;

        if ((index = url.indexOf("?"))!=-1){
            general = url.substring(index+1);
            lines = general.split("&");
        }
        for (String line : lines){
            if (line.contains("obj")) flag = true;
            if ((index = line.indexOf("="))!=-1){
                System.out.print(line.substring(0,index) + " ");
            }else{
                System.out.print(line + " ");
            }
        }
        if (flag==true) {
            System.out.println();
            for (String obj : lines) {
                if (obj.contains("obj")) {
                    String s = obj.substring(obj.indexOf("=") + 1);
                        try {
                            alert(Double.parseDouble(s));
                        } catch (NumberFormatException e) {
                            alert(s);
                        }
                }
            }
        }
        br.close();

    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
