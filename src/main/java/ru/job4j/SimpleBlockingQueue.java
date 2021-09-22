package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final static int LIMIT = 10;

    public synchronized void offer(T value) throws InterruptedException {
            while (queue.size() == LIMIT) {
                wait();
            }
            queue.offer(value);
            notify();
    }

    public synchronized T poll() throws InterruptedException {
            while (queue.size() == 0) {
                wait();
            }
            notify();
            return queue.poll();
    }

    public boolean isEmpty() {
        if (queue.size() == 0) {
            return true;
        }
        return false;
    }
}