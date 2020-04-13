package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Формируем WHERE

name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'

*/
public class Solution {
    public static void main(String[] args) {
        /*
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);

        System.out.println(getQuery(map));
        */

    }
    public static String getQuery(Map<String, String> params) {
        if (params == null)
            return null;

        StringBuilder sb = new StringBuilder();

        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> map = it.next();
            String key = map.getKey();
            String value = map.getValue();
            if (key != null && value != null) {
                if (sb.length() != 0)
                    sb.append(" and ");
                sb.append(key).append(" = ").append("'").append(value).append("'");
            }
        }
        return sb.toString();


    }
}
