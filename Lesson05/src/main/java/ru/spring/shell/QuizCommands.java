package ru.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.spring.quiz.QuizService;
import ru.spring.service.I18nService;

@ShellComponent
public class QuizCommands {
    private final QuizService quizService;
    private final I18nService i18nService;

    public QuizCommands(QuizService quizService, I18nService i18nService) {
        this.quizService = quizService;
        this.i18nService = i18nService;
    }

    @ShellMethod("Login")
    public void login(@ShellOption(defaultValue = "ru_RU") String locale) {
        this.i18nService.setLocale(locale);
        this.quizService.login();
    }

    @ShellMethod("Start quiz")
    public void startQuiz() {
        this.quizService.holdQuiz();
    }

    @ShellMethod("Show result")
    public void showResult() {
        this.quizService.showResult();
    }
}
