package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface MealService {
    Meal save(Meal meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    Collection<Meal> getAll(int userId);

    void update(Meal meal, int userId) throws NotFoundException;

    Collection<Meal> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Collection<Meal> getBetween(LocalDate startDate, LocalDate endTime, int userId) {
        return getBetweenDates(LocalDateTime.of(startDate, LocalTime.MIN),
                LocalDateTime.of(endTime, LocalTime.MAX),
                userId);
    }
}
