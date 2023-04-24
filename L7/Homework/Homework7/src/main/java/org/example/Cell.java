package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Token token;
    private boolean visited;

    public Cell() {
        visited = false;
    }

    public void setToken(Token token) {
        this.token = token;
    }



    public Token getToken() {
        return token;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}