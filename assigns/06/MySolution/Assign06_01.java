/*
 *  Array-based Quicksort
 */
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {
    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T,T> cmp) { // added <T> parameter
	// Please implement standard array-based quickSort and make sure
	// that equal elements are properly handled. In particular, your
	// testing code should test your implementation on an array of 1M zeros!
        quickSort_arrayseg_rand(A, 0, A.length-1, cmp, new Random());
    }

    private static<T> void arrayseg_swap(T[] xs, int i, int j) {
	    T tmp = xs[i]; xs[i] = xs[j]; xs[j] = tmp;
    }

    private static<T> int[] arrayseg_pivot(T[] xs, int ia, int iz, ToIntBiFunction<T,T> cmp) {
        int lo = ia, hi = iz, i = ia;
        T pvt = xs[lo];

        while (i <= hi) {
            int c = cmp.applyAsInt(xs[i], pvt);

            if (c < 0) {
                arrayseg_swap(xs, lo, i);
                lo++; i++;
            } else if (c > 0) {
                arrayseg_swap(xs, i, hi);
                hi--;
            } else {
                i++;
            }
        }

        return new int[]{lo, hi};
    }

    private static<T> void quickSort_arrayseg_rand(T[] xs, int ia, int iz, ToIntBiFunction<T,T> cmp, Random rand) {
        int ln = iz-ia+1;
        if (ln <= 1) return;

        int pvt = rand.nextInt(ln);
        assert(0 <= pvt && pvt <= ln-1);

        arrayseg_swap(xs, ia+pvt, ia);

        int[] im = arrayseg_pivot(xs, ia, iz, cmp);

        quickSort_arrayseg_rand(xs, ia, im[0]-1, cmp, rand);
        quickSort_arrayseg_rand(xs, im[1]+1, iz, cmp, rand);
    }

	public static void main(String[] args) { // added for testing
		int n = 1_000_000;
		Integer[] A = new Integer[n];
		for (int i = 0; i < n; i++) A[i] = 0;

		arrayQuickSort(A, (x1, x2) -> x1.compareTo(x2));
		//for (Integer x : A) System.out.print(x + " ");
		//System.out.println();
		
		// other testing
		Integer[] B = {5, 3, 8, 1, 2, 7, 4, 6};
		arrayQuickSort(B, (x1, x2) -> x1.compareTo(x2));
		for (Integer x : B) System.out.print(x + " ");
		System.out.println();

		Integer[] C = {5, 3, 8, 1, 2, 7, 4, 6, 5, 3, 8, 1, 2, 7, 4, 6};
		arrayQuickSort(C, (x1, x2) -> x1.compareTo(x2));
		for (Integer x : C) System.out.print(x + " ");
		System.out.println();

		Integer[] D = {1, 2, 3, 4, 5, 6, 7, 8};
		arrayQuickSort(D, (x1, x2) -> x1.compareTo(x2));
		for (Integer x : D) System.out.print(x + " ");
		System.out.println();

		Integer[] E = {8, 7, 6, 5, 4, 3, 2, 1};
		arrayQuickSort(E, (x1, x2) -> x1.compareTo(x2));
		for (Integer x : E) System.out.print(x + " ");
		System.out.println();
	}

} // end of [public class Assign06_01{...}]
