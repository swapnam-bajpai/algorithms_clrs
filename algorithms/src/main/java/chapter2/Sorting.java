package chapter2;

import utils.ArrayUtils;
import utils.PerformanceUtils;

import java.util.Arrays;
import java.util.function.Function;

public class Sorting {

    public static void printSortingPerformance(Function<int[], int[]> algorithm) {
        PerformanceUtils.printPerformance(() -> algorithm.apply(ArrayUtils.getRandomArray(50_000)), 50_000, 100);
    }

    /**
     * Given a sequence of n values, returns a new sorted sequence using insertion sort. <br>
     * Results for data size 100_000 and 100 iterations:
     * Min execution time: 687 ms
     * Max execution time: 1027 ms
     * Average execution time: 757.0 ms
     */
    public static int[] insertionSort(int[] input) {
        int len = input.length;
        var result = Arrays.copyOf(input, len);
        int key;

        for (int idx = 1; idx < len; idx++) {
            key = result[idx];
            int prev = idx - 1;
            //Shift the left subarray in 1:idx-1 forward until correct position for key is found
            while (prev >= 0 && result[prev] > key) {
                result[prev + 1] = result[prev];
                prev = prev - 1;
            }
            result[prev + 1] = key; //insert key at correct position, now 1:idx is sorted
        }

        return result;
    }
}
