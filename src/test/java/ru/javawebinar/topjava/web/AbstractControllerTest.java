package ru.javawebinar.topjava.web;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.Profiles;

import javax.annotation.PostConstruct;

@ContextConfiguration({
        "classpath:spring/spring-mvc",
        "classpath:spring/spring-app",
        "classpath:spring/spring-db"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({Profiles.ACTIVE_REPOSITORY, Profiles.ACTIVE_DB})
@Transactional
public abstract class AbstractControllerTest {
    private static CharacterEncodingFilter filter = new CharacterEncodingFilter();

    static{
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    @Autowired
    protected UserService userService;

    @Autowired
    protected MealService mealService;

    @Autowired
    private WebApplicationContext webContext;

    @PostConstruct
    public void postConstruct()
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                //.addFilter(filter)
                .build();
    }

    @Before
    public void setUp()
    {
        userService.evictCache();
    }


}
