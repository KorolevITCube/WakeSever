package com.korolev.wake.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Component
public class DataBaseConnector {

    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    public Connection getConnection(){
        try {
            Class.forName(driverName);
            var connect = DriverManager
                    .getConnection(databaseUrl,
                            user, password);
            connect.setAutoCommit(false);
            log.info("Connection open " + connect.toString());
            return connect;
        }catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public void closeConnection(Connection connect) throws SQLException {
        try {
            connect.commit();
            connect.close();
            log.info("Connection closed");
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
