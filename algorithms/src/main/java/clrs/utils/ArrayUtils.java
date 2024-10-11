package clrs.utils;

public final class ArrayUtils {
    /**
     * Given a length, return an array of random integers of size length in the range 1-100
     */
    public static int[] getRandomArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    /**
     * Given a length, return an array of sorted integers from 1->length shifted by a random integer in 1-100
     */
    public static int[] getSortedArray(int length) {
        int randomStart = (int) (Math.random() * 100);
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = randomStart + i;
        }
        return array;
    }

    /**
     * Given a length, return an array of reverse sorted integers from length->1 shifted by a random integer in 1-100
     */
    public static int[] getReverseSortedArray(int length) {
        int randomStart = (int) (Math.random() * 100);
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = randomStart + length - i;
        }
        return array;
    }

    public static void printArray(int[] toPrint) {
        for (int j : toPrint) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void assertSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new AssertionError("Array is not sorted");
            }
        }
    }
}
