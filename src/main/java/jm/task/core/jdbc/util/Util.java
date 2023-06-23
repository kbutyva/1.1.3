package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    String url = "jdbc:postgresql://localhost:5432/pre_project";
    String username = "postgres";
    String password = "admin";

    Connection connection = null;

    public Connection getConnection() {

        Driver driver = new org.postgresql.Driver();
        try {
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }


}
