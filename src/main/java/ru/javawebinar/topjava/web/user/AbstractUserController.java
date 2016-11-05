package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;

import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractUserController {
    protected static final org.slf4j.Logger LOG = getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        LOG.info("update " + user);
        service.update(user);
    }

    public void update(UserTo updatedUser)
    {
        LOG.info("update " + updatedUser);
        service.update(updatedUser);
    }

    public User getByMail(String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public void check(int id, boolean enabled) {
        LOG.info("check with id = {} and enabled = {}", id, enabled);
        service.check(id, enabled);
    }
}
