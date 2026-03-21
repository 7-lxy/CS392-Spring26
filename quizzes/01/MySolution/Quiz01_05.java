//
// HX: 30 points
//
/*
//
 Reverse-stable sorting is similar to stable sorting:
 The ordering of the equals are reversed in the sorted
 version. For instance, 1^1, 2^1, 3^1, 2^2, 3^2, 1^2
 becomes 1^2, 1^1, 2^2, 2^1, 3^2, 3^1 after sorted in
 the reverse-stable manner. If this is unclear to you,
 please seek clarification on Piazza.
//
 No use of external methods (e.g., those from Arrays)
 is allowed here.
//
*/
import MyLibrary.FnList.FnList;
import MyLibrary.Sorting.InsertionSortStable;

import java.util.function.ToIntBiFunction;

abstract public class Quiz01_05 {
    public static<T>
	FnList<T> someSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// This one is abstract, that is, not implemented
		return InsertionSortStable.insertSort(xs, cmp);
    }
    public static<T>
	FnList<T> someRevStableSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// Please implement a reverse-stable sorting method
	// based on someSort
        FnList<Tagged<T>> tagged = new FnList<>();
        int i = 0;
        FnList<T> ys = xs;

        while (ys.consq()) {
            tagged = new FnList<>(new Tagged<>(ys.hd(), i), tagged);
            ys = ys.tl();
            i += 1;
        }

        FnList<Tagged<T>> sorted =
            someSort(
                tagged,
                (a, b) -> {
                    int sgn = cmp.applyAsInt(a.val, b.val);
                    if (sgn != 0) return sgn;

                    // reverse-stable: later element comes first
                    if (a.idx > b.idx) return -1;
                    if (a.idx < b.idx) return 1;
                    return 0;
                }
            );

        FnList<T> res = new FnList<>();
        while (sorted.consq()) {
            res = new FnList<>(sorted.hd().val, res);
            sorted = sorted.tl();
        }

        FnList<T> ans = new FnList<>();
        while (res.consq()) {
            ans = new FnList<>(res.hd(), ans);
            res = res.tl();
        }
        return ans;
    }

    private static class Tagged<T> {
        T val;
        int idx;

        Tagged(T v, int i) {
            val = v;
            idx = i;
        }
    }
}

////////////////////////////////////////////////////////////////////////.
//
// HX-2026-03-04:
//
// Please find a way to test someRevStableSort by
// implementing someSort as insertion-sort on FnList
// and then use someReStableSort to parity-sort the following
// list of 1K integers:
// 0, 1, 2, 3, 4, ..., 999
//
// Your testing code should be inside Quiz01_05_test.java!
//
// Note that you should not add a 'main' method into Quiz01_05
// directly; instead, try to create another class to test Quiz01_05
//
// Note that you should be able to call the insertion sort
// you did (Assign05_01); should not do another implementation of it
//
////////////////////////////////////////////////////////////////////////.
