package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger LOG = getLogger(MealRestController.class);
    @Autowired
    private MealService mealService;

    public List<MealWithExceed> getBetween(LocalDate of, LocalTime of1, LocalDate of2, LocalTime of3) {
        return null;
    }

    public void delete(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete meal with id={} for user={}", id, userId);
        mealService.delete(id, userId);
    }

    public Meal get(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get meal with id={} for user={}", id, userId);
        return mealService.get(id, userId);
    }

    public List<MealWithExceed> getAll()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all meal for user={}", userId);
        return MealsUtil.getListWithExceed(mealService.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal create(Meal meal)
    {
        meal.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create meal={} for user={}", meal, userId);
        return mealService.save(meal, userId);
    }

    public void update(Meal meal, int id)
    {
        meal.setId(id);
        int userId = AuthorizedUser.id();
        LOG.info("Update meal={} for user={}", meal, userId);
        mealService.save(meal, userId);
    }
}
