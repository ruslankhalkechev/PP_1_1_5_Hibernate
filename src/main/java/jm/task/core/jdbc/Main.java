package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 5);
        userService.saveUser("Petr", "Petrov", (byte) 10);
        userService.saveUser("Alexander", "Alexandrov", (byte) 15);
        userService.saveUser("Mihail", "Mihailov", (byte) 20);

        List<User> userList = userService.getAllUsers();
        userService.showAllUsers(userList);

        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.close();
    }
}
