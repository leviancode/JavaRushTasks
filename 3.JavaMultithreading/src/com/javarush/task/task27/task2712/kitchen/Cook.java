package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> ordersQueue;
    private static LinkedBlockingQueue<Order> readyOrders = new LinkedBlockingQueue<>();

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order){
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order.toString());
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
            readyOrders.put(order);
        } catch (InterruptedException e) {
        }
        StatisticManager.getInstance()
                .register(new CookedOrderEventDataRow(order.getTablet().toString(),
                name,
                order.getTotalCookingTime()*60,
                order.getDishes()));
        busy = false;
    }

    public void setOrdersQueue(LinkedBlockingQueue<Order> ordersQueue) {
        this.ordersQueue = ordersQueue;
    }

    public static LinkedBlockingQueue<Order> getReadyOrders() {
        return readyOrders;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
                if(!ordersQueue.isEmpty())
                    startCookingOrder(ordersQueue.take());
            } catch (InterruptedException e) {
            }
        }
    }
}
