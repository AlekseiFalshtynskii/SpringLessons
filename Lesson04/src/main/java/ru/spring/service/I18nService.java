package ru.spring.service;

import org.springframework.lang.Nullable;
import ru.spring.quiz.Question;

import java.io.IOException;
import java.util.List;

public interface I18nService {
    List<Question> getQuestions() throws IOException;
    String getMessage(String code);
    String getMessage(String code, @Nullable Object[] args);
}
