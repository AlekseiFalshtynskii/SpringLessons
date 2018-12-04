package ru.spring.csv;

import ru.spring.quiz.Question;

import java.io.IOException;
import java.util.List;

public interface CSVReader {
    List<Question> readQuestions() throws IOException;
}
