package ru.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.spring.model.Comment;
import ru.spring.service.CommentService;

@ShellComponent
public class CommentCommands {
    private final CommentService commentService;

    public CommentCommands(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod("Save comment")
    public String saveComment(@ShellOption Comment comment) {
        commentService.save(comment);
        return "\nSaved " + comment;
    }

    @ShellMethod("Find comment by id")
    public String findByIdComment(@ShellOption Long id) {
        Comment comment = commentService.findById(id);
        return comment == null ? "Comment not found by id = " + id : "Finded " + comment.toString();
    }

    @ShellMethod("Find all comments")
    public String findAllComment() {
        return commentService.findAll().toString();
    }

    @ShellMethod("Get count of comments")
    public long countComment() {
        return commentService.count();
    }

    @ShellMethod("Delete comment by id")
    public String deleteByIdComment(Long id) {
        commentService.deleteById(id);
        return "Deleted comment by id = " + id;
    }

    @ShellMethod("Delete all comments")
    public String deleteAllComment() {
        commentService.deleteAll();
        return "Deleted all comments";
    }
}
