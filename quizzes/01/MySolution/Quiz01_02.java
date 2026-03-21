public class Quiz01_02 {
    public static boolean solve_3prod(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation

		// My implmentation is soft O(n^2) because the merge sort takes O(n log n) and the nested loops which utilizes binary search 
        // takes O(log n). Therefore the total time is O(n^2 log n) which is soft O(n^2)
		
        int n = A.length;
        if (n < 3) return false;

        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            B[i] = A[i];
        }

        mergeSort(B, 0, n - 1);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int prod = B[i] * B[j];

                int k = binarySearch(B, prod);
                if (k == -1) continue;

                if (k != i && k != j) return true;

                int left = k - 1;
                while (left >= 0 && B[left] == prod) {
                    if (left != i && left != j) return true;
                    left--;
                }

                int right = k + 1;
                while (right < n && B[right] == prod) {
                    if (right != i && right != j) return true;
                    right++;
                }
            }
        }

        return false;
    }

    private static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }

        return -1;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int t = 0; t < temp.length; t++) {
            arr[left + t] = temp[t];
        }
    }

    public static void main(String[] argv) { // added keyword void
	// Please write some code here for testing solve_3prod
        Integer[] test1 = {2, 3, 6};
        Integer[] test2 = {1, 4, 7, 9};
        Integer[] test3 = {0, 5, 0};
        Integer[] test4 = {2, 2, 4};
        Integer[] test5 = {2, 2, 2};
        Integer[] test6 = {-2, 3, -6};

        System.out.println(solve_3prod(test1)); // true
        System.out.println(solve_3prod(test2)); // false
        System.out.println(solve_3prod(test3)); // true
        System.out.println(solve_3prod(test4)); // true
        System.out.println(solve_3prod(test5)); // false
        System.out.println(solve_3prod(test6)); // true
    }
}
