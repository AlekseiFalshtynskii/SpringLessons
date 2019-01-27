package ru.spring.shell.converter;

import com.google.gson.Gson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.spring.model.Author;

@Component
public class AuthorConverter implements Converter<String, Author> {
    private final Gson gson;

    public AuthorConverter() {
        this.gson = new Gson();
    }

    @Nullable
    @Override
    public Author convert(String source) {
        return gson.fromJson(source, Author.class);
    }
}
