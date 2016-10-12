package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.Profiles;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import static ru.javawebinar.topjava.UserTestData.*;


public class UserServiceTest extends AbstractServiceTest{
    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception
    {
        service.evictCache();
    }

    public void testSave() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, Collections.singleton(Role.ROLE_USER));
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER), service.getAll());
    }

    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    public void testDeleteNotFound() throws Exception {
        super.testGetNotFound();
        service.delete(100);
    }

    public void testGet() throws Exception {
        User user = service.get(USER_ID);
        MATCHER.assertEquals(USER, user);
    }

    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
        service.get(100);
    }

    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);
    }

    public void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }

    @Test
    public void testDuplicateMailSave() throws Exception {
        exception.expect(DataAccessException.class);
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);
    }
}