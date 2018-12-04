package ru.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.spring.quiz.QuizService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        quizService.holdQuiz();
    }
}
