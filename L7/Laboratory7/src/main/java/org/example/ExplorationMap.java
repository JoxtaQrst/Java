package org.example;

import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;

    public ExplorationMap(Cell[][] matrix){
        this.matrix = matrix;
    }
    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if(!matrix[row][col].isVisited()){
                List<Token> tokens = robot.extractTokens(row,col);
                matrix[row][col].setVisited(true);
                System.out.printf("%s visited cell (%d,%d) %n", robot.getName(),row,col);
                return true;
            }
            else{
                return false;
            }
        }

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

    public int getSize() {
        return matrix.length;
    }
}

