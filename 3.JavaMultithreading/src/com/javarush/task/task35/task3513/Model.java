package com.javarush.task.task35.task3513;


import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile [][] gameTiles;
    protected int score;
    protected int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model (){
        resetGameTiles();
    }

    public boolean hasBoardChanged(){
        int sumTilesValue = Arrays.stream(gameTiles)
                .mapToInt(t -> Arrays.stream(t).mapToInt(v -> v.value).sum()).sum();
        int sumTilesPreValue = Arrays.stream(previousStates.peek())
                .mapToInt(t -> Arrays.stream(t).mapToInt(v -> v.value).sum()).sum();

        return sumTilesValue != sumTilesPreValue;
    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
            queue.add(getMoveEfficiency(this::left));
            queue.add(getMoveEfficiency(this::right));
            queue.add(getMoveEfficiency(this::up));
            queue.add(getMoveEfficiency(this::down));
            queue.peek().getMove().move();
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged())
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else
            moveEfficiency = new MoveEfficiency(-1, 0, move);

        rollback();
        return moveEfficiency;
    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0: left(); break;
            case 1: right(); break;
            case 2: up(); break;
            case 3: down();
        }
    }

    private void saveState(Tile[][] tiles){
        Tile[][] newMatrix = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newMatrix[i][j] = new Tile(tiles[i][j].value);
            }
        }

        previousStates.push(newMatrix);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()){
            emptyTiles.get((int) (emptyTiles.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> emptyTiles = new ArrayList<>();

        for (Tile[] gameTile : gameTiles) {
            for (Tile tile : gameTile) {
                if (tile.isEmpty())
                    emptyTiles.add(tile);
            }
        }
        return emptyTiles;
    }

    protected void resetGameTiles(){
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        previousStates.clear();
        previousScores.clear();
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        Tile[] beforeSort = tiles.clone();
        Arrays.sort(tiles, Comparator.comparing(t -> t.value == 0));
        return !Arrays.equals(beforeSort, tiles);
    }

    private boolean mergeTiles(Tile[] tiles){
        boolean flag = false;
        for (int i = 0; i < tiles.length-1; i++){
            if (tiles[i].value != 0 && tiles[i].value == tiles[i+1].value){
                tiles[i].value *= 2;
                tiles[i+1].value = 0;
                score += tiles[i].value;
                flag = true;
                if (tiles[i].value > maxTile)
                    maxTile = tiles[i].value;
            }
        }
        if (flag) compressTiles(tiles);
        return flag;
    }

    public void left(){
        if (isSaveNeeded) saveState(gameTiles);

        int isNeedAddTile = 0;
        for (Tile[] row : gameTiles){
            if(compressTiles(row) | mergeTiles(row))
                isNeedAddTile++;
        }
        isSaveNeeded = true;
        if (isNeedAddTile > 0) addTile();
    }

    public void right(){
        saveState(gameTiles);
        rotateMatrix90Clockwise();
        rotateMatrix90Clockwise();
        left();
        rotateMatrix90Clockwise();
        rotateMatrix90Clockwise();
    }
    public void up(){
        saveState(gameTiles);
        rotateMatrix90Clockwise();
        rotateMatrix90Clockwise();
        rotateMatrix90Clockwise();
        left();
        rotateMatrix90Clockwise();
    }
    public void down(){
        saveState(gameTiles);
        rotateMatrix90Clockwise();
        left();
        rotateMatrix90Clockwise();
        rotateMatrix90Clockwise();
        rotateMatrix90Clockwise();
    }

    private void rotateMatrix90Clockwise(){
        Tile[][] rotatedMatrix = new Tile[gameTiles.length][gameTiles.length];

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                rotatedMatrix[i][j] = gameTiles[gameTiles.length - j - 1][i];
            }
        }
        gameTiles = rotatedMatrix;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j+1].value
                        || gameTiles[i][j].value * gameTiles[i][j+1].value == 0
                        || (i != gameTiles.length-1 && gameTiles[i][j].value == gameTiles[i+1][j].value))
                    return true;
            }
        }
        return false;
    }
}
