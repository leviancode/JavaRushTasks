package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//  /Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/Text.xml

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String tag = args[0];


        FileReader fileReader = new FileReader(fileName);
        String line = "";
        while (fileReader.ready()){
            line += Character.toString((char)fileReader.read());
        }
        fileReader.close();

        line = line.replaceAll("(\n|\r)", " ");
      //  System.out.println(line + "\n");

        Pattern pattern1 = Pattern.compile("<"+tag);
        Pattern pattern2 = Pattern.compile("</"+tag+">");

        Matcher matcher1 = pattern1.matcher(line);
        Matcher matcher2 = pattern2.matcher(line);

        List<Integer> beginTag = new ArrayList<>();
        List<Integer> endTag = new ArrayList<>();
        List<String> result = new ArrayList<>();

        while (matcher1.find()){
            beginTag.add(matcher1.start());
        }

        while (matcher2.find()){
            endTag.add(matcher2.end());
        }
//        System.out.println(beginTag.toString());
//        System.out.println(endTag.toString());
//        System.out.println("\n");

        for (int i = 0; i < beginTag.size(); i++){
            int start = beginTag.get(i);
            int index = i;
            for (int j = 0; j < endTag.size(); j++){
                int end = endTag.get(j);
                if (end>start){
                    if (index != beginTag.size()-1){
                        if (end <= beginTag.get(index+1)){
                            result.add(line.substring(start, end) + "\n");
                            break;
                        } else {
                            index++;
                            continue;
                        }
                    } else {
                        result.add(line.substring(start, end) + "\n");
                        break;
                    }
                }else continue;
            }
        }
        for (String s : result) {
            System.out.print(s);
        }
    }
}
