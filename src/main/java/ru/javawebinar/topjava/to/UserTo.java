package ru.javawebinar.topjava.to;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import ru.javawebinar.topjava.util.UsersUtil;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @Length(min = 3, max = 30, message = "{error.userTo.name.length}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я][a-zа-я0-9-_]{2,}$", message = "{error.userTo.name}")
    private String name;

    @Length(max = 50, message = "{error.userTo.email.length}")
    @Email(regexp = "([A-z0-9_.-]{1,})@([A-z0-9_.-]{1,})[.]([A-z]{2,8})", message = "{error.userTo.email}")
    private String email;

    @Size(min = 5, max = 20, message = " {error.userTo.password.size}")
    private String password;


    @NotNull(message = "{error.userTo.calories.notnull}")
    @Range(min = 100, max = 9999, message = "{error.userTo.calories.range}")
    private Integer caloriesPerDay = UsersUtil.DEFAULT_CALORIES_PER_DAY;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, Integer caloriesPerDay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNew() {
        return id == null;
    }

    public Integer getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(Integer caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", caloriesPerDay='" + caloriesPerDay + '\'' +
                '}';
    }
}

