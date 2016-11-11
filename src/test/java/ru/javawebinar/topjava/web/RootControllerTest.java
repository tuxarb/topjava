package ru.javawebinar.topjava.web;


import org.junit.Test;
import ru.javawebinar.topjava.TestUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.TestUtil.authorize;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

public class RootControllerTest extends AbstractControllerTest{
    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(authorize(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    public void testMealsGet() throws Exception
    {
        authorize(USER);
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("mealList", hasSize(6)))
                .andExpect(model().attribute("mealList", hasItem(
                        hasProperty("id", is(5)))));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testCss() throws Exception
    {
         mockMvc.perform(get("/resources/css/style.css"))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(content().contentTypeCompatibleWith("text/css"));
    }

}
