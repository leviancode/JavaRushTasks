package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        String fileName = args[0];
        // String fileName = "/Users/daniel/Projects/Java/IDEA/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1921/Text.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (bufferedReader.ready()){
            String line = bufferedReader.readLine();
            String [] data = line.split(" ");
            String name = "";
            String birth = "";

            for (int i = 0; i < data.length; i ++){
                String s = data[i];
                if (s.matches("\\d+"))
                    birth += s + " ";
                else
                    name += s + " ";
            }

            Date dateBirth = new SimpleDateFormat("dd MM yyyy").parse(birth);
            PEOPLE.add(new Person(name.trim(), dateBirth));
        }
        bufferedReader.close();

    }
}
