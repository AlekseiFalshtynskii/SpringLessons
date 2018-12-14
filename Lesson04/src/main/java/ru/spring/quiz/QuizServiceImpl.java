package ru.spring.quiz;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.spring.csv.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final MessageSource messageSource;
    private final Locale locale;
    private List<Question> questions;
    private String lastName;
    private String firstName;
    private int rightAnswers = 0;

    public QuizServiceImpl(Scanner scanner, PrintStream printStream, MessageSource messageSource, CSVReader csvReader) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.messageSource = messageSource;
        this.locale = LocaleContextHolder.getLocale();
        try {
            this.questions = csvReader.readQuestions();
        } catch (IOException e) {
            this.questions = Collections.emptyList();
            this.printStream.println(this.messageSource.getMessage("quiz.error", null, this.locale));
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
        this.printStream.print(this.messageSource.getMessage("quiz.enter.last.name", null, this.locale));
        this.lastName = this.scanner.nextLine();
    }

    private void inputFirstName() {
        this.printStream.print(this.messageSource.getMessage("quiz.enter.first.name", null, this.locale));
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
        this.printStream.println(this.messageSource.getMessage("quiz.result",
                new Object[]{this.lastName, this.firstName, this.rightAnswers},
                this.locale));
    }
}
