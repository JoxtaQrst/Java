package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static DBManager instance;
    private static Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/music_library";
    private final String user = "root";
    private final String password = "VLADimir2002";

    private DBManager(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
