package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    private final Connection connection;
    public ArtistDAO() throws SQLException {connection = DBManager.getInstance().getConnection();}
    //SELECT
    public List<Artist> getAllArtists(){
        List<Artist> artists = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM artists");
            while (resultSet.next()){
                Artist artist = new Artist();
                artist.setId(resultSet.getInt("id"));
                artist.setName(resultSet.getString("name"));
                artists.add(artist);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return artists;
    }

    //INSERT

    public void insertArtist (Artist artist) throws SQLException{
        String name = artist.getName();
        Integer existingArtist = findByName(name);
        //already exists
        if(existingArtist !=null){
            artist.setId(existingArtist);
            return;
        }
        //doesnt exist
        String sql = "INSERT INTO artists (name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, artist.getName());
        statement.executeUpdate();
        ResultSet artistKey = statement.getGeneratedKeys();
        if(artistKey.next()){
            int artistId = artistKey.getInt(1);
            artist.setId(artistId);
        } else {
            throw new SQLException("Inserting artist failed, no ID obtained");
        }
        statement.close();
    }

    public Integer findByName(String name) throws SQLException{
        Connection con = DBManager.getInstance().getConnection();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM artists WHERE name='" + name + "'");
            return resultSet.next() ? resultSet.getInt(1):null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteArtist(int artist_id){
        try {
            // Punem toate albumele care au acelasi artist intr-o lista ( doar ID-ul ne trebuie )
            List<Integer> albumIds = new ArrayList<Integer>();
            PreparedStatement albumsStatement = connection.prepareStatement("SELECT id FROM albums WHERE artist_id=?");
            albumsStatement.setInt(1,artist_id);
            ResultSet resultSet = albumsStatement.executeQuery();
            // luam id-urile din query si le punem in lista
            while(resultSet.next()){
                albumIds.add(resultSet.getInt("id"));
            }
            // stergem fiecare album si genurile pe care le are
            for(int albumId:albumIds){
                // stergem genurile pe care le are albumul
                PreparedStatement genreStatement = connection.prepareStatement("DELETE FROM album_genres WHERE album_id = ?");
                genreStatement.setInt(1,albumId);
                genreStatement.executeUpdate();

                //stergem albumul
                PreparedStatement albumStatement = connection.prepareStatement("DELETE FROM albums WHERE album_id = ?");
                albumStatement.setInt(1,albumId);
                albumStatement.executeUpdate();
            }

            //intr-un final, stergem artistul
            PreparedStatement artistStatement = connection.prepareStatement("DELETE FROM artists WHERE id = ?");
            artistStatement.setInt(1,artist_id);
            artistStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Artist getArtist(int artistId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM artists WHERE id = ?");
        statement.setInt(1, artistId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            Artist artist = new Artist(name);
            resultSet.close();
            statement.close();
            return artist;
        } else {
            throw new SQLException("Could not find artist with id: " + artistId);
        }
    }
}
