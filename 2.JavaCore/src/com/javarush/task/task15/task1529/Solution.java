package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        reset();

    }

    public static CanFly result;

    public static void reset() {
        //add your code here - добавьте код тут
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            if (line.equals("helicopter")) result = new Helicopter();
            else if (line.equals("plane")){
                String line2 = br.readLine();
                int amount = Integer.parseInt(line2);
                result = new Plane(amount);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
