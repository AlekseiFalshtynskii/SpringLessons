package ru.spring.csv;

import org.junit.Test;

import java.io.IOException;

public class CSVReaderImplTest {
    private static final String CSV_RU = "csv/quiz_ru_RU.csv";
    private static final String CSV_EN = "csv/quiz_en_US.csv";

    private CSVReader csvReader = new CSVReaderImpl();

    @Test
    public void readQuestions() throws Exception {
        this.readQuestions(CSV_RU);
        this.readQuestions(CSV_EN);
    }

    private void readQuestions(String csv) throws IOException {
        this.csvReader.readQuestions(csv);
    }
}
