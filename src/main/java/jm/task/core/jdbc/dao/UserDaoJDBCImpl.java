package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
                Statement stat = conn.createStatement()) {
            String sqlCommand = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(30), lastname VARCHAR (30), age TINYINT UNSIGNED)";
            stat.executeUpdate(sqlCommand);
        } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
                Statement stat = conn.createStatement()) {
            String sqlCommand = "DROP TABLE IF EXISTS user";
            stat.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO user(name, lastname, age) VALUES (?, ?, ?)";

        try (Connection conn = Util.getConnection();
                PreparedStatement preStat = conn.prepareStatement(sqlCommand)) {
            preStat.setString(1, name);
            preStat.setString(2, lastName);
            preStat.setByte(3, age);
            preStat.executeUpdate();
            System.out.println("User с именем – " + name +" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM user WHERE id= ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement preStat = conn.prepareStatement(sqlCommand)) {
            preStat.setLong(1, id);
            preStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection conn = Util.getConnection();
                Statement stat = conn.createStatement()) {
            String sqlCommand = "SELECT * FROM user";
            ResultSet rs = stat.executeQuery(sqlCommand);
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()) {
            String sqlCommand = "DELETE FROM user";
            stat.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
