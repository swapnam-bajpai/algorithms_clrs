package clrs.chapter2;

import clrs.utils.ArrayUtils;
import clrs.utils.Utils;

import java.util.Arrays;

public class Sorting {
    /**
     * Given a sequence of integers, returns a new sorted sequence using bubble sort. <br>
     * Terrible for random input <br>
     * Bad even for sorted data, though small sizes are tolerable <br>
     * Unsorted marginally worse than insertion sort <br>
     * <br>
     * Results for data size 10_000 : <br>
     * Min execution time: 90 ms, 16, 33 <br>
     * Max execution time: 121 ms, 37, 50 <br>
     * Average execution time: 96.07 ms, 20.7, 40.2 <br>
     * <br>
     * Results for data size 100_000 : <br>
     * Min execution time: 12160 ms, 1033, 2858 <br>
     * Max execution time: 15444 ms, 2088, 3806 <br>
     * Average execution time: 13373.4 ms, 1279.4, 3346.2 <br>
     */
    public static int[] bubbleSort(int[] input) {
        var result = Arrays.copyOf(input, input.length);

        for (int idx = 0; idx < result.length - 1; idx++) {
            for (int j = result.length - 1; j > idx; j--) {
                if (result[j] < result[j - 1]) { //bubble down smallest element in idx:len-1 to idx
                    Utils.swapIndices(result, j, j - 1);
                }
            }
        }

        ArrayUtils.assertSorted(result);
        return result;
    }

    /**
     * Given a sequence of integers, returns a new sorted sequence using insertion sort. <br>
     * Best for sorted data, regardless of size <br>
     * Decent for random data of small sizes <br>
     * Unsorted marginally better than bubble sort <br>
     * <br>
     * Results for data size 10_000 : <br>
     * Min execution time: 7 ms, 0, 18 <br>
     * Max execution time: 18 ms, 2, 34 <br>
     * Average execution time: 9.16 ms, 0.6, 25.9 <br>
     * <br>
     * Results for data size 100_000 : <br>
     * Min execution time: 687 ms, 1, 1849 <br>
     * Max execution time: 1027 ms, 8, 2321 <br>
     * Average execution time: 757.0, 2.5, 2017.2 ms <br>
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
     * Great performance for random data across sizes <br>
     * Sorted/unsorted are almost same and somewhat faster than random. Consistent in general. <br>
     * <br>
     * Results for data size 10_000 : <br>
     * Min execution time: 1 ms, 2, 1 <br>
     * Max execution time: 5 ms, 6, 4 <br>
     * Average execution time: 1.57 ms, 3.2, 2.1 <br>
     * <br>
     * Results for data size 100_000 : <br>
     * Min execution time: 10 ms, 5, 5 <br>
     * Max execution time: 27 ms, 20, 20 <br>
     * Average execution time: 13.42 ms, 8.22, 8.32 <br>
     * <br>
     * Results for data size 10_000_000 : <br>
     * Min execution time: 1320 ms, 515, 623 <br>
     * Max execution time: 1746 ms, 874, 1177 <br>
     * Average execution time: 1418.8 ms, 591, 679.68 <br>
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
