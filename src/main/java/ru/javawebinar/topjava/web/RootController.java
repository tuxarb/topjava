package ru.javawebinar.topjava.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class RootController {
    /*@Autowired
    private MealRestController mealController;*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:meals";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers() {
        return "users";
    }

    @GetMapping("/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }


    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getMeals() {
        return "meals";
    }

    /*@RequestMapping(value = "/meals", params = {"action=filter"}, method = RequestMethod.POST)
    public String filterMeals(Model model, HttpServletRequest req) {
        LocalDate startDate = TimeUtil.parseLocalDate(req.getParameter("startDate"));
        LocalDate endDate = TimeUtil.parseLocalDate(req.getParameter("endDate"));
        LocalTime startTime = TimeUtil.parseLocalTime(req.getParameter("startTime"));
        LocalTime endTime = TimeUtil.parseLocalTime(req.getParameter("endTime"));
        model.addAttribute("mealList", mealController.getBetween(startTime, endTime, startDate, endDate));
        return "meals";
    }


    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String createOrUpdateMeal(HttpServletRequest req) throws IOException {
        String id = req.getParameter("id");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.valueOf(req.getParameter("calories"));

        final Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id), ldt, description, calories);
        if (id.isEmpty()) {
            mealController.create(meal);
        } else
            mealController.update(meal, Integer.valueOf(id));
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", params = "action=delete", method = RequestMethod.GET)
    public String deleteMeal(HttpServletRequest req) {
        int id = getId(req);
        mealController.delete(id);
        return "redirect:meals";
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(paramId);
    }*/

    @GetMapping("/ru_text")
    public @ResponseBody String testUTF() {
        return "Русские буквы";
    }
}
