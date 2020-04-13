package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Cook cook = new Cook("Cook-1");
        Cook cook2 = new Cook("Cook-2");
        cook.setOrdersQueue(orderQueue);
        cook2.setOrdersQueue(orderQueue);


        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        Thread waiterThread = new Thread(new Waiter());
        Thread cookThread = new Thread(cook);
        Thread cookThread2 = new Thread(cook2);
        Thread orderThread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        waiterThread.start();
        cookThread.start();
        cookThread2.start();
        orderThread.start();

        try {
            Thread.sleep(1000);
            waiterThread.interrupt();
            cookThread.interrupt();
            cookThread2.interrupt();
            orderThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}

