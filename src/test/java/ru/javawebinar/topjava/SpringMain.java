package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.Profiles;
import static ru.javawebinar.topjava.TestUtil.mockAuthorize;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.USER;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.ACTIVE_DB, Profiles.ACTIVE_REPOSITORY);
            appCtx.load("spring/spring-app", "spring/spring-db", "spring/spring-mvc");
            appCtx.refresh();

            mockAuthorize(USER);

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.get(UserTestData.USER_ID);
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalTime.of(7, 0),
                            LocalTime.of(11, 0),
                            LocalDate.of(2015, Month.MAY, 30),
                            LocalDate.of(2015, Month.MAY, 31));
            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}
