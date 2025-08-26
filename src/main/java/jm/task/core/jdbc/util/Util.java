package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static SessionFactory sessionFactory;

    // JDBC connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД (JDBC)", e);
        }
    }

    // Hibernate SessionFactory
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties props = new Properties();
                props.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                props.setProperty("hibernate.connection.url", URL);
                props.setProperty("hibernate.connection.username", USER);
                props.setProperty("hibernate.connection.password", PASSWORD);
                props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                props.setProperty("hibernate.show_sql", "true");

                sessionFactory = new Configuration()
                        .addProperties(props)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException("Ошибка создания SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}