package ru.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

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

    private I18nService i18nService;

    @Before
    public void setUp() throws Exception {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        i18nService = new I18nServiceImpl(LOCALE_RU, "quiz_%s.csv", messageSource);
    }

    @Test
    public void getLocale() {
        Locale expectedLocale = new Locale(LOCALE_RU);
        assertEquals(expectedLocale, i18nService.getLocale());
        expectedLocale = new Locale(LOCALE_EN);
        i18nService.setLocale(LOCALE_EN);
        assertEquals(expectedLocale, i18nService.getLocale());
    }

    @Test
    public void getQuestionsCsvFileName() {
        String expectedFileName = "quiz_ru_ru.csv";
        assertEquals(expectedFileName, i18nService.getQuestionsCsvFileName());
        expectedFileName = "quiz_en_us.csv";
        i18nService.setLocale(LOCALE_EN);
        assertEquals(expectedFileName, i18nService.getQuestionsCsvFileName());
    }

    @Test
    public void getMessage() {
        IntConsumer assertMessageRU = i -> assertEquals(MESSAGES_RU.get(i), i18nService.getMessage(BUNDLE_KEYS.get(i)));
        IntStream.range(0, BUNDLE_KEYS.size()).forEach(assertMessageRU);

        i18nService.setLocale(LOCALE_EN);

        IntConsumer assertMessageEN = i -> assertEquals(MESSAGES_EN.get(i), i18nService.getMessage(BUNDLE_KEYS.get(i)));
        IntStream.range(0, BUNDLE_KEYS.size()).forEach(assertMessageEN);
    }
}
