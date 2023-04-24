package org.example;

import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;
    private final SharedMemory sharedMemory;
    private int n;

    public ExplorationMap(int n){
        this.n=n;
        matrix = new Cell[n][n];
        sharedMemory = new SharedMemory(n);
        System.out.println(sharedMemory.toString());
        for (int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j] = new Cell();
            }
        }
    }
    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if(!matrix[row][col].isVisited()){
                List<Token> tokens = sharedMemory.extractTokens(n);
                for(Token token : tokens){
                    matrix[row][col].setToken(token);
                    robot.setTokensPlaced(token.getNumber());
                }
                matrix[row][col].setVisited(true);
                System.out.printf("%s visited cell (%d,%d) %n and placed %d tokens %n", robot.getName(),row,col,robot.getTokensPlaced());
                System.out.println(this.toString());
                return true;
            }
            else{
                return false;
            }
        }

    }
    public boolean allCellsVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!getCell(i,j).isVisited()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] cells : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                if (cells[j].isVisited()) {
                    sb.append("[X]");
                } else sb.append("[ ]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Cell getCell(int row, int col) {
        return matrix[row][col];
    }

    public int getSize() {
        return matrix.length;
    }
}

