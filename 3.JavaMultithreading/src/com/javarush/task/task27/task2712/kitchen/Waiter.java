package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

public class Waiter implements Runnable{
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Order order = Cook.getReadyOrders().take();
                ConsoleHelper.writeMessage(String.format("The order for %s was taken by the waiter",
                        order.getTablet().toString()));
            } catch (InterruptedException e) {
            }
        }
    }
}
