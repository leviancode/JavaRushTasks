package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// /Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1828/Text.txt
// -u 32 "iMac with retina display" 1999.99 150

public class Solution {
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private static List<String> list;
    private static String fileName;

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
        list = readFile();

        if (args.length!=0) {

            switch (args[0]) {
                case "-u":
                    updateItem(args);
                    break;
                case "-d":
                    deleteItem(args[1]);
                    break;
            }
            updateFile();
        }

        reader.close();
        writer.close();
    }



    private static void updateFile() throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));

        for (String line : list){
            writer.write(line + "\n");
        }
    }

    private static List <String> readFile () throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
        List<String> list = new ArrayList<>();
        while (reader.ready()){
            list.add(reader.readLine());
        }
        return list;
    }
    private static void updateItem (String[] args) {
        String id = args[1];
        String productName = args[2];
        String price = args[3];
        String quantity = args [4];

        if (id.length()>8)
            id = id.substring(0,8);
        if (productName.length() > 30)
            productName = productName.substring(0,30);
        if (price.length()>8)
            price = price.substring(0,8);
        if (quantity.length()>4)
            quantity = quantity.substring(0,4);

        StringBuffer stringBuffer = new StringBuffer("                                                  ");
        stringBuffer.insert(0, id);
        stringBuffer.insert(8, productName);
        stringBuffer.insert(38, price);
        stringBuffer.insert(46, quantity);
        stringBuffer.setLength(50);

        for (int i = 0; i<list.size(); i++){
            String str = list.get(i).substring(0,7).trim();
            if (str.equals(id))
                list.set(i, stringBuffer.toString());
        }
    }

    private static void deleteItem(String id) {
        for (int i = 0; i<list.size(); i++){
            String str = list.get(i).substring(0,7).trim();
            if (str.equals(id))
                list.remove(i);
        }
    }
}
