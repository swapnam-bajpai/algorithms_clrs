package utils;

public final class ArrayUtils {
    /**
     * Given a length, return an array of random integers in the range 1-100.
     */
    public static int[] getRandomArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 100);
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
