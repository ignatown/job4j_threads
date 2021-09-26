package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RowColSumTest {

    @Test
    public void syncSum() {
        int[][] ints = {
                {6, 7, 5},
                {4, 3, 2},
                {1, 9, 0}
        };
        RowColSum.Sums[] sums = RowColSum.sum(ints);
        assertEquals(11, sums[0].getColSum());
        assertEquals(18, sums[0].getRowSum());
        assertEquals(19, sums[1].getColSum());
        assertEquals(9, sums[1].getRowSum());
        assertEquals(7, sums[2].getColSum());
        assertEquals(10, sums[2].getRowSum());
    }

    @Test
    public void asyncSum() throws InterruptedException, ExecutionException {
        int[][] ints = {
                {5, 2, 1},
                {6, 4, 2},
                {0, 15, 2}
        };
        RowColSum.Sums[] sums = RowColSum.asyncSum(ints);
        assertEquals(11, sums[0].getColSum());
        assertEquals(8, sums[0].getRowSum());
        assertEquals(21, sums[1].getColSum());
        assertEquals(12, sums[1].getRowSum());
        assertEquals(5, sums[2].getColSum());
        assertEquals(17, sums[2].getRowSum());
    }

}