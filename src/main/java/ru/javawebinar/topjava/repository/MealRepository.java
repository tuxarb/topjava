package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MealRepository {
    // null if meal do not belong to userId
    Meal get(int id, int userId);

    // null if meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    Collection<Meal> getAll(int userId);

    // ORDERED date
    Collection<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Meal getWithUser(int id, int userId)
    {
        throw new UnsupportedOperationException();
    }
}
