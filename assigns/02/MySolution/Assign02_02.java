import java.util.Arrays;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
	// Please give a recursive implementation of 'indexOf' that is
	// equivalent to the above one
        return search(a, key, 0, a.length - 1);
    }

    private static <T extends Comparable<T> > int search(T[] a, T key, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(a[mid]);
        if (cmp == 0) {
            return mid;
        } else if (cmp < 0) {
            return search(a, key, lo, mid - 1);
        } else {
            return search(a, key, mid + 1, hi);
        }

    }

    public static void main(String[] argv) { // added 'static' key word to allow execution
	// Please write some testing code for your implementation of 'indexOf'
        Integer[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int key1 = 7;
        int key2 = 4;

        int index1 = indexOf(arr, key1);
        int index2 = indexOf(arr, key2);

        System.out.println("Index of " + key1 + ": " + index1); // Expected output: Index of 7: 3
        System.out.println("Index of " + key2 + ": " + index2); // Expected output: Index of 4: -1
    }
}
