package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws SQLException {
        importerTool();
        //testInsert();

    }

    public static void importerTool() throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        ArtistDAO artistDAO = new ArtistDAO();
        AlbumDAO albumDAO = new AlbumDAO();
        GenreDAO genreDAO = new GenreDAO();
        String csvFilePath = "C:\\Users\\pinte\\Documents\\Java\\L8\\Laboratory8\\Laboratory 8\\src\\main\\resources\\albumlist.csv";
        CsvImporter csvImporterTool = new CsvImporter(csvFilePath,artistDAO,albumDAO,genreDAO);
        try{
            connection.setAutoCommit(false);
            csvImporterTool.parseCSV();
            connection.commit();
                 // display data
            System.out.println("Artists:");
            List<Artist> artists = artistDAO.getAllArtists();
            for(Artist artist : artists) {
                System.out.println(artist);
            }

            System.out.println("\nAlbums:");
            List<Album> albums = albumDAO.getAllAlbums();
            for(Album album : albums) {
                System.out.println(album);
            }

            System.out.println("\nGenres:");
            List<Genre> genres = genreDAO.getAllGenres();
            for(Genre genre : genres) {
                System.out.println(genre);
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public static void testInsert() throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        ArtistDAO artistDAO = new ArtistDAO();
        AlbumDAO albumDAO = new AlbumDAO();
        GenreDAO genreDAO = new GenreDAO();

        //for inserting
        List<Artist> artistList = new ArrayList<>();
        List<Album> albumList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();

        //K/DA
        Artist kda = new Artist("K/DA");
        artistList.add(kda);
        Album kdaAlbum = new Album("POP/STARS","2018",kda);
        albumList.add(kdaAlbum);

        Genre kdaGenre = new Genre("K-Pop");
        genreList.add(kdaGenre);

        //TWICE
        Artist twice = new Artist("TWICE");
        artistList.add(twice);
        Album twiceAlbum = new Album("Fancy You","2019",twice);
        albumList.add(twiceAlbum);

        Genre twiceGenre = new Genre("K-Pop");
        genreList.add(twiceGenre);

        //PENTAKILL
        Artist pentakill = new Artist("Pentakill");
        artistList.add(pentakill);
        Album pentakillAlbum = new Album("Smite and Ignite","2014",pentakill);
        albumList.add(pentakillAlbum);

        Genre pentakillGenre = new Genre("Metal");
        genreList.add(pentakillGenre);

        //BBNO$
        Artist bbno = new Artist("Bbno$");
        artistList.add(bbno);
        Album bbnoAlbum = new Album("Eat Ya Veggies","2021",bbno);
        albumList.add(bbnoAlbum);

        Genre bbnoGenre = new Genre("Hip-Hop");
        genreList.add(bbnoGenre);

        //Yung Gravy
        Artist yungGravy = new Artist("Yung Gravy");
        artistList.add(yungGravy);
        Album yungGravyAlbum = new Album("GASANOVA","2020",yungGravy);
        albumList.add(yungGravyAlbum);

        Genre yungGravyGenre = new Genre("Hip-Hop");
        genreList.add(yungGravyGenre);

        //insert data
        try{
            connection.setAutoCommit(false);

            for(int i=0; i<artistList.size();i++){
                Artist artist = artistList.get(i);
                artistDAO.insertArtist(artist);
                int artistId = artist.getId();

                Album album = albumList.get(i);
                album.setArtist_id(artistId);
                albumDAO.insertAlbum(album);
                int albumId = album.getId();

                Genre genre = genreList.get(i);
                genreDAO.insertGenre(genre);
                int genreId = genre.getId();

                //albums_genres table
                albumDAO.addAlbumToGenre(albumId,genreId);
            }
            connection.commit();
            // display data
            System.out.println("Artists:");
            List<Artist> artists = artistDAO.getAllArtists();
            for(Artist artist : artists) {
                System.out.println(artist);
            }

            System.out.println("\nAlbums:");
            List<Album> albums = albumDAO.getAllAlbums();
            for(Album album : albums) {
                System.out.println(album);
            }

            System.out.println("\nGenres:");
            List<Genre> genres = genreDAO.getAllGenres();
            for(Genre genre : genres) {
                System.out.println(genre);
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}