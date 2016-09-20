package ru.javawebinar.topjava;


import ru.javawebinar.topjava.model.Role;
import java.util.Set;

public class LoggedUser {
    protected Integer id;
    protected Set<Role> roles;
    protected boolean enabled;

    public Integer getId() {
        return id;
    }
}
