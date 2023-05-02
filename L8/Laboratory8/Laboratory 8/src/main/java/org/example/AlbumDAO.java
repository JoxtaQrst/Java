package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    private final Connection connection;

    public AlbumDAO() throws SQLException {connection = DBManager.getInstance().getConnection();}

    //SELECT
    public List<Album> getAllAlbums(){
        List<Album> albumsList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM albums");
            while(resultSet.next()){
                ArtistDAO artistById= new ArtistDAO();
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String releaseYear = resultSet.getString("release_year");
                int artistId = resultSet.getInt("artist_id");
                Artist artist = artistById.getArtist(artistId);
                Album album = new Album(title,releaseYear,artist);
                album.setId(id);
                album.setArtist_id(artistId);
                albumsList.add(album);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return albumsList;
    }

    public void insertAlbum(Album album) throws SQLException{
        String sql = "INSERT INTO albums (release_year, title, artist_id) VALUES ( ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, album.getRelease_year());
        statement.setString(2, album.getTitle());
        statement.setInt(3, album.getArtist_id());
        statement.executeUpdate();
        ResultSet albumKey = statement.getGeneratedKeys();
        if(albumKey.next()){
            int albumId = albumKey.getInt(1);
            album.setId(albumId);
        } else {
            throw new SQLException("Inserting artist failed, no ID obtained");
        }
        statement.close();

    }
    public void deleteAlbum(int id){
        try{
            Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM album_genres WHERE album_id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM albums WHERE id = ? ");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //update albums_genre table
    public void addAlbumToGenre(int albumId, int genreId) {
        try {
            PreparedStatement albumStatement = connection.prepareStatement("SELECT id FROM albums WHERE id = ?");
            albumStatement.setInt(1, albumId);
            ResultSet albumResult = albumStatement.executeQuery();
            if (!albumResult.next()) {
                throw new IllegalArgumentException("Album with id " + albumId + " does not exist");
            }

            PreparedStatement genreStatement = connection.prepareStatement("SELECT id FROM genres WHERE id = ?");
            genreStatement.setInt(1, genreId);
            ResultSet genreResult = genreStatement.executeQuery();
            if (!genreResult.next()) {
                throw new IllegalArgumentException("Genre with id " + genreId + " does not exist");
            }

            PreparedStatement statement = connection.prepareStatement("INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)");
            statement.setInt(1, albumId);
            statement.setInt(2, genreId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
