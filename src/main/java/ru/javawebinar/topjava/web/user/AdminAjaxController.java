package ru.javawebinar.topjava.web.user;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.List;

import static ru.javawebinar.topjava.web.user.AdminAjaxController.URL;

@RestController
@RequestMapping(URL)
public class AdminAjaxController extends AbstractUserController {
    static final String URL = "/ajax/admin/users";

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(UserTo newUser) {
        User user = UsersUtil.createNewUserFromForm(newUser);
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(user, user.getId());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }


    @PostMapping(value = "/{id}")
    public void check(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled)
    {
        super.check(id, enabled);
    }

}
