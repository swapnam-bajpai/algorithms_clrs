import clrs.chapter2.Sorting;
import clrs.utils.PerformanceUtils;

public class Runner {
    public static void main(String[] args) {
//        ArrayUtils.printArray(Sorting.mergeSort(ArrayUtils.getRandomArray(20)));
        PerformanceUtils.printSortingPerformanceSorted(Sorting::mergeSort);
    }
}
