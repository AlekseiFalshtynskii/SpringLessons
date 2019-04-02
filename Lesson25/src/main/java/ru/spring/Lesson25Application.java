package ru.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.config.EnableIntegration;
import ru.spring.service.InstagramService;

@SpringBootApplication
@EnableIntegration
public class Lesson25Application {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Lesson25Application.class);
        ctx.getBean(InstagramService.class).start();
    }
}
