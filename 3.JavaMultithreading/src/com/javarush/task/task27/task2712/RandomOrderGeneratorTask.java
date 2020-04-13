package com.javarush.task.task27.task2712;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tabletList;
    private final int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tabletList, int interval){
        this.tabletList = tabletList;
        ORDER_CREATING_INTERVAL = interval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                tabletList.get(ThreadLocalRandom.current().nextInt(5)).createTestOrder();
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
