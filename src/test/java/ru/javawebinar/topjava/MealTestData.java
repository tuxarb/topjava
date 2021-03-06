package ru.javawebinar.topjava;


import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final Meal MEAL1 = new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(4, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(5, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(7, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(8, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL1 = new Meal(9, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);

    public static final List<Meal> MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();
}
