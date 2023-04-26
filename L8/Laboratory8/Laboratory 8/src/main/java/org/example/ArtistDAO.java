package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    public List<Artist> getAllArtists(){
        List<Artist> artists = new ArrayList<>();
        try{
            Connection connection = DBManager.getInstance().getConnection();
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
}
