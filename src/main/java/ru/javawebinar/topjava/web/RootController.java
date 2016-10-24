package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
public class RootController {
    @Autowired
    private AdminRestController userController;

    @Autowired
    private MealRestController mealController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("users", userController.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getMeals(HttpServletRequest req) {
        List<MealWithExceed> mealList = mealController.getAll();
        req.setAttribute("mealList", mealList);
        return "meals";
    }

    @RequestMapping(value = "/meals", params = {"action=filter"}, method = RequestMethod.POST)
    public String filterMeals(Model model, HttpServletRequest req) {
        LocalDate startDate = TimeUtil.parseLocalDate(req.getParameter("startDate"));
        LocalDate endDate = TimeUtil.parseLocalDate(req.getParameter("endDate"));
        LocalTime startTime = TimeUtil.parseLocalTime(req.getParameter("startTime"));
        LocalTime endTime = TimeUtil.parseLocalTime(req.getParameter("endTime"));
        model.addAttribute("mealList", mealController.getBetween(startTime, endTime, startDate, endDate));
        return "meals";
    }

    @RequestMapping(value = "/meals", params = {"action=update"}, method = RequestMethod.GET)
    public String showUpdateMeal(Model model, HttpServletRequest req) {
        final Meal meal = mealController.get(getId(req));
        model.addAttribute("meal", meal);
        return "mealUpdate";
    }

    @RequestMapping(value = "/meals", params = {"action=create"}, method = RequestMethod.GET)
    public String showCreateMeal(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0);
        model.addAttribute("meal", meal);
        return "mealUpdate";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String createOrUpdateMeal(HttpServletRequest req) throws IOException{
        String id = req.getParameter("id");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.valueOf(req.getParameter("calories"));

        final Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id), ldt, description, calories);
        if (id.isEmpty()) {
            mealController.create(meal);
        }
        else
            mealController.update(meal, Integer.valueOf(id));
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", params = "action=delete", method = RequestMethod.GET)
    public String deleteMeal(HttpServletRequest req)
    {
        int id = getId(req);
        mealController.delete(id);
        return "redirect:meals";
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @GetMapping("/ru_text")
    public @ResponseBody String testUTF()
    {
        return "Русские буквы";
    }
}
