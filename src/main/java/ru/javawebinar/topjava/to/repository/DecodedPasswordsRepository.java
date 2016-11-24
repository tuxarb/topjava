package ru.javawebinar.topjava.to.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DecodedPasswordsRepository {
    private static Map<String, String> repository = new ConcurrentHashMap<String, String>() {{
        put("user@yandex.ru", "password");
        put("admin@gmail.com", "admin");
    }};

    private DecodedPasswordsRepository() {
    }

    public static void putCredentials(String email, String password) {
        repository.put(email, password);
    }

    public static String getDecodedPassword(String email) {
        return repository.get(email);
    }

}