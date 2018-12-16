package ru.spring.quiz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.config.QuizServiceImplTestConfig;
import ru.spring.csv.CSVReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {QuizServiceImplTestConfig.class})
public class QuizServiceImplTest {
    private static final String LOCALE_RU = "ru_RU";
    private static final String LOCALE_EN = "en_US";
    private static final String LAST_NAME = "Ivanov";
    private static final String FIRST_NAME = "Ivan";
    private static final String RESULT_RU = "Введите вашу фамилию: Введите ваше имя: 2 * 2 = 3 * 3 = 4 * 4 = 5 * 5 = 6 * 6 = %s %s, правильных ответов: %s\n";
    private static final String RESULT_EN = "Enter your last name: Enter your first name: 2 x 2 = 3 x 3 = 4 x 4 = 5 x 5 = 6 x 6 = Dear, %s %s. Correct answers are %s\n";
    private static final String RESULT_ERROR_RU = "Ошибка загрузки файла quiz.csv\n";
    private static final String RESULT_ERROR_EN = "quiz.csv file loading error\n";
    private static final List<Question> QUESTIONS_RU = Arrays.asList(
            new Question("2 * 2 = ", "4"),
            new Question("3 * 3 = ", "9"),
            new Question("4 * 4 = ", "16"),
            new Question("5 * 5 = ", "25"),
            new Question("6 * 6 = ", "36")
    );
    private static final List<Question> QUESTIONS_EN = Arrays.asList(
            new Question("2 x 2 = ", "4"),
            new Question("3 x 3 = ", "9"),
            new Question("4 x 4 = ", "16"),
            new Question("5 x 5 = ", "25"),
            new Question("6 x 6 = ", "36")
    );
    private static final List<String> ANSWERS_OK = Arrays.asList("4", "9", "16", "25", "36");
    private static final List<String> ANSWERS_ERROR = Arrays.asList("4", "9", "16", "24", "37");
    private static final int RIGHT_ANSWERS_OK = 5;
    private static final int RIGHT_ANSWERS_ERROR = 3;

    @Autowired
    private MessageSource messageSource;
    @MockBean
    private CSVReader csvReaderMock;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void before() {
        Mockito.reset(this.csvReaderMock);
    }

    @Test
    public void holdQuizOkRU() throws Exception {
        holdQuiz(LOCALE_RU, QUESTIONS_RU, ANSWERS_OK, RESULT_RU, RIGHT_ANSWERS_OK);
    }

    @Test
    public void holdQuizOkEN() throws Exception {
        holdQuiz(LOCALE_EN, QUESTIONS_EN, ANSWERS_OK, RESULT_EN, RIGHT_ANSWERS_OK);
    }

    @Test
    public void holdQuizErrorRU() throws Exception {
        holdQuiz(LOCALE_RU, QUESTIONS_RU, ANSWERS_ERROR, RESULT_RU, RIGHT_ANSWERS_ERROR);
    }

    @Test
    public void holdQuizErrorEN() throws Exception {
        holdQuiz(LOCALE_EN, QUESTIONS_EN, ANSWERS_ERROR, RESULT_EN, RIGHT_ANSWERS_ERROR);
    }

    private void holdQuiz(String locale, List<Question> questions, List<String> answers, String result, int rightAnswers) throws IOException {
//        LocaleContextHolder.setDefaultLocale(new Locale(locale));
//        when(this.csvReaderMock.readQuestions()).thenReturn(questions);
//        QuizService quizService = new QuizServiceImpl(new Scanner(System.in), System.out, this.messageSource, this.csvReaderMock);
//        this.systemInMock.provideLines(LAST_NAME, FIRST_NAME, answers.get(0), answers.get(1), answers.get(2), answers.get(3), answers.get(4));
//        quizService.holdQuiz();
//        assertEquals(String.format(result, LAST_NAME, FIRST_NAME, rightAnswers), this.systemOutRule.getLog());
    }

    @Test
    public void holdQuizExceptionRU() throws Exception {
        holdQuizException(LOCALE_RU, RESULT_ERROR_RU);
    }

    @Test
    public void holdQuizExceptionEN() throws Exception {
        holdQuizException(LOCALE_EN, RESULT_ERROR_EN);
    }

    private void holdQuizException(String locale, String result) throws IOException {
//        LocaleContextHolder.setDefaultLocale(new Locale(locale));
//        when(this.csvReaderMock.readQuestions()).thenThrow(new IOException());
//        QuizService quizService = new QuizServiceImpl(new Scanner(System.in), System.out, messageSource, this.csvReaderMock);
//        quizService.holdQuiz();
//        assertEquals(result, this.systemOutRule.getLog());
    }
}
