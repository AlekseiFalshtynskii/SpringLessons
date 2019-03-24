package ru.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MigrationDbJobLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(MigrationDbJobLauncher.class);

    private final Job job;

    private final JobLauncher jobLauncher;

    MigrationDbJobLauncher(Job migrationDbJob, JobLauncher jobLauncher) {
        this.job = migrationDbJob;
        this.jobLauncher = jobLauncher;
    }

    public void launchMigrationDbJob() throws JobExecutionException {
        LOGGER.info("Starting migrationDbJob");

        jobLauncher.run(job, newExecution());

        LOGGER.info("Stopping migrationDbJob");
    }

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();

        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);

        return new JobParameters(parameters);
    }
}
