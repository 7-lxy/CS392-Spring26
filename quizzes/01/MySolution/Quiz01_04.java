//
// HX: 50 points
//

public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement (stable) insertion sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class; you cannot use any constructors
	// in LnList
        if (xs.nilq1() || xs.tl1().nilq1()) {
            return xs;
        }

        LnList<T> rest = xs.unlink();
        LnList<T> sorted = xs;

        while (rest.consq1()) {
            LnList<T> next = rest.unlink();
            LnList<T> cur = rest;
            rest = next;

            T x = cur.hd1();

            if (x.compareTo(sorted.hd1()) < 0) {
                cur.link(sorted);
                sorted = cur;
            } else {
                LnList<T> p = sorted;
                while (p.tl1().consq1()
                       && p.tl1().hd1().compareTo(x) <= 0) {
                    p = p.tl1();
                }

                LnList<T> tail = p.unlink();
                p.link(cur);
                cur.link(tail);
            }
        }

        return sorted;
    }

    private static class ParityInt implements Comparable<ParityInt> {
        int value;
        int id;

        ParityInt(int value, int id) {
            this.value = value;
            this.id = id;
        }

        public int compareTo(ParityInt other) {
            return Integer.compare(this.value % 2, other.value % 2);
        }

        public String toString() {
            return "(" + value + "," + id + ")";
        }
    }

    private static <T> void printFirstN(LnList<T> xs, int n) {
        final int[] cnt = {0};
        xs.foritm1(x -> {
            if (cnt[0] < n) {
                System.out.print(x + " ");
                cnt[0] += 1;
            }
        });
        System.out.println();
    }

    private static boolean checkParityStability(LnList<ParityInt> xs) {
        final int[] lastEvenId = {-1};
        final int[] lastOddId = {-1};
        final boolean[] ok = {true};

        xs.foritm1(x -> {
            if (x.value % 2 == 0) {
                if (x.id < lastEvenId[0]) ok[0] = false;
                lastEvenId[0] = x.id;
            } else {
                if (x.id < lastOddId[0]) ok[0] = false;
                lastOddId[0] = x.id;
            }
        });
        return ok[0];
    }
		
    public static void main (String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.
	// Please write minimal testing code for LnListInsertSort
	// 1. Please sort a nearly sorted list of 1M elements
	// 2. Please do parity-sorting to test that LnListInsertSort is stable
        int n = 1_000_000;

        LnList<Integer> xs = new LnList<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            xs = new LnList<Integer>(i, xs);
        }

        LnList<Integer> a = xs;
        LnList<Integer> b = xs.tl1();
        LnList<Integer> rest = b.unlink();
        a.unlink();
        b.link(a);
        a.link(rest);
        xs = b;

        long t1 = System.currentTimeMillis();
        xs = LnListInsertSort(xs);
        long t2 = System.currentTimeMillis();

        System.out.println("Sorted 1,000,000 nearly sorted elements.");
        System.out.println("Elapsed ms = " + (t2 - t1));
        System.out.print("First 10: ");
        printFirstN(xs, 10);

        LnList<ParityInt> ps = new LnList<ParityInt>();
        ps = new LnList<ParityInt>(new ParityInt(6, 5), ps);
        ps = new LnList<ParityInt>(new ParityInt(7, 4), ps);
        ps = new LnList<ParityInt>(new ParityInt(4, 3), ps);
        ps = new LnList<ParityInt>(new ParityInt(5, 2), ps);
        ps = new LnList<ParityInt>(new ParityInt(2, 1), ps);
        ps = new LnList<ParityInt>(new ParityInt(3, 0), ps);
        // actual list: (3,0) (2,1) (5,2) (4,3) (7,4) (6,5)

        System.out.print("Before parity-sort: ");
        printFirstN(ps, 20);

        ps = LnListInsertSort(ps);

        System.out.print("After parity-sort: ");
        printFirstN(ps, 20);

        System.out.println("Stable: " + checkParityStability(ps));
    }
}
