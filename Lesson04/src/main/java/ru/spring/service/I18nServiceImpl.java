package ru.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class I18nServiceImpl implements I18nService {
    private Locale locale;
    private final MessageSource messageSource;
    private String quizFileNamePattern;

    public I18nServiceImpl(@Value("${quiz.locale}") String quizLocale,
                           @Value("${quiz.file-name-pattern}") String quizFileNamePattern,
                           MessageSource messageSource) {
        this.messageSource = messageSource;
        this.setLocale(quizLocale);
        this.quizFileNamePattern = quizFileNamePattern;
    }

    @Override
    public void setLocale(String quizLocale) {
        this.locale = new Locale(quizLocale);
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public String getQuestionsCsvFileName() {
        return String.format(this.quizFileNamePattern, this.locale.toString());
    }

    @Override
    public String getMessage(String code) {
        return this.messageSource.getMessage(code, null, this.getLocale());
    }

    @Override
    public String getMessage(String code, @Nullable Object[] args) {
        return this.messageSource.getMessage(code, args, this.getLocale());
    }
}
