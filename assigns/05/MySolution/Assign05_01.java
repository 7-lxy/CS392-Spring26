import MyLibrary.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T> FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2026-02-26: Please implement this method
	// You can use while-loops but cannot make recursive
	// calls.
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

    public static void main(String[] args) {
	// Please write some testing code that applies
	// insertSort to the following list of 1M numbers:
	// 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998.
    int n = 1_000_000;
    FnList<Integer> xs = new FnList<>();

    for (int i = n - 2; i >= 0; i -= 2) {
        xs = new FnList<>(i + 1, new FnList<>(i, xs));
    }

    System.out.println("finished building list");
    System.out.println(xs.length());

    long t1 = System.currentTimeMillis();
    FnList<Integer> ys = insertSort(xs);
    long t2 = System.currentTimeMillis();

    System.out.println("finished sorting");
    System.out.println(ys.length());
    System.out.println(t2 - t1);
	}
} // end of [public class Assign05_01{...}]
