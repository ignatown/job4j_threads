package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class Getter {
    private final File file;

    public Getter(File file) {
        this.file = file;
    }

    public String get(Predicate<Integer> filter) throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder stringBuilder = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test(data)) {
                    stringBuilder.append(data);
                }
            }
            return stringBuilder.toString();
        }
    }

    public String getContent() throws IOException {
        return get(i -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return get(i -> i < 0x80);
    }
}