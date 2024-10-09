import chapter2.Sorting;
import utils.ArrayUtils;

public class Runner {
    public static void main(String[] args) {
        ArrayUtils.printArray(Sorting.mergeSort(ArrayUtils.getRandomArray(20)));
    }
}
