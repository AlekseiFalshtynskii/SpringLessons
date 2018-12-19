package ru.spring.quiz;

import org.springframework.stereotype.Service;
import ru.spring.csv.CSVReader;
import ru.spring.service.I18nService;
import ru.spring.service.InputOutputService;

import java.io.IOException;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final InputOutputService inputOutputService;
    private final I18nService i18nService;
    private final CSVReader csvReader;
    private String lastName;
    private String firstName;
    private int rightAnswers = 0;

    public QuizServiceImpl(InputOutputService inputOutputService, I18nService i18nService, CSVReader csvReader) {
        this.inputOutputService = inputOutputService;
        this.i18nService = i18nService;
        this.csvReader = csvReader;
    }

    @Override
    public void login() {
        this.inputLastName();
        this.inputFirstName();
    }

    @Override
    public void holdQuiz() {
        try {
            this.askQuestions(this.csvReader.readQuestions(this.i18nService.getQuestionsCsvFileName()));
        } catch (IOException e) {
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

    private void askQuestions(List<Question> questions) {
        for (Question question : questions) {
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

    @Override
    public void showResult() {
        this.inputOutputService.println(this.i18nService.getMessage("quiz.result",
                new Object[]{this.lastName, this.firstName, this.rightAnswers}));
    }
}
