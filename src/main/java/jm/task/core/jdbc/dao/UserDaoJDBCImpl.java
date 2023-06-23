package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

//    Connection connection = new Util().getConnection();
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = util.getConnection()){
            connection
                    .prepareStatement("CREATE TABLE users (id serial not null , name varchar(10), lastName varchar(10), age int)")
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection()){
            connection
                    .prepareStatement("DROP TABLE if exists users")
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = util.getConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT into users (name, lastName, age) values (?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection()){
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = util.getConnection()){
            PreparedStatement preparedStatement =
                    connection
                            .prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                list.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection()){
            connection
                    .prepareStatement("TRUNCATE TABLE users")
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
