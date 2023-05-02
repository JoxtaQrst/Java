package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/music_library";
    private static final String USER = "root";
    private static final String PASSWORD = "VLADimir2002";
    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE = 5;
    private static final long CONNECTION_TIMEOUT = 30000;
    private static final long IDLE_TIMEOUT = 600000;

    private static DBManager instance;
    private static DataSource dataSource;

    private DBManager() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.setMinimumIdle(MIN_IDLE);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.setIdleTimeout(IDLE_TIMEOUT);
        dataSource = new HikariDataSource(config);
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
