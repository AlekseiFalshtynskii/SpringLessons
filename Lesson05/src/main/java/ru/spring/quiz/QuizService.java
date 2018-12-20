package ru.spring.quiz;

import ru.spring.exception.CompletionAccessException;
import ru.spring.exception.UnauthorizedAccessException;

public interface QuizService {
    void login();
    void holdQuiz() throws UnauthorizedAccessException;
    void showResult() throws UnauthorizedAccessException, CompletionAccessException;
}
