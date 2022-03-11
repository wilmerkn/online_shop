package model;

import controller.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectionManager {
    private final Properties properties;
    private final String url;

    private Connection conn;
    private final Controller controller;


    public ConnectionManager(Controller controller) {
        this.properties = new Properties();
        properties.setProperty("user","ah0773");
        properties.setProperty("password","1db3dcbp");
        properties.setProperty("ssl","false");
        url = "jdbc:postgresql://pgserver.mau.se/ah0773";
        this.controller = controller;
    }

    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url, properties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}
