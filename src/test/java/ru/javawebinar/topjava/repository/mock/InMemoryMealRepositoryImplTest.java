package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImplTest.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImplTest.USER_ID;

@Repository
public class InMemoryMealRepositoryImplTest implements MealRepository {
    private static final Comparator<Meal> MEAL_ORDER = Comparator.comparing(Meal::getDateTime).reversed();
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> mealRepository = repository.get(userId);
        return mealRepository == null ? null : mealRepository.get(id);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);
        if (meal.isNew())
            meal.setId(counter.incrementAndGet());
        else if (get(meal.getId(), userId) == null) {
            return null;
        }
        // if not exists, just create new, otherwise add to the existing
        Map<Integer, Meal> mealRepository = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        return mealRepository.put(meal.getId(), meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> mealRepository = repository.get(userId);
        return mealRepository != null && mealRepository.remove(id) != null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> mealRepository = repository.get(userId);
        return mealRepository == null ? Collections.emptyList() : mealRepository.values().stream()
                .sorted(MEAL_ORDER)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return getAll(userId).stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                .collect(Collectors.toList());
    }
}
