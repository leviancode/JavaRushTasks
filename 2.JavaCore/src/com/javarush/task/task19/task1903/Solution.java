package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static{
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {


    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(data.getCountryCode());
        }

        @Override
        public String getName() {
            return data.getContactLastName() + ", " + data.getContactFirstName();
        }

        @Override
        public String getPhoneNumber() {
            String number = String.valueOf(data.getPhoneNumber());
            StringBuilder stringBuilder = new StringBuilder();
            if (number.length() < 10){
                for (int i = number.length(); i<10; i++)
                    stringBuilder.append("0");
            }
            stringBuilder.append(number);
            try {
                MaskFormatter maskFormatter = new MaskFormatter("(###)###-##-##");
                maskFormatter.setValueContainsLiteralCharacters(false);
                number = maskFormatter.valueToString(stringBuilder);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "+" + data.getCountryPhoneCode() + number;

//            return "+" + data.getCountryPhoneCode()
//                    + "(0" + number.substring(0,2) + ")"
//                    + number.substring(2,5) + "-" + number.substring(5,7) + "-" + number.substring(7,9);
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example: 501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67
    }
}