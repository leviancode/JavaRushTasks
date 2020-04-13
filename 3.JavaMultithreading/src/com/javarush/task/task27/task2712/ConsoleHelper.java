package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();

        writeMessage(Dish.allDishesToString());
        writeMessage("Выберете блюдо: ");

        String order;
        while (!(order = readString()).equalsIgnoreCase("exit")){
            String orderCap = order.substring(0,1).toUpperCase() + order.substring(1);
            try {
                Dish dish = Dish.valueOf(orderCap);
                dishes.add(dish);
            }catch (Exception e){
                writeMessage("Такого блюда нет в меню. Выберете другое: ");
            }
        }
        return dishes;
    }
}
