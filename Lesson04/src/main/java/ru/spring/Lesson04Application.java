package ru.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.spring.quiz.QuizService;

@SpringBootApplication
public class Lesson04Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lesson04Application.class, args);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.holdQuiz();
    }
}
