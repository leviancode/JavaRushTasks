package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
       // setDaemon(true);
    }

    @Override
    public void run() {
        State state = target.getState();
        System.out.println(target.getState());

        while (true) {
            if (state != target.getState()){
                System.out.println(target.getState());
                state = target.getState();
            }
            if (state == State.TERMINATED) break;
        }
    }
}
