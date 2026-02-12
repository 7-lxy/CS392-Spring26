public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?
		int n = A.length;
		for (int k = 0; k < n; k++) {
			int target = A[k];
			int left = 0;
			int right = n - 1;
			while (left < right) {
				if (left == k) {
					left++;
					continue;
				}
				if (right == k) {
					right--;
					continue;
				}
				int sum = A[left] + A[right];
				if (sum == target) {
					return true;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return false;

		// implementation is soft O(n^2) because we have an outer loop iterating through n elements (line 9)
		// and then a innter while loop that loops through all n elements in the worst case (line 13)
		// resulting in O(n*n) = O(n^2)
    }
    public static void main(String[] argv) { // added 'void' key word to allow execution
	// Please write some code here for testing solve_3sum
		Integer[] arr1 = {1, 2, 3, 4, 5};
		Integer[] arr2 = {1, 2, 4, 7, 10};
		System.out.println("Test case 1: " + solve_3sum(arr1)); // Expected output: true (1+2=3)
		System.out.println("Test case 2: " + solve_3sum(arr2)); // Expected output: false
    }
}
