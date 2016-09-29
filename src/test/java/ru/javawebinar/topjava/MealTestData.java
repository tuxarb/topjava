package ru.javawebinar.topjava;


import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public class MealTestData {
    private final static Meal meal1 = new Meal(1, LocalDateTime.now(), "milk", 100);
    private final static Meal meal2 = new Meal(1, LocalDateTime.now(), "meal", 300);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();
}
