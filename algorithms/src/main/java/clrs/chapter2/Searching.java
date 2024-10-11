package clrs.chapter2;

public class Searching {
    /**
     * Given a sorted array and a target value, return the first index of the target value in the array. <br>
     * In case the value is not present, return -1
     */
    public int binarySearch(int[] input, int target) {
        return binarySearchFirstPresent(input, target, 0, input.length - 1);
    }

    private int binarySearchFirstPresent(int[] input, int target, int lo, int hi) {
        while (lo < hi) {
            //avoid overflow with (lo + hi) / 2
            int mid = lo + (hi - lo) / 2; //lo <= mid < hi
            if (input[mid] == target) { //recurse in left half, but keep mid - might be the first occurrence
                hi = mid;
            } else {    //recurse in right half, but exclude mid
                lo = mid + 1;
            }
        }
        return input[lo] == target ? lo : -1;
    }

    private int binarySearchLastAbsent(int[] input, int target, int lo, int hi) {
        while (lo < hi) {
            //for (absent, present), move mid to present, so hi = mid - 1 = lo can go to absent and terminate
            int mid = lo + (hi - lo + 1) / 2; //lo < mid <= hi
            if (input[mid] == target) { //recurse in left half, but exclude mid
                hi = mid - 1;
            } else {    //recurse in right half, but keep mid - might be the last non occurrence
                lo = mid;
            }
        }
        return input[lo] == target ? -1 : lo;
    }
}
