package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T target;
    private final int start;
    private final int end;

    public SearchIndex(T[] array, T target, int start, int end) {
        this.array = array;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    public SearchIndex() {
        this.array = null;
        this.target = null;
        this.start = 0;
        this.end = 0;
    }

    @Override
    protected Integer compute() {
        if (this.start - this.end < 10) {
            return simpleSearch();
        }
        int middlePoint = (start + end) / 2;
        var leftSide = new SearchIndex<>(array, target, start, middlePoint);
        var rightSide = new SearchIndex<>(array, target, middlePoint + 1, end);
        leftSide.fork();
        rightSide.fork();
        int leftIndex = leftSide.join();
        int rightIndex = rightSide.join();
        return Math.max(leftIndex, rightIndex);
    }

    public static <T> int search(T[] array, T item) {
        return new ForkJoinPool().invoke(new SearchIndex<>(array, item, 0, array.length - 1));
    }

    private int simpleSearch() {
        int rsl = -1;
        for (int i = start; i <= end; i++) {
            if (array[i].equals(target)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

}