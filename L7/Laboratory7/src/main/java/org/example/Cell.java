package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    //private final int tokenCapacity;
    private boolean visited;

    public Cell() {
        //this.tokenCapacity
        visited = false;
    }

//    public int getTokenCapacity() {
//        return tokenCapacity;
//    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}