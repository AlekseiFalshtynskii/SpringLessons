package ru.spring.csv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.quiz.Question;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class CSVReaderImplTest {
    private static final String CSV_RU = "csv/quiz_ru_RU.csv";
    private static final String CSV_EN = "csv/quiz_en_US.csv";

    @Autowired
    private CSVReader csvReader;

    @Test
    public void readQuestions() throws Exception {
        List<Question> questions = this.csvReader.readQuestions(CSV_RU);
        assertEquals(1, questions.size());
        assertEquals("Как дела?", questions.get(0).getQuestion());
        assertEquals("Нормально. Спасибо", questions.get(0).getAnswer());

        questions = this.csvReader.readQuestions(CSV_EN);
        assertEquals(1, questions.size());
        assertEquals("How are you?", questions.get(0).getQuestion());
        assertEquals("I'm fine. Thank you", questions.get(0).getAnswer());
    }
}
