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
            String output = "";
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test(data)) {
                    output += (char) data;
                }
            }
            return output;
        }
    }

    public String getContent() throws IOException {
        return get(i -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return get(i -> i < 0x80);
    }
}