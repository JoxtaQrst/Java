package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenreDAO {
    private Connection connection;
    public GenreDAO(){
        connection = DBManager.getInstance().getConnection();
    }

    public void insert(Genre genre) throws SQLException{
        String sql = "INSERT INTO genres (name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,genre.getName());
        statement.executeUpdate();
        statement.close();
    }
}
