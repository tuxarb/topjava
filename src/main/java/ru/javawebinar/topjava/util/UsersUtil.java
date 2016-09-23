package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public final static List<User> USERS_LIST = Arrays.asList(
            new User(1, "Anya", "", "", Role.ROLE_USER),
            new User(2, "Bogdan", "", "", Role.ROLE_USER),
            new User(3, "Vasya", "", "", Role.ROLE_USER),
            new User(4, "Alex", "", "", Role.ROLE_USER)
    );
}
