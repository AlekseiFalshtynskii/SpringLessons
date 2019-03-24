package ru.spring.batch;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.Files.delete;

@AllArgsConstructor
@Log4j2
public class MigrationPathDeletingTasklet implements Tasklet {

    private Resource directory;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        try (Stream<Path> walk = Files.walk(Paths.get(directory.getFile().getPath()))) {
            walk.filter(Files::isRegularFile).map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            log.error("Error deleting migration path", e);
            throw new UnexpectedJobExecutionException("Unable to delete files");
        }
        try {
            delete(directory.getFile().toPath());
        } catch (IOException e) {
            log.error("Error deleting migration path", e);
            throw new UnexpectedJobExecutionException("Unable to delete files");
        }
        return RepeatStatus.FINISHED;
    }
}
