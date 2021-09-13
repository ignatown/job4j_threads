package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] process = {"/", "\\", "|"};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (i == 3) {
                    i = 0;
                }
                System.out.print("\r load: " + process[i++]);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}