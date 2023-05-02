package org.example;

public class Genre {
    private int genre_id;
    private String name;


    public Genre(){

    }

    public int getId() {
        return genre_id;
    }

    public void setId(int genre_id) {
        this.genre_id = genre_id;
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre_id=" + genre_id +
                ", name='" + name + '\'' +
                '}';
    }
}
