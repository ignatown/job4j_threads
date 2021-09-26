package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }
    }

    private static CompletableFuture<Sums> work(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            Sums rsl = new Sums();
            for (int i = 0; i < matrix.length; i++) {
                rsl.rowSum += matrix[index][i];
                rsl.colSum += matrix[i][index];
            }
            return rsl;
        });
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rsl[i] == null) {
                    rsl[i] = new Sums();
                }
                if (rsl[j] == null) {
                    rsl[j] = new Sums();
                }
                rsl[j].colSum += matrix[i][j];
                rsl[i].rowSum += matrix[i][j];
            }
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) throws InterruptedException, ExecutionException {
        Map<Integer, CompletableFuture<Sums>> intCfMap = new HashMap<>();
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            intCfMap.put(i, work(matrix, i));
        }
        for (Integer k: intCfMap.keySet()) {
            rsl[k] = intCfMap.get(k).get();
        }
        return rsl;
    }
}