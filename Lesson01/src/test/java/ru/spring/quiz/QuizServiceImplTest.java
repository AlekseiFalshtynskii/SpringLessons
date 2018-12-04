package ru.spring.quiz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;
import ru.spring.csv.CSVReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.when;

public class QuizServiceImplTest {
    private static final String LAST_NAME = "Ivanov";
    private static final String FIRST_NAME = "Ivan";
    private static final String RESULT = "Введите вашу фамилию: Введите ваше имя: 2 x 2 = 3 x 3 = 4 x 4 = 5 x 5 = 6 x 6 = %s %s, правильных ответов: %s\n";
    private static final List<Question> questions = Arrays.asList(
            new Question("2 x 2 = ", "4"),
            new Question("3 x 3 = ", "9"),
            new Question("4 x 4 = ", "16"),
            new Question("5 x 5 = ", "25"),
            new Question("6 x 6 = ", "36")
    );

    private CSVReader csvReaderMock = Mockito.mock(CSVReader.class);
    private QuizService quizService;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void before() {
        Mockito.reset(this.csvReaderMock);
    }

    @Test
    public void holdQuizOk() throws Exception {
        when(this.csvReaderMock.readQuestions()).thenReturn(questions);
        this.quizService = new QuizServiceImpl(new Scanner(System.in), System.out, csvReaderMock);

        systemInMock.provideLines(LAST_NAME, FIRST_NAME, "4", "9", "16", "25", "36");
        this.quizService.holdQuiz();
        assertEquals(String.format(RESULT, LAST_NAME, FIRST_NAME, "5"), systemOutRule.getLog());
    }

    @Test
    public void holdQuizError() throws Exception {
        when(this.csvReaderMock.readQuestions()).thenReturn(questions);
        this.quizService = new QuizServiceImpl(new Scanner(System.in), System.out, csvReaderMock);

        systemInMock.provideLines(LAST_NAME, FIRST_NAME, "4", "9", "16", "25", "37");
        this.quizService.holdQuiz();
        assertEquals(String.format(RESULT, LAST_NAME, FIRST_NAME, "4"), systemOutRule.getLog());
    }

    @Test
    public void holdQuizException() throws Exception {
        when(this.csvReaderMock.readQuestions()).thenThrow(new IOException());
        this.quizService = new QuizServiceImpl(new Scanner(System.in), System.out, csvReaderMock);

        this.quizService.holdQuiz();
        assertEquals("Error: Ошибка загрузки файла quiz.csv\n", systemOutRule.getLog());
    }
}
