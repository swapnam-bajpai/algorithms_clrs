import chapter2.Sorting;

public class Runner {
    public static void main(String[] args) {
//        ArrayUtils.printArray(Sorting.mergeSort(ArrayUtils.getRandomArray(20)));
        Sorting.printSortingPerformanceSorted(Sorting::mergeSort);
    }
}
