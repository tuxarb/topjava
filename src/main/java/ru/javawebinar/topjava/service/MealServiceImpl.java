package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class MealServiceImpl implements MealService{
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        Assert.notNull(meal, "meal must not be null");
        ExceptionUtil.checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    @Override
    @Transactional
    public void update(MealTo editedMeal, int userId) {
        Meal meal = get(editedMeal.getId(), userId);
        repository.save(MealsUtil.updateMealFromForm(meal, editedMeal), userId);
    }

    public Collection<Meal> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int userId)
    {
        Assert.notNull(startDate, "start_date must not be null");
        Assert.notNull(endDate, "end_date must not be null");
        return repository.getBetween(startDate, endDate, userId);
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
