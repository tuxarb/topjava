package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.to.repository.DecodedPasswordsRepository;

public class UsersUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createNewUserFromForm(UserTo userTo) {
        putUserCredentials(userTo);
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getCaloriesPerDay(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        String decodedPassword = getDecodedPassword(user.getEmail());
        return new UserTo(user.getId(), user.getName(), user.getEmail(), decodedPassword, user.getCaloriesPerDay());
    }

    public static User getUserFromUserTo(User user, UserTo userTo) {
        putUserCredentials(userTo);
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        user.setCaloriesPerDay(userTo.getCaloriesPerDay());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    private static void putUserCredentials(UserTo userTo) {
        DecodedPasswordsRepository.putCredentials(userTo.getEmail(), userTo.getPassword());
    }

    private static String getDecodedPassword(String email) {
        return DecodedPasswordsRepository.getDecodedPassword(email);
    }

}
