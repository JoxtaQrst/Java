package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlbumDAO {
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
}
