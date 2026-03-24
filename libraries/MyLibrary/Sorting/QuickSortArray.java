package MyLibrary.Sorting;

import java.util.Random;
import java.util.function.ToIntBiFunction;

class QuickSortArray {
    public static <T extends Comparable<T>> void quickSort_array(T[] xs) {
        quickSort_array(xs, (x1, x2) -> x1.compareTo(x2));
        return;
    }

    public static<T> void quickSort_array(T[] xs, ToIntBiFunction<T,T> cmp) {
        quickSort_arrayseg_rand(xs, 0, xs.length-1, cmp, new Random());
        return;
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
}

