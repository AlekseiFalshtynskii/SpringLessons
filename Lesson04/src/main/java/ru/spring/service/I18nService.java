package ru.spring.service;

import org.springframework.lang.Nullable;

import java.util.Locale;

public interface I18nService {
    void setLocale(String quizLocale);
    Locale getLocale();
    String getQuestionsCsvFileName();
    String getMessage(String code);
    String getMessage(String code, @Nullable Object[] args);
}
