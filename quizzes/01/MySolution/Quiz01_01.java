//
// HX: 20 points
//
/*
import Library00.FnA1sz.*;
*/
import MyLibrary.FnA1sz.*;
public class Quiz01_01 {
    public static
	<T extends Comparable<T>>
	int FnA1szBinarySearch(FnA1sz<T> A, T key) {
	// HX-2026-03-03:
	// Please implement binary search on a sorted functional array (FnA1sz)
	// that returns the largest index i such that key >= A[i] if such i exists,
	// or the method returns -1. The comparison function should be the compareTo
	// method implemented by the class T.
		int low = 0;
		int high = A.length() - 1;
		int ans = -1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			T midVal = A.getAt(mid);
			int cmp = key.compareTo(midVal);

			if (cmp >= 0) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return ans;
    }
    public static void main (String[] args) {
	// HX-2026-03-04:
	// Please write minimal testing code for FnA1szBinarySearch
	// Should test for cases T = Integer and T = String
		FnA1sz<Integer> A = new FnA1sz<>(new Integer[]{1, 3, 3, 5, 8});
		FnA1sz<String> B = new FnA1sz<>(new String[]{"apple", "banana", "banana", "carrot"});

		// Testing with Integer
		System.out.println(FnA1szBinarySearch(A, 3)); // return: 2
		System.out.println(FnA1szBinarySearch(A, 4)); // return: 2
		System.out.println(FnA1szBinarySearch(A, 0)); // return: -1
		System.out.println(FnA1szBinarySearch(A, 20)); // return: 4

		System.out.println();
		// Testing with String
		System.out.println(FnA1szBinarySearch(B, "banana")); // return: 2
		System.out.println(FnA1szBinarySearch(B, "date")); // return: 3
		System.out.println(FnA1szBinarySearch(B, "-")); // return: -1
		System.out.println(FnA1szBinarySearch(B, "z")); // return: 3

		return /*void*/;
    }
}
