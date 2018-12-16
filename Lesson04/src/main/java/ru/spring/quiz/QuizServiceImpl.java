package ru.spring.quiz;

import org.springframework.stereotype.Service;
import ru.spring.service.I18nService;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final I18nService i18nService;
    private List<Question> questions;
    private String lastName;
    private String firstName;
    private int rightAnswers = 0;

    public QuizServiceImpl(Scanner scanner, PrintStream printStream, I18nService i18nService) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.i18nService = i18nService;
        try {
            this.questions = i18nService.getQuestions();
        } catch (IOException e) {
            this.questions = Collections.emptyList();
            this.printStream.println(this.i18nService.getMessage("quiz.error"));
        }
    }

    public void holdQuiz() {
        if (!this.questions.isEmpty()) {
            this.inputLastName();
            this.inputFirstName();
            this.askQuestions();
            this.showResult();
        }
    }

    private void inputLastName() {
        this.printStream.print(this.i18nService.getMessage("quiz.enter.last.name"));
        this.lastName = this.scanner.nextLine();
    }

    private void inputFirstName() {
        this.printStream.print(this.i18nService.getMessage("quiz.enter.first.name"));
        this.firstName = this.scanner.nextLine();
    }

    private void askQuestions() {
        for (Question question : this.questions) {
            this.checkQuestion(question);
        }
    }

    private void checkQuestion(Question question) {
        this.printStream.print(question.getQuestion());
        String answer = this.scanner.nextLine();
        if (question.getAnswer().equalsIgnoreCase(answer)) {
            this.rightAnswers++;
        }
    }

    private void showResult() {
        this.printStream.println(this.i18nService.getMessage("quiz.result",
                new Object[]{this.lastName, this.firstName, this.rightAnswers}));
    }
}
