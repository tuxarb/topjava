package ru.javawebinar.topjava.web.meal;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import ru.javawebinar.topjava.web.AbstractControllerTest;

public class MealRestControllerTest extends AbstractControllerTest{
    private static final String URL = MealRestController.URL;

    @Test
    public void testGet() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
