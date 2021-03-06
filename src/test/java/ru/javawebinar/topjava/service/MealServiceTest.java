package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class MealServiceTest extends AbstractServiceTest{
    @Autowired
    private MealService service;

    public void testDelete() {
        service.delete(MEAL1.getId(), USER_ID);
        MATCHER.assertCollectionEquals(
                MEALS.stream().filter(meal -> !meal.getId().equals(MEAL1.getId())).collect(Collectors.toList()),
                service.getAll(1));
    }

    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
        service.delete(MEAL1.getId(), 3);
    }

    public void testSave() throws Exception {
        Meal created = new Meal(null, LocalDateTime.now(), "abcd", 1000);
        service.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(USER_ID));
    }

    public void testGet() throws Exception {
        Meal actual = service.get(ADMIN_MEAL1.getId(), ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
    }

    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
        service.get(MEAL1.getId(), ADMIN_ID);
    }

    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1.getId(), LocalDateTime.now(), "sfsf", 150);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL1.getId(), USER_ID));
    }

    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS, service.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetween(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        exception.expect(NotFoundException.class);
        Meal item = service.get(MEAL1.getId(), USER_ID);
        service.update(item, ADMIN_ID);
    }
}