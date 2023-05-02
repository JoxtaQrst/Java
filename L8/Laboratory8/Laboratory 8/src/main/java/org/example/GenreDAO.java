package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private final Connection connection;
    public GenreDAO() throws SQLException {
        connection = DBManager.getInstance().getConnection();
    }

    //SELECT
    public List<Genre> getAllGenres(){
        List<Genre> genresList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM genres");
            while (resultSet.next()){
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("name"));
                genresList.add(genre);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return genresList;
    }

    //INSERT
    public void insertGenre(Genre genre) throws SQLException{
        String name = genre.getName();
        Integer existingGenre = findByName(name);
        //already exists
        if(existingGenre !=null){
            genre.setId(existingGenre);
            return;
        }
        //doesnt exist
        String sql = "INSERT INTO genres (name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,genre.getName());
        statement.executeUpdate();
        ResultSet genreKey = statement.getGeneratedKeys();
        if(genreKey.next()){
            int genreId = genreKey.getInt(1);
            genre.setId(genreId);
        } else {
            throw new SQLException("Inserting artist failed, no ID obtained");
        }
        statement.close();
    }

    public Integer findByName(String name) throws SQLException{
        Connection con = DBManager.getInstance().getConnection();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM genres WHERE name='" + name + "'");
            return resultSet.next() ? resultSet.getInt(1):null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE
    public void deleteGenre(int genreId){
        try{
            // stergem in albums_genres
            PreparedStatement albumsGenresStatement = connection.prepareStatement("DELETE FROM album_genres WHERE genre_id=?");
            albumsGenresStatement.setInt(1,genreId);
            albumsGenresStatement.executeUpdate();

            //stergem in genres
            PreparedStatement genreStatement = connection.prepareStatement("DELETE FROM genres WHERE id = ?");
            genreStatement.setInt(1,genreId);
            genreStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
