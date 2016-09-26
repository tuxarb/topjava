package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getILoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = getLogger(InMemoryMealRepositoryImpl.class);
    private static final Comparator<Meal> MEAL_ORDER = Comparator.comparing(Meal::getDateTime).reversed();
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEAL_LIST.forEach(meal -> save(meal, USER_ID));
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500), ADMIN_ID);
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("+++ PreDestroy");
    }

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
