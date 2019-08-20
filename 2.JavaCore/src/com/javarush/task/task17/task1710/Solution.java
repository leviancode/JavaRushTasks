package com.javarush.task.task17.task1710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws IOException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Date date;

        String[] command = args;

        // "-c name sex bd" - добавляет человека в список
        if (command[0].equals("-c")){
            date = dateFormat.parse(command[3]);
            Person newPerson;
            if (command[2].equals("м"))
                newPerson = Person.createMale(command[1],date);
            else
                newPerson = Person.createFemale(command[1],date);
            allPeople.add(newPerson);
            System.out.println(allPeople.indexOf(newPerson));

        }
        // "-u id name sex bd" - обновляет данные о человеке
        else if (command[0].equals("-u")){
            int id = Integer.parseInt(command[1]);
            date = dateFormat.parse(command[4]);

            allPeople.get(id).setName(command[2]);

            if(command[3].equals("м"))
                allPeople.get(id).setSex(Sex.MALE);
            else
                allPeople.get(id).setSex(Sex.FEMALE);

            allPeople.get(id).setBirthDate(date);

        }
        // "-d id" - производит логическое удаление человека с id, заменяет все его данные на null
        else if (command[0].equals("-d")){
            int id = Integer.parseInt(command[1]);
            allPeople.get(id).setName(null);
            allPeople.get(id).setSex(null);
            allPeople.get(id).setBirthDate(null);

        }
        // "-i id" - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
        else if (command[0].equals("-i")){
            int id = Integer.parseInt(command[1]);
            String sex = allPeople.get(id).getSex()==Sex.MALE ? "м" : "ж";

            System.out.println(allPeople.get(id).getName() + " " + sex + " " + formatter.format(allPeople.get(id).getBirthDate()));

        }
    }
}
