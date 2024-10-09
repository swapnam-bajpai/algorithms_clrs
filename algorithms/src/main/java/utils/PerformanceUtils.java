package utils;

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
}
