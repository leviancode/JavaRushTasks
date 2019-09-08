package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String[] person;
            String lastName;
            String firstName;
            String middleName;
            Date birthDate = new Date();
            DateFormat format = new SimpleDateFormat("dd MM yyyy");

            if (fileScanner.hasNextLine()) {
                person = fileScanner.nextLine().split(" ");
                lastName = person[0];
                firstName = person[1];
                middleName = person[2];
                String birth = person[3] + " " + person [4] + " " + person[5];
                try {
                    birthDate = format.parse(birth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return new Person(firstName, middleName, lastName, birthDate);
            }else{
                return null;
            }
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
