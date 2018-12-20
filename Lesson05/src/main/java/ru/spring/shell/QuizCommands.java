package ru.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.spring.exception.CompletionAccessException;
import ru.spring.exception.UnauthorizedAccessException;
import ru.spring.quiz.QuizService;
import ru.spring.service.I18nService;
import ru.spring.service.InputOutputService;

@ShellComponent
public class QuizCommands {
    private final QuizService quizService;
    private final I18nService i18nService;
    private final InputOutputService inputOutputService;

    public QuizCommands(QuizService quizService, I18nService i18nService, InputOutputService inputOutputService) {
        this.quizService = quizService;
        this.i18nService = i18nService;
        this.inputOutputService = inputOutputService;
    }

    @ShellMethod("Login")
    public void login(@ShellOption(defaultValue = "ru_RU") String locale) {
        this.i18nService.setLocale(locale);
        this.quizService.login();
    }

    @ShellMethod("Start quiz")
    public void startQuiz() {
        try {
            this.quizService.holdQuiz();
        } catch (UnauthorizedAccessException e) {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.need.login"));
        }
    }

    @ShellMethod("Show result")
    public void showResult() {
        try {
            this.quizService.showResult();
        } catch (UnauthorizedAccessException e) {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.need.login"));
        } catch (CompletionAccessException e) {
            this.inputOutputService.println(this.i18nService.getMessage("quiz.need.complete"));
        }
    }
}
