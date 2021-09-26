package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool() {
        newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public synchronized void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private void newFixedThreadPool(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            Thread newThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted() || !tasks.isEmpty()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            newThread.start();
            threads.add(newThread);
        }
    }
}
