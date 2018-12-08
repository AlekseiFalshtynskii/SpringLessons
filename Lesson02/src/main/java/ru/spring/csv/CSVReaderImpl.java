package ru.spring.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.spring.quiz.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReaderImpl implements CSVReader {
    private final Resource resource;

    public CSVReaderImpl(Resource resource) {
        this.resource = resource;
    }

    public List<Question> readQuestions() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.resource.getInputStream()));
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(br);
        List<Question> questions = new ArrayList<>();
        for (CSVRecord record : parser) {
            questions.add(new Question(record.get("question"), record.get("answer")));
        }
        return questions;
    }
}
