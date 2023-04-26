package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //displayArtists();
        insertGenre();
        //deleteAlbum();
    }

    public static void insertGenre(){
        //insert method
        GenreDAO genreDAO = new GenreDAO();
        Genre genre = new Genre("KPOP");
        try{
            genreDAO.insert(genre);
            System.out.println("Genre inserted succesfully!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void displayArtists(){
        //Get all artists from the database
        ArtistDAO artistDAO = new ArtistDAO();
        List<Artist> artists = artistDAO.getAllArtists();
        System.out.println("All artists in the database: ");
        for(Artist artist : artists){
            System.out.println("ID: " + artist.getId() + " Name: " + artist.getName());
        }
    }

    public static void deleteAlbum(){
        //Delete an album from the database
        AlbumDAO albumDAO = new AlbumDAO();
        albumDAO.deleteAlbum(2);
        System.out.println("Album deleted successfully!");
    }
}