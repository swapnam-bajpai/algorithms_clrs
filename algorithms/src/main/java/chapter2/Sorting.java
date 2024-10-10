package chapter2;

import utils.ArrayUtils;
import utils.PerformanceUtils;

import java.util.Arrays;
import java.util.function.Function;

public class Sorting {

    public static void printSortingPerformance(Function<int[], int[]> algorithm) {
        PerformanceUtils.printPerformance(() -> algorithm.apply(ArrayUtils.getRandomArray(100_000)), 100_000, 100);
    }

    /**
     * Given a sequence of integers, returns a new sorted sequence using insertion sort. <br>
     * <p>
     * Results for data size 100_000 and 100 iterations: <br>
     * Min execution time: 687 ms <br>
     * Max execution time: 1027 ms <br>
     * Average execution time: 757.0 ms
     */
    public static int[] insertionSort(int[] input) {
        var result = Arrays.copyOf(input, input.length);

        int key;
        for (int idx = 1; idx < input.length; idx++) {
            key = result[idx];
            int prev = idx - 1;
            //Shift the left subarray in 0:idx-1 forward until correct position for key is found
            while (prev >= 0 && result[prev] > key) {
                result[prev + 1] = result[prev];
                prev = prev - 1;
            }
            result[prev + 1] = key; //insert key at correct position, now 0:idx is sorted
        }

        ArrayUtils.assertSorted(result);
        return result;
    }

    /**
     * Given a sequence of integers, returns a new sorted sequence using merge sort. <br>
     * <p>
     * Results for data size 100_000 and 100 iterations: <br>
     * Min execution time: 11 ms <br>
     * Max execution time: 27 ms <br>
     * Average execution time: 13.51 ms
     */
    public static int[] mergeSort(int[] input) {
        var result = mergeSortInRange(Arrays.copyOf(input, input.length), 0, input.length - 1);
        ArrayUtils.assertSorted(result);
        return result;
    }

    private static int[] mergeSortInRange(int[] input, int start, int end) {
        //base case, can be simplified to start == end if first call guaranteed to be (0, len-1) with len-1 >= 0
        if (start >= end) {
            return input;
        }

        //recursively sort two halves and combine results
        int mid = (start + end) / 2; //start <= mid < end
        mergeSortInRange(input, start, mid);
        mergeSortInRange(input, mid + 1, end);

        return merge(input, start, mid, end);
    }

    private static int[] merge(int[] input, int start, int mid, int end) {
        int leftLen = mid - start + 1, rightLen = end - mid;
        var leftArr = new int[leftLen];
        var rightArr = new int[rightLen];

        System.arraycopy(input, start, leftArr, 0, leftLen); //index start to mid, both inclusive
        System.arraycopy(input, mid + 1, rightArr, 0, rightLen); //index mid+1 to end, both inclusive

        int leftIdx = 0, rightIdx = 0, resultIdx = start;

        while (leftIdx < leftLen && rightIdx < rightLen) { //look at top of both piles and copy smaller
            if (leftArr[leftIdx] <= rightArr[rightIdx]) {
                input[resultIdx++] = leftArr[leftIdx++];
            } else {
                input[resultIdx++] = rightArr[rightIdx++];
            }
        }

        //copy whatever is leftover in either one of the piles
        while (leftIdx < leftLen) {
            input[resultIdx++] = leftArr[leftIdx++];
        }
        while (resultIdx < rightLen) {
            input[resultIdx++] = rightArr[rightIdx++];
        }

        return input;
    }
}
