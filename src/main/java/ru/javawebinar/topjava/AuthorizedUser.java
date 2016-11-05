package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.UsersUtil;

public class AuthorizedUser {
    public static int id = 1;

    private AuthorizedUser()
    {}

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return UsersUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
