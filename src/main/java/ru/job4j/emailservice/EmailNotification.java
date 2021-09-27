package ru.job4j.emailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private void emailTo(User user) {
        pool.submit(new Runnable() {
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
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
