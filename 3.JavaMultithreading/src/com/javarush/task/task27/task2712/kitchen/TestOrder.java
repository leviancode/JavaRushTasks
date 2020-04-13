package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) {
        super(tablet);
        initDishes();
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        int dishesAmount = ThreadLocalRandom.current().nextInt(5);
        for (int i = 0; i < dishesAmount; i++){
            dishes.add(Dish.values()[ThreadLocalRandom.current().nextInt(5)]);
        }
    }
}
