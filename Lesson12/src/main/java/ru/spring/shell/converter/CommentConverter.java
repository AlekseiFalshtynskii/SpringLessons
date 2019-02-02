package ru.spring.shell.converter;

import com.google.gson.Gson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.spring.model.Comment;

@Component
public class CommentConverter implements Converter<String, Comment> {
    private final Gson gson;

    public CommentConverter() {
        this.gson = new Gson();
    }

    @Nullable
    @Override
    public Comment convert(String source) {
        return gson.fromJson(source, Comment.class);
    }
}
