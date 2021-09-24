package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CASCountTest {
    @Test
    public void whenIncrement() {
        CASCount test = new CASCount();
        test.increment();
        assertEquals(1, test.get());
    }

    @Test
    public void when2ThreadIncrementAndGet() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread thread = new Thread(() -> {
            casCount.increment();
            casCount.increment();
        });
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(4, casCount.get());
    }
}