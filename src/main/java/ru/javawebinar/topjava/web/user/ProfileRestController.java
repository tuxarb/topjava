package ru.javawebinar.topjava.web.user;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.User;

@RestController
@RequestMapping(value = ProfileRestController.URL)
public class ProfileRestController extends AbstractUserController {
    static final String URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.getId());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        super.update(user, AuthorizedUser.getId());
    }
}
