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

    @ShellMethod(key = "start", value = "Start quiz")
    public void holdQuiz(@ShellOption(defaultValue = "ru_RU") String locale) {
        this.i18nService.setLocale(locale);
        this.quizService.holdQuiz();
    }
}
