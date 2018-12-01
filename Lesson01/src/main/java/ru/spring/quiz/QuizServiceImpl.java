package ru.spring.quiz;

import ru.spring.csv.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizServiceImpl implements QuizService {
    private final Scanner scanner;
    private final PrintStream printStream;
    private List<Question> questions;
    private String lastName;
    private String firstName;
    private int rightAnswers = 0;

    public QuizServiceImpl(Scanner scanner, PrintStream printStream, CSVReader csvReader) {
        this.scanner = scanner;
        this.printStream = printStream;
        try {
            this.questions = csvReader.readQuestions();
        } catch(IOException e) {
            this.questions = Collections.emptyList();
            this.printStream.println("Error: Ошибка загрузки файла quiz.csv");
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
        this.printStream.print("Введите вашу фамилию: ");
        this.lastName = this.scanner.nextLine();
    }

    private void inputFirstName() {
        this.printStream.print("Введите ваше имя: ");
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
        if (question.getAnswer().equals(answer)) {
            this.rightAnswers++;
        }
    }

    private void showResult() {
        this.printStream.println(this.lastName + " " + this.firstName + ", правильных ответов: " + this.rightAnswers);
    }
}
