package jm.task.core.jdbc;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        // реализуйте алгоритм здесь
        try (Connection conn = Util.getConnection()) {
            if (conn != null) {
                System.out.println("БД доступна, можно работать");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Антон", "Иванов", (byte) 25);
        userService.saveUser("Мария", "Петрова", (byte) 30);
        userService.saveUser("Степан", "Петров", (byte) 33);
        userService.saveUser("Екатерина", "Иванова", (byte) 30);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
