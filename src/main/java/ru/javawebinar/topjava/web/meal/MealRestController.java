package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger LOG = getLogger(MealRestController.class);
    @Autowired
    private MealService mealService;

    public void delete(int id)
    {
        int userId = AuthorizedUser.getId();
        LOG.info("Delete meal with id={} for user={}", id, userId);
        mealService.delete(id, userId);
    }

    public Meal get(int id)
    {
        int userId = AuthorizedUser.getId();
        LOG.info("Get meal with id={} for user={}", id, userId);
        return mealService.get(id, userId);
    }

    public List<MealWithExceed> getAll()
    {
        int userId = AuthorizedUser.getId();
        LOG.info("Get all meal for user with id={}", userId);
        return MealsUtil.getListWithExceed(mealService.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal create(Meal meal)
    {
        meal.setId(null);
        int userId = AuthorizedUser.getId();
        LOG.info("Create meal={} for user={}", meal, userId);
        return mealService.save(meal, userId);
    }

    public void update(Meal meal, int id)
    {
        meal.setId(id);
        int userId = AuthorizedUser.getId();
        LOG.info("Update meal={} for user={}", meal, userId);
        mealService.save(meal, userId);
    }

    public List<MealWithExceed> getBetween(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.getId();
        LOG.info("Get meals between date {} - {} and time{} - {} for user with id={}", startDate, endDate,
                startTime, endTime, userId);
        return MealsUtil.getFilteredWithExceeded(
                mealService.getBetween(startDate == null ? TimeUtil.MIN_DATE : startDate,
                        endDate == null ? TimeUtil.MAX_DATE : endDate,
                        userId),
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime,
                AuthorizedUser.getCaloriesPerDay()
        );
    }
}
