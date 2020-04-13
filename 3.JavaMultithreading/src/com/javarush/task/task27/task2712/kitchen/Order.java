package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Stream;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet){
        this.tablet = tablet;
        initDishes();
    }

    public int getTotalCookingTime(){
        return dishes.stream().mapToInt(Dish::getDuration).sum();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes(){
        try {
            dishes = ConsoleHelper.getAllDishesForOrder();
        } catch (IOException e) {
            Tablet.getLogger().log(Level.SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dishes != null) {
            sb.append("Your order: ");
            Stream.of(dishes)
                    .map(String::valueOf)
                    .forEach(sb::append);
            sb.append(" of ")
                    .append(tablet.toString())
                    .append(", cooking time ")
                    .append(getTotalCookingTime())
                    .append("min");
        }
        return sb.toString();
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }
}
