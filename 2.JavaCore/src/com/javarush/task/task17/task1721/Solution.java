package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        Solution sol = new Solution();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = null;
        BufferedReader fr2 = null;

        try {
            String name = reader.readLine();
            String name2 = reader.readLine();

            fr = new BufferedReader(new FileReader(new File (name)));
            fr2 = new BufferedReader(new FileReader(new File(name2)));

            String line;
            while ((line= fr.readLine())!= null){
                allLines.add(line);
            }

            String line2;
            while ((line2=fr2.readLine()) != null){
                forRemoveLines.add(line2);
            }

            sol.joinData();

            reader.close();
            fr.close();
            fr2.close();

        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
        boolean contains = false;

        for (String s : forRemoveLines){
            if (allLines.contains(s)) {
                contains = true;
            }
            else {
                contains = false;
                break;
            }
        }

        if (contains){
            allLines.removeAll(forRemoveLines);
        }else{
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
