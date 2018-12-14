package ru.spring.csv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.spring.config.CSVReaderImplTestConfig;
import ru.spring.quiz.Question;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CSVReaderImplTestConfig.class})
public class CSVReaderImplTest {
    private static final String LOCALE_RU = "ru_RU";
    private static final String LOCALE_EN = "en_US";
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

    @Autowired
    private CSVReader csvReaderRU;
    @Autowired
    private CSVReader csvReaderEN;

    @Test
    public void readQuestionsRU() throws Exception {
        this.readQuestions(LOCALE_RU, this.csvReaderRU, QUESTIONS_RU);
    }

    @Test
    public void readQuestionsEN() throws Exception {
        this.readQuestions(LOCALE_EN, this.csvReaderEN, QUESTIONS_EN);
    }

    private void readQuestions(String locale, CSVReader csvReader, List<Question> questions) throws IOException {
        Locale.setDefault(new Locale(locale));
        assertThat(csvReader.readQuestions(), is(questions));
    }
}
