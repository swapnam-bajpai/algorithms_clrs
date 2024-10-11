package clrs.utils;

import java.util.function.Function;

public final class PerformanceUtils {
    public static void printPerformance(Runnable runnable, int datasize, int iterations) {
        long[] times = new long[iterations];

        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();
            runnable.run();
            long endTime = System.currentTimeMillis();
            times[i] = endTime - startTime;
        }

        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        long totalTime = 0;

        for (long time : times) {
            if (time < minTime) {
                minTime = time;
            }
            if (time > maxTime) {
                maxTime = time;
            }
            totalTime += time;
        }

        double averageTime = (double) totalTime / iterations;

        System.out.println("Results for data size " + datasize + " and " + iterations + " iterations:");
        System.out.println("Min execution time: " + minTime + " ms");
        System.out.println("Max execution time: " + maxTime + " ms");
        System.out.println("Average execution time: " + averageTime + " ms");
    }

    /**
     * Sorting performances include time to initialize array, copy to new target array, assert correctness <br>
     * Random, sorted and reverse sorted inputs are considered <br>
     * Performance is averaged across multiple iterations, typically 100, unless individual cost is too large <br>
     */
    public static void printSortingPerformance(Function<int[], int[]> algorithm) {
        int dataSize = 100_000, iterations = 10;
        PerformanceUtils.printPerformance(() -> algorithm.apply(ArrayUtils.getRandomArray(dataSize)), dataSize, iterations);
    }

    public static void printSortingPerformanceSorted(Function<int[], int[]> algorithm) {
        int dataSize = 100_000, iterations = 50;
        PerformanceUtils.printPerformance(() -> algorithm.apply(ArrayUtils.getSortedArray(dataSize)), dataSize, iterations);
    }

    public static void printSortingPerformanceReverseSorted(Function<int[], int[]> algorithm) {
        int dataSize = 10_000_000, iterations = 100;
        PerformanceUtils.printPerformance(() -> algorithm.apply(ArrayUtils.getReverseSortedArray(dataSize)), dataSize, iterations);
    }
}
