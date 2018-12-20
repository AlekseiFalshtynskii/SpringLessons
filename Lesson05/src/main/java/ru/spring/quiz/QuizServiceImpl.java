package ru.spring.quiz;

import org.springframework.stereotype.Service;
import ru.spring.csv.CSVReader;
import ru.spring.service.I18nService;
import ru.spring.service.InputOutputService;

import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class QuizServiceImpl implements QuizService {
    private final InputOutputService inputOutputService;
    private final I18nService i18nService;
    private final CSVReader csvReader;
    private String lastName;
    private String firstName;
    private boolean complete;
    private int rightAnswers = 0;

    public QuizServiceImpl(InputOutputService inputOutputService, I18nService i18nService, CSVReader csvReader) {
        this.inputOutputService = inputOutputService;
        this.i18nService = i18nService;
        this.csvReader = csvReader;
    }

    @Override
    public void login() {
        this.complete = false;
        this.inputLastName();
        this.inputFirstName();
    }

    @Override
    public void holdQuiz() {
        if (isEmpty(this.lastName) || isEmpty(this.firstName)) {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.need.login"));
        } else {
            try {
                this.askQuestions(this.csvReader.readQuestions(this.i18nService.getQuestionsCsvFileName()));
                this.complete = true;
            } catch (IOException e) {
                this.inputOutputService.println(this.i18nService.getMessage("quiz.error"));
            }
        }
    }

    private void inputLastName() {
        do {
            this.inputOutputService.print(this.i18nService.getMessage("quiz.enter.last.name"));
            this.lastName = this.inputOutputService.nextLine();
        } while (isEmpty(this.lastName));
    }

    private void inputFirstName() {
        do {
            this.inputOutputService.print(this.i18nService.getMessage("quiz.enter.first.name"));
            this.firstName = this.inputOutputService.nextLine();
        } while (isEmpty(this.firstName));
    }

    private void askQuestions(List<Question> questions) {
        for (Question question : questions) {
            this.checkQuestion(question);
        }
    }

    private void checkQuestion(Question question) {
        String answer;
        do {
            this.inputOutputService.print(question.getQuestion());
            answer = this.inputOutputService.nextLine();
        } while (isEmpty(answer));
        if (question.getAnswer().equalsIgnoreCase(answer)) {
            this.rightAnswers++;
        }
    }

    @Override
    public void showResult() {
        if (this.complete) {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.result",
                    new Object[]{this.lastName, this.firstName, this.rightAnswers}));
        } else if (isEmpty(this.lastName) || isEmpty(this.firstName)) {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.need.login"));
        } else {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.need.complete"));
        }
    }
}
