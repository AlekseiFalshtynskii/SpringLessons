package ru.spring.quiz;

import org.springframework.stereotype.Service;
import ru.spring.csv.CSVReader;
import ru.spring.service.I18nService;
import ru.spring.service.InputOutputService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final InputOutputService inputOutputService;
    private final I18nService i18nService;
    private final CSVReader csvReader;
    private List<Question> questions;
    private String lastName;
    private String firstName;
    private int rightAnswers = 0;

    public QuizServiceImpl(InputOutputService inputOutputService, I18nService i18nService, CSVReader csvReader) {
        this.inputOutputService = inputOutputService;
        this.i18nService = i18nService;
        this.csvReader = csvReader;
    }

    public void holdQuiz() {
        try {
            this.questions = this.csvReader.readQuestions(this.i18nService.getQuestionsCsvFileName());
            if (!this.questions.isEmpty()) {
                this.inputLastName();
                this.inputFirstName();
                this.askQuestions();
                this.showResult();
            }
        } catch (IOException e) {
            this.questions = Collections.emptyList();
            this.inputOutputService.println(this.i18nService.getMessage("quiz.error"));
        }
    }

    private void inputLastName() {
        this.inputOutputService.print(this.i18nService.getMessage("quiz.enter.last.name"));
        this.lastName = this.inputOutputService.nextLine();
    }

    private void inputFirstName() {
        this.inputOutputService.print(this.i18nService.getMessage("quiz.enter.first.name"));
        this.firstName = this.inputOutputService.nextLine();
    }

    private void askQuestions() {
        for (Question question : this.questions) {
            this.checkQuestion(question);
        }
    }

    private void checkQuestion(Question question) {
        this.inputOutputService.print(question.getQuestion());
        String answer = this.inputOutputService.nextLine();
        if (question.getAnswer().equalsIgnoreCase(answer)) {
            this.rightAnswers++;
        }
    }

    private void showResult() {
        this.inputOutputService.println(this.i18nService.getMessage("quiz.result",
                new Object[]{this.lastName, this.firstName, this.rightAnswers}));
    }
}
