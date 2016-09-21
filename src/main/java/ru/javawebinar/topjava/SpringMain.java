package ru.javawebinar.topjava;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.mock.MockUserRepository;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app");
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        //MockUserRepository mockUserRepository = (MockUserRepository) applicationContext.getBean("mockUserRepository");
        applicationContext.close();
    }
}
