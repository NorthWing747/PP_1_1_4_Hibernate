package jm.task.core.jdbc.util;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Util {

    // реализуйте настройку соеденения с БД
    private static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static String USER = "root";
    private static String PASSWORD = "root";

    // Метод для получения соединения
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Подгрузка драйвера
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Соединение установлено!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ошибка подключения к БД!");
            e.printStackTrace();
        }
        return connection;
    }
}