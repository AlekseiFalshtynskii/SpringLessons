package ru.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class I18nServiceImplTest {
    private static final String LOCALE_RU = "ru_RU";
    private static final String LOCALE_EN = "en_US";
    private static final List<String> BUNDLE_KEYS = Arrays.asList(
            "quiz.enter.last.name",
            "quiz.enter.first.name",
            "quiz.result",
            "quiz.error"
    );
    private static final List<String> MESSAGES_RU = Arrays.asList(
            "Введите вашу фамилию: ",
            "Введите ваше имя: ",
            "{0} {1}, правильных ответов: {2}",
            "Ошибка загрузки файла quiz.csv"
    );
    private static final List<String> MESSAGES_EN = Arrays.asList(
            "Enter your last name: ",
            "Enter your first name: ",
            "Dear, {0} {1}. Correct answers are {2}",
            "quiz.csv file loading error"
    );

    @Autowired
    private I18nService i18nService;

    @Before
    public void setUp() throws Exception {
        this.i18nService.setLocale(LOCALE_RU);
    }

    @Test
    public void getLocale() {
        Locale expectedLocale = new Locale(LOCALE_RU);
        assertEquals(expectedLocale, this.i18nService.getLocale());
        expectedLocale = new Locale(LOCALE_EN);
        this.i18nService.setLocale(LOCALE_EN);
        assertEquals(expectedLocale, this.i18nService.getLocale());
    }

    @Test
    public void getQuestionsCsvFileName() {
        String expectedFileName = "csv/quiz_ru_ru.csv";
        assertEquals(expectedFileName, this.i18nService.getQuestionsCsvFileName());
        expectedFileName = "csv/quiz_en_us.csv";
        this.i18nService.setLocale(LOCALE_EN);
        assertEquals(expectedFileName, this.i18nService.getQuestionsCsvFileName());
    }

    @Test
    public void getMessage() {
        IntConsumer assertMessageRU = i -> assertEquals(MESSAGES_RU.get(i), this.i18nService.getMessage(BUNDLE_KEYS.get(i)));
        IntStream.range(0, BUNDLE_KEYS.size()).forEach(assertMessageRU);

        this.i18nService.setLocale(LOCALE_EN);

        IntConsumer assertMessageEN = i -> assertEquals(MESSAGES_EN.get(i), this.i18nService.getMessage(BUNDLE_KEYS.get(i)));
        IntStream.range(0, BUNDLE_KEYS.size()).forEach(assertMessageEN);
    }
}
