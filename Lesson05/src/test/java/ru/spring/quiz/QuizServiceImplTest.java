package ru.spring.quiz;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.csv.CSVReader;
import ru.spring.service.I18nService;
import ru.spring.service.InputOutputService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class QuizServiceImplTest {
    private static final String CSV = "csv";
    private static final String LAST_NAME = "Ivanov";
    private static final String FIRST_NAME = "Ivan";
    private static final String ERROR_CSV = "Error csv";
    private static final List<Question> QUESTIONS = Arrays.asList(
            new Question("Question_1 ", "1"),
            new Question("Question_2 ", "2"),
            new Question("Question_3 ", "3"),
            new Question("Question_4 ", "4"),
            new Question("Question_5 ", "5")
    );

    @MockBean
    private InputOutputService inputOutputService;
    @MockBean
    private I18nService i18nServiceMock;
    @MockBean
    private CSVReader csvReaderMock;
    @Autowired
    private QuizService quizService;

    @Before
    public void before() throws Exception {
        when(this.i18nServiceMock.getQuestionsCsvFileName()).thenReturn(CSV);
        when(this.i18nServiceMock.getMessage("quiz.error")).thenReturn(ERROR_CSV);
        when(this.csvReaderMock.readQuestions(CSV)).thenReturn(QUESTIONS);
        reset(this.inputOutputService);
    }

    @Test
    public void holdQuiz() throws Exception {
        when(this.inputOutputService.nextLine()).thenReturn(LAST_NAME, FIRST_NAME, "1", "2", "3", "4", "5");
        this.quizService.holdQuiz();
        verify(this.inputOutputService, times(7)).nextLine();
        verify(this.i18nServiceMock, times(1)).getQuestionsCsvFileName();
        verify(this.i18nServiceMock, times(1)).getMessage("quiz.enter.last.name");
        verify(this.i18nServiceMock, times(1)).getMessage("quiz.enter.first.name");
        verify(this.i18nServiceMock, times(1)).getMessage("quiz.result",
                new Object[]{LAST_NAME, FIRST_NAME, 5});
        verify(this.csvReaderMock, times(1)).readQuestions(CSV);
    }

    @Test
    public void holdQuizException() throws Exception {
        when(this.csvReaderMock.readQuestions(CSV)).thenThrow(new IOException());
        this.quizService.holdQuiz();
        verify(this.i18nServiceMock, times(1)).getQuestionsCsvFileName();
        verify(this.csvReaderMock, times(1)).readQuestions(CSV);
        verify(this.i18nServiceMock, times(1)).getMessage("quiz.error");
        verify(this.inputOutputService, times(1)).println(ERROR_CSV);

        verify(this.inputOutputService, times(0)).nextLine();
        verify(this.i18nServiceMock, times(0)).getMessage("quiz.enter.last.name");
        verify(this.i18nServiceMock, times(0)).getMessage("quiz.enter.first.name");
        verify(this.i18nServiceMock, times(0)).getMessage("quiz.result",
                new Object[]{LAST_NAME, FIRST_NAME, 5});
    }
}
