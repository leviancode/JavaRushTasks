package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

// /Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1916/Text.xml

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        List<String> file1List = new ArrayList<>();
        List<String> file2List = new ArrayList<>();

        FileReader file1Reader = new FileReader(fileName);
        BufferedReader bufferedFileReader1 = new BufferedReader(file1Reader);
        while (bufferedFileReader1.ready()){
            file1List.add(bufferedFileReader1.readLine());
        }
        file1Reader.close();
        bufferedFileReader1.close();

        FileReader file2Reader = new FileReader(fileName2);
        BufferedReader bufferedFileReader2 = new BufferedReader(file2Reader);

        while (bufferedFileReader2.ready()){
            file2List.add(bufferedFileReader2.readLine());
        }
        file2Reader.close();
        bufferedFileReader2.close();

        boolean flag = false;
        for (String line1 : file1List){
            if (file2List.contains(line1)) {
                if (!flag) {
                    lines.add(new LineItem(Type.SAME, line1));
                    flag = true;
                } else {
                    lines.add(null);
                    lines.add(new LineItem(Type.SAME, line1));
                }
            }else{
                lines.add(new LineItem(Type.REMOVED, line1));
                flag = false;
            }
        }
        for (String line2 : file2List){
            if (!file1List.contains(line2)){
                for (int i = 0; i < lines.size(); i++){
                    if (lines.get(i)==null){
                        lines.set(i, new LineItem(Type.ADDED, line2));
                        break;
                    }else if (i == lines.size()-1){
                        lines.add(new LineItem(Type.ADDED, line2));
                        break;
                    }
                }
            }

        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
