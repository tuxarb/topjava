package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.util.Profiles;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@ContextConfiguration({
        "classpath:spring/spring-app",
        "classpath:spring/spring-db"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB")
@ActiveProfiles(value = {Profiles.ACTIVE_DB, Profiles.ACTIVE_REPOSITORY})
public abstract class AbstractServiceTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public abstract void testDelete() throws Exception;

    @Test()
    public void testDeleteNotFound() throws Exception
    {
        exception.expect(NotFoundException.class);
    }

    @Test
    public abstract void testSave() throws Exception;

    @Test
    public abstract void testUpdate() throws Exception;

    @Test
    public abstract void testGet() throws Exception;

    @Test()
    public void testGetNotFound() throws Exception
    {
        exception.expect(NotFoundException.class);
    }

    @Test
    public abstract void testGetAll() throws Exception;
}
