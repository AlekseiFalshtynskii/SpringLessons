package ru.spring.quiz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.csv.CSVReader;
import ru.spring.service.I18nService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizServiceImplTest {
    private static final String CSV = "csv";
    private static final String LAST_NAME = "Ivanov";
    private static final String FIRST_NAME = "Ivan";
    private static final String RESULT = "Last name: First name: Question_1 Question_2 Question_3 Question_4 Question_5 %s %s %s\n";
    private static final String RESULT_ERROR = "Error csv\n";
    private static final List<Question> QUESTIONS = Arrays.asList(
            new Question("Question_1 ", "1"),
            new Question("Question_2 ", "2"),
            new Question("Question_3 ", "3"),
            new Question("Question_4 ", "4"),
            new Question("Question_5 ", "5")
    );
    private static final List<String> ANSWERS_OK = Arrays.asList("1", "2", "3", "4", "5");
    private static final List<String> ANSWERS_ERROR = Arrays.asList("0", "0", "0", "0", "0");
    private static final int RIGHT_ANSWERS_OK = 5;
    private static final int RIGHT_ANSWERS_ERROR = 0;

    @MockBean
    private I18nService i18nServiceMock;
    @MockBean
    private CSVReader csvReaderMock;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void before() throws Exception {
        when(this.i18nServiceMock.getQuestionsCsvFileName()).thenReturn(CSV);
        when(this.i18nServiceMock.getMessage("quiz.enter.last.name")).thenReturn("Last name: ");
        when(this.i18nServiceMock.getMessage("quiz.enter.first.name")).thenReturn("First name: ");
        when(this.i18nServiceMock.getMessage("quiz.error")).thenReturn("Error csv");
        when(this.i18nServiceMock.getMessage("quiz.result", new Object[]{LAST_NAME, FIRST_NAME, RIGHT_ANSWERS_OK})).thenReturn(
                String.format("%s %s %s", LAST_NAME, FIRST_NAME, RIGHT_ANSWERS_OK)
        );
        when(this.i18nServiceMock.getMessage("quiz.result", new Object[]{LAST_NAME, FIRST_NAME, RIGHT_ANSWERS_ERROR})).thenReturn(
                String.format("%s %s %s", LAST_NAME, FIRST_NAME, RIGHT_ANSWERS_ERROR)
        );
        when(this.csvReaderMock.readQuestions(CSV)).thenReturn(QUESTIONS);
    }

    @Test
    public void holdQuizOk() throws Exception {
        holdQuiz(ANSWERS_OK, RESULT, RIGHT_ANSWERS_OK);
    }

    @Test
    public void holdQuizError() throws Exception {
        holdQuiz(ANSWERS_ERROR, RESULT, RIGHT_ANSWERS_ERROR);
    }

    private void holdQuiz(List<String> answers, String result, int rightAnswers) throws IOException {
        QuizService quizService = new QuizServiceImpl(new Scanner(System.in), System.out, this.i18nServiceMock, this.csvReaderMock);
        this.systemInMock.provideLines(LAST_NAME, FIRST_NAME, answers.get(0), answers.get(1), answers.get(2), answers.get(3), answers.get(4));
        quizService.holdQuiz();
        assertEquals(String.format(result, LAST_NAME, FIRST_NAME, rightAnswers), this.systemOutRule.getLog());
    }

    @Test
    public void holdQuizException() throws Exception {
        holdQuizException(RESULT_ERROR);
    }

    private void holdQuizException(String result) throws Exception {
        when(this.csvReaderMock.readQuestions(CSV)).thenThrow(new IOException());
        QuizService quizService = new QuizServiceImpl(new Scanner(System.in), System.out, this.i18nServiceMock, this.csvReaderMock);
        quizService.holdQuiz();
        assertEquals(result, this.systemOutRule.getLog());
    }
}
