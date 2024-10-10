package utils;

public final class Utils {
    public static void swapIndices(int[] input, int first, int second) {
        int temp = input[first];
        input[first] = input[second];
        input[second] = temp;
    }
}
