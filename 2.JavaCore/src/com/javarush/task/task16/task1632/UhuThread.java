package com.javarush.task.task16.task1632;

public class UhuThread extends Thread {
    @Override
    public void run() {

            try {
                while(true) {
                    System.out.println("Ура");
                    Thread.sleep(500);

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


    }
}
