package ru.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class InputOutputServiceImplTest {
    private static final String TEST = "Какая-то тестовая строка";

    @Configuration
    static class Config {
        @Bean
        public InputStream inputStream() {
            return new ByteArrayInputStream(TEST.getBytes());
        }

        @Bean
        public PrintStream printStream() {
            return new PrintStream(outputStream());
        }

        @Bean
        public OutputStream outputStream() {
            return new ByteArrayOutputStream();
        }

        @Bean
        public InputOutputService inputOutputService() {
            return new InputOutputServiceImpl(inputStream(), printStream());
        }
    }

    @Autowired
    private OutputStream outputStream;
    @Autowired
    private InputOutputService inputOutputService;

    @Before
    public void before() throws Exception {
        ((ByteArrayOutputStream) this.outputStream).reset();
    }

    @Test
    public void nextLine() throws Exception {
        String line = this.inputOutputService.nextLine();
        assertEquals(TEST, line);
    }

    @Test
    public void print() throws Exception {
        this.inputOutputService.print(TEST);
        String out = this.outputStream.toString();
        assertEquals(TEST, out);
    }

    @Test
    public void println() throws Exception {
        this.inputOutputService.println(TEST);
        String out = this.outputStream.toString();
        assertEquals(TEST + "\n", out);
    }
}