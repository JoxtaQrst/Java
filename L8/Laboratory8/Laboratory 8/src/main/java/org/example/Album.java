package org.example;

public class Album {
    private int id;
    private String title;
    private String release_year;
    private Artist artist;

    public Album(){

    }

    public Album(String title, String release_year,Artist artist) {
        this.title = title;
        this.release_year = release_year;
        this.artist=artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public int getArtist_id() {
        return artist.getId();
    }

    public void setArtist_id(int artist_id) {
        this.artist.setId(artist_id);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_year='" + release_year + '\'' +
                ", artist=" + artist +
                '}';
    }
}
