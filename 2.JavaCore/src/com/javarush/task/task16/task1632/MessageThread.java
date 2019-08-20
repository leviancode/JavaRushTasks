package com.javarush.task.task16.task1632;

public class MessageThread extends Thread implements Message {

    boolean repeat = true;

    @Override
    public void showWarning() {
        repeat = false;
    }

    @Override
    public void run() {
        while (repeat);
    }
}
