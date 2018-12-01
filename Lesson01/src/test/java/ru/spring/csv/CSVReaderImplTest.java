package ru.spring.csv;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.spring.quiz.Question;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CSVReaderImplTest {
    private static final List<Question> questions = Arrays.asList(
            new Question("2 x 2 = ", "4"),
            new Question("3 x 3 = ", "9"),
            new Question("4 x 4 = ", "16"),
            new Question("5 x 5 = ", "25"),
            new Question("6 x 6 = ", "36")
    );

    @Test
    public void readQuestions() throws Exception {
        Resource resource = new ClassPathResource("quiz.csv");
        CSVReader csvReader = new CSVReaderImpl(resource);
        csvReader.readQuestions();
        assertThat(csvReader.readQuestions(), is(questions));
    }
}
