package ru.spring.shell.converter;

import com.google.gson.Gson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.spring.model.Genre;

@Component
public class GenreConverter implements Converter<String, Genre> {
    private final Gson gson;

    public GenreConverter() {
        this.gson = new Gson();
    }

    @Nullable
    @Override
    public Genre convert(String source) {
        return gson.fromJson(source, Genre.class);
    }
}
