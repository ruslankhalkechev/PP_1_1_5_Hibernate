package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory factory;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            Properties props = new Properties();
            props.setProperty("hibernate.connection.url", URL);
            props.setProperty("hibernate.connection.username", USERNAME);
            props.setProperty("hibernate.connection.password", PASSWORD);
            props.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            props.setProperty("current_session_context_class", "thread");
            props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            props.setProperty("hibernate.show_sql", "true");
            try {
                factory = new Configuration()
                        .addProperties(props)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Throwable exc) {
                System.out.println("Connection error");
                exc.printStackTrace();
            }
        }
        return factory;

    }
}


