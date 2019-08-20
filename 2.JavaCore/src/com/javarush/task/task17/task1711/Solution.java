package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2

-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...

*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Date date;

        switch (args[0]){
            case "-c":
                synchronized (allPeople){
                    for (int i = 1; i<args.length; i+=3){
                        date = dateFormat.parse(args[i+2]);
                        Person newPerson;
                        if (args[i+1].equals("м"))
                            newPerson = Person.createMale(args[i], date);
                        else
                            newPerson = Person.createFemale(args[i], date);
                        allPeople.add(newPerson);
                        System.out.println(allPeople.indexOf(newPerson));
                    }

                } break;

            case "-u":
                synchronized (allPeople){
                    for (int i = 1; i<args.length; i+=4){
                        int id = Integer.parseInt(args[i]);
                        allPeople.get(id).setName(args[i+1]);
                        allPeople.get(id).setSex(args[i+2].equals("м") ? Sex.MALE : Sex.FEMALE);
                        allPeople.get(id).setBirthDate(dateFormat.parse(args[i+3]));
                    }
                } break;

            case "-d":
                synchronized (allPeople){
                    for (int i = 1; i<args.length; i++){
                        int id = Integer.parseInt(args[i]);
                        allPeople.get(id).setName(null);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setBirthDate(null);
                    }
                } break;

            case "-i":
                synchronized (allPeople){
                    for (int i = 1; i<args.length; i++){
                        int id = Integer.parseInt(args[i]);
                        String sex = allPeople.get(id).getSex()==Sex.MALE ? "м" : "ж";
                        System.out.println(allPeople.get(id).getName() + " " + sex + " " + formatter.format(allPeople.get(id).getBirthDate()));
                    }
                } break;
        }
    }
}
