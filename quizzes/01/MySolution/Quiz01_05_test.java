//
// HX: For testing Quiz01_05
//

import java.util.function.ToIntBiFunction;
import MyLibrary.FnList.FnList;

abstract public class Quiz01_05_test {

    private static FnList<Integer> makeRange(int n) {
        FnList<Integer> xs = new FnList<>();
        for (int i = n - 1; i >= 0; i--) {
            xs = new FnList<>(i, xs);
        }
        return xs;
    }

    private static <T> void printFirstN(FnList<T> xs, int n) {
        int i = 0;
        while (xs.consq() && i < n) {
            System.out.print(xs.hd());
            xs = xs.tl();
            i += 1;
            if (xs.consq() && i < n) System.out.print(", ");
        }
        System.out.println();
    }

    private static <T> void printLastN(FnList<T> xs, int n) {
        int len = xs.length();
        int skip = len - n;
        if (skip < 0) skip = 0;

        int i = 0;
        while (xs.consq() && i < skip) {
            xs = xs.tl();
            i += 1;
        }

        printFirstN(xs, n);
    }
    public static void main (String args[]) {
	// Your testing code for Quiz01_05
        FnList<Integer> xs = makeRange(1000);

        ToIntBiFunction<Integer,Integer> parityCmp =
            (x1, x2) -> Integer.compare(x1 % 2, x2 % 2);

        FnList<Integer> ys = Quiz01_05.someRevStableSort(xs, parityCmp);

        System.out.println("original length = " + xs.length());
        System.out.println("sorted length   = " + ys.length());

        System.out.print("first 20: ");
        printFirstN(ys, 20);

        System.out.print("last 20: ");
        printLastN(ys, 20);
    }
}
