package ru.job4j.emailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService poll =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private void emailTo(User user) {
        poll.submit(new Runnable() {
            @Override
            public void run() {
                String email = user.getEmail();
                String username = user.getUsername();
                String subject = "Notification " + username + " to email " + email + ".";
                String body = "Add a new event to " + username;
                send(subject, body, email);
            }
        });
    }

    private void close() {
        poll.shutdown();
    }

    public void send(String subject, String body, String email) {

    }
}
