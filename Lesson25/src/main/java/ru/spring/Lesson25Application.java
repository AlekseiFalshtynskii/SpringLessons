package ru.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.config.EnableIntegration;
import ru.spring.service.PikabuService;

@SpringBootApplication
@EnableIntegration
public class Lesson25Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Lesson25Application.class, args);
        ctx.getBean(PikabuService.class).start();
    }
}
