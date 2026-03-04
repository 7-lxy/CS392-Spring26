package MyLibrary.Sorting;

import java.util.function.ToIntBiFunction;
import MyLibrary.FnList.*;

public class InsertionSortStable {
	// Only works for nearly-sorted lists linearly
    public static<T> FnList<T> insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        FnList<T> rev = new FnList<>();

        while (xs.consq()) {
            rev = new FnList<>(xs.hd(), rev);
            xs = xs.tl();
        }

        FnList<T> sorted = new FnList<>();

        while (rev.consq()) {
            T x = rev.hd();
            rev = rev.tl();

            FnList<T> prefixRev = new FnList<>();
            FnList<T> rest = sorted;

            while (rest.consq() && cmp.applyAsInt(rest.hd(), x) < 0) {
                prefixRev = new FnList<>(rest.hd(), prefixRev);
                rest = rest.tl();
            }

            FnList<T> ys = new FnList<>(x, rest);

            while (prefixRev.consq()) {
                ys = new FnList<>(prefixRev.hd(), ys);
                prefixRev = prefixRev.tl();
            }

            sorted = ys;
        }

        return sorted;
    }

    public static<T> void printList(FnList<T> list) {
        while (list.consq()) {
            System.out.print(list.hd() + " ");
            list = list.tl();
        }
        System.out.println();
    }
}
