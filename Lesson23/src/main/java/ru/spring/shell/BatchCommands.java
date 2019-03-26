package ru.spring.shell;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.spring.batch.MigrationDbJobLauncher;

@ShellComponent
public class BatchCommands {

    @Autowired
    MigrationDbJobLauncher launcher;

    @ShellMethod("Run migration db task")
    public void run() throws JobExecutionException {
        launcher.launchMigrationDbJob();
    }
}
