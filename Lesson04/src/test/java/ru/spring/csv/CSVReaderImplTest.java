package ru.spring.csv;

import org.junit.Test;
import ru.spring.quiz.Question;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVReaderImplTest {
    private static final String CSV_RU = "csv/quiz_ru_RU.csv";
    private static final String CSV_EN = "csv/quiz_en_US.csv";

    private CSVReader csvReader = new CSVReaderImpl();

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
