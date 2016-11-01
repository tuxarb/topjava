package ru.javawebinar.topjava.web.user;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

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
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, email, password, Role.ROLE_USER);
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(user, id);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }
}
