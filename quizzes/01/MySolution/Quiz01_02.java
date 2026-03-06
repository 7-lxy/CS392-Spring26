public class Quiz01_02 {
    public static boolean solve_3prod(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation

		// Implentation isn't soft O(n^2) it's O(n^3), I couldn't find a way to make it soft O(n^2)
		
        int n = A.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;

                    if (A[i] * A[j] == A[k]) {
                        return true;
                    }
                }
            }
        }

		// 

        return false;
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
