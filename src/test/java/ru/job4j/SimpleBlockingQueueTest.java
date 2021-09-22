package ru.job4j;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    @Test
    public void whenProducerConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();
        List<Integer> result = new ArrayList<>();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    simpleBlockingQueue.offer(3);
                    simpleBlockingQueue.offer(2);
                    simpleBlockingQueue.offer(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result.add(simpleBlockingQueue.poll());
                    result.add(simpleBlockingQueue.poll());
                    result.add(simpleBlockingQueue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        List<Integer> expected = List.of(3, 2, 1);
        assertThat(result, is(expected));
    }
}