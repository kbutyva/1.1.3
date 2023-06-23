package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        Connection connection = new Util().getConnection();

        Util util = new Util();

        try (Connection connection = util.getConnection()){
            connection
                    .prepareStatement("DROP TABLE if exists users")
                    .executeUpdate();
            connection
                    .prepareStatement("CREATE TABLE users (id serial primary key, name varchar(10), lastName varchar(10), age int)")
                    .executeUpdate();

            connection
                    .prepareStatement("INSERT into users (name, lastName, age) values ('bu', 'ku', 10)")
                    .executeUpdate();
            System.out.println("inserted user bu, ku, 10");
            connection
                    .prepareStatement("INSERT into users (name, lastName, age) values ('bu', 'ku', 9)")
                    .executeUpdate();
            System.out.println("inserted user bu, ku, 9");
            connection
                    .prepareStatement("INSERT into users (name, lastName, age) values ('bu', 'ku', 8)")
                    .executeUpdate();
            System.out.println("inserted user bu, ku, 8");
            connection
                    .prepareStatement("INSERT into users (name, lastName, age) values ('bu', 'ku', 7)")
                    .executeUpdate();
            System.out.println("inserted user bu, ku, 7");

            PreparedStatement preparedStatementSelectAll = connection.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatementSelectAll.executeQuery();

            while (resultSet.next()){
                List l = new ArrayList();
                for (int i = 2; i <= 4; i++) {
                    l.add(resultSet.getObject(i));
                }
                System.out.println(l);
            }

            connection
                    .prepareStatement("truncate table users")
                    .executeUpdate();
            System.out.println("truncate table");

            connection
                    .prepareStatement("DROP TABLE if exists users")
                    .executeUpdate();
            System.out.println("drop table");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
