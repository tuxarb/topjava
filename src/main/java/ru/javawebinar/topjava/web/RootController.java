package ru.javawebinar.topjava.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.to.PasswordUserTo;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.PasswordUtil;
import ru.javawebinar.topjava.util.UsersUtil;
import ru.javawebinar.topjava.web.user.AbstractUserController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {

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

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult bindingResult, SessionStatus status) {
        if (!bindingResult.hasErrors()) {
            try {
                UserTo authUser = AuthorizedUser.get().getUserTo();
                if (!userTo.getEmail().equals(authUser.getEmail()) &&
                        UsersUtil.isTestAdmin(authUser)) {
                    throw new Exception();
                }
                userTo.setId(AuthorizedUser.id());
                userTo.setPassword(authUser.getPassword());
                super.update(userTo);
                AuthorizedUser.get().update(userTo);
                status.setComplete();
                return "redirect:profile?message=profile.success";
            } catch (DataIntegrityViolationException e) {
                bindingResult.rejectValue("email", "user.duplicatedMail");
            } catch (Exception e) {
                bindingResult.rejectValue("email", "profile.testAdmin.email.immutable");
            }
        }
        return "profile";
    }

    @PostMapping(value = "/profile", params = "password/update")
    public String updatePassword(@Valid PasswordUserTo passwordUserTo, BindingResult bindingResult, SessionStatus status, HttpServletRequest request) {
        if (!bindingResult.hasErrors()) {
            UserTo userTo = AuthorizedUser.get().getUserTo();
            if (UsersUtil.isTestAdmin(userTo)) {
                return "redirect:profile?message=profile.testAdmin.password.immutable";
            }
            if (!PasswordUtil.isMatch(passwordUserTo.getOldPassword(), userTo.getPassword())) {
                bindingResult.rejectValue("oldPassword", "profile.password.wrong");
                return "profile";
            }
            userTo.setPassword(passwordUserTo.getNewPassword());
            super.update(userTo);
            AuthorizedUser.get().update(userTo);
            status.setComplete();
            return "redirect:profile?message=profile.password.success";
        }
        return "profile";
    }

    @GetMapping("/register")
    public String register(ModelMap map) {
        map.put("userTo", new UserTo());
        map.put("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserTo userTo, BindingResult result, SessionStatus sessionStatus, ModelMap map) {
        if (!result.hasErrors()) {
            try {
                super.create(UsersUtil.createNewUserFromForm(userTo));
                sessionStatus.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException e) {
                result.rejectValue("email", "user.duplicatedMail");
            }
        }
        map.put("register", true);
        return "profile";
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
    public
    @ResponseBody
    String testUTF() {
        return "Русские буквы";
    }
}
