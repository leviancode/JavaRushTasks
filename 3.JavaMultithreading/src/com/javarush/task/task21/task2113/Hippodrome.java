package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    private Horse winner;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<>());
        Horse horse1 = new Horse("H1",3,0);
        Horse horse2 = new Horse("H2",3,0);
        Horse horse3 = new Horse("H3",3,0);
        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);

        game.run();
        game.printWinner();
    }

    public void run(){
        for (int i = 1; i <=100; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move(){
        for (Horse horse : horses)
            horse.move();

    }

    public void print(){
        for (Horse horse : horses)
            horse.print();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Horse getWinner(){
        Horse winner = null;
        double maxDistance = 0;
        for (Horse horse : horses) {
            if (horse.getDistance() > maxDistance)
                maxDistance = horse.getDistance();
        }

        for (Horse horse : horses){
            if (horse.getDistance() == maxDistance)
                winner = horse;
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
