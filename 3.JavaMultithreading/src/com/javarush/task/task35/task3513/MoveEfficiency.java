package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency anotherMove) {
        int emptyTilesCompare = Integer.compare(numberOfEmptyTiles, anotherMove.numberOfEmptyTiles);
        int scoreCompare = Integer.compare(score, anotherMove.score);

        if (emptyTilesCompare != 0)
            return emptyTilesCompare;
        else return scoreCompare;
    }
}
