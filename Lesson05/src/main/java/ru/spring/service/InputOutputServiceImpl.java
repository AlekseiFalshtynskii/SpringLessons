package ru.spring.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService {
    private final Scanner scanner;
    private final PrintStream printStream;

    public InputOutputServiceImpl(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void print(String s) {
        this.printStream.print(s);
    }

    @Override
    public void println(String s) {
        this.printStream.println(s);
    }
}
