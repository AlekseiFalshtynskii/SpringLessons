package ru.spring.csv;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.spring.quiz.Question;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CSVReaderImplTest {
    private static final String LOCALE_RU = "ru_RU";
    private static final String LOCALE_EN = "en_US";
    private static final String CSV_RU = "csv/quiz_ru_RU.csv";
    private static final String CSV_EN = "csv/quiz_en_US.csv";
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

    @Test
    public void readQuestionsRU() throws Exception {
        this.readQuestions(LOCALE_RU, CSV_RU, QUESTIONS_RU);
    }

    @Test
    public void readQuestionsEN() throws Exception {
        this.readQuestions(LOCALE_EN, CSV_EN, QUESTIONS_EN);
    }

    private void readQuestions(String locale, String csv, List<Question> questions) throws IOException {
        Locale.setDefault(new Locale(locale));
        Resource resource = new ClassPathResource(csv);
        CSVReader csvReader = new CSVReaderImpl(resource);
        assertThat(csvReader.readQuestions(), is(questions));
    }
}
