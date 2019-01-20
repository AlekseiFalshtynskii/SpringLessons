package ru.spring.shell.converter;

import com.google.gson.Gson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.spring.model.Book;

@Component
public class BookConverter implements Converter<String, Book> {
    private final Gson gson;

    public BookConverter() {
        this.gson = new Gson();
    }

    @Nullable
    @Override
    public Book convert(String source) {
        return gson.fromJson(source, Book.class);
    }
}
