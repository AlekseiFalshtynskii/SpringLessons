package ru.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.spring.csv.CSVReader;
import ru.spring.quiz.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

@Service
public class I18nServiceImpl implements I18nService {
    private final Environment env;
    private final MessageSource messageSource;
    private final CSVReader csvReader;

    public I18nServiceImpl(Environment environment, MessageSource messageSource, CSVReader csvReader) {
        this.env = environment;
        this.messageSource = messageSource;
        this.csvReader = csvReader;
        this.setLocale(this.env.getProperty("quiz.locale"));
    }

    public void setLocale(String locale) {
        LocaleContextHolder.setDefaultLocale(new Locale(locale));
    }

    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        return this.csvReader.readQuestions(this.env.getProperty(format("quiz.questions.%s", this.getLocale().getLanguage())));
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
