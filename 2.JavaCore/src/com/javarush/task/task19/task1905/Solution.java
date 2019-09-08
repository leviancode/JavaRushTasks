package com.javarush.task.task19.task1905;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static{
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String code = null;
            for (Map.Entry<String, String> entry : countries.entrySet()){
                String value = entry.getValue();
                if (value.equals(customer.getCountryName()))
                    code = entry.getKey();
            }
            return code;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String[] name = contact.getName().split(", ");
            return name[1];
        }

        @Override
        public String getContactLastName() {
            String[] name = contact.getName().split(", ");
            return name[0];
        }

        @Override
        public String getDialString() {
            char [] arr = contact.getPhoneNumber().toCharArray();
            String number = "+";
            for (char c : arr){
                if (Character.isDigit(c))
                    number += String.valueOf(c);
            }
            return "callto://" + number;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA
        String getCompany();            //For example: JavaRush Ltd.
        String getContactFirstName();   //For example: Ivan
        String getContactLastName();    //For example: Ivanov
        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.
        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan
        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}