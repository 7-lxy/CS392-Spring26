/*
// HX: 50 points for Final_05
// HX: This one tests your priority queue implementation
*/


import MyLibrary.LnList.LnList;
import MyLibrary.LnList.LnListSUtil;
import MyLibrary.FnList.FnList;

import java.util.function.ToIntBiFunction;

public class Final_05 {
    private static final int NMERGE = 100;

    private static class PQItem<T> {
        LnList<T> list;
        int source;

        PQItem(LnList<T> list, int source) {
            this.list = list;
            this.source = source;
        }
    }

    private static class MinPQueue<T> {
        PQItem<T>[] heap;
        int size;
        ToIntBiFunction<T,T> cmp;

        MinPQueue(int cap, ToIntBiFunction<T,T> cmp) {
            heap = (PQItem<T>[]) new PQItem[cap + 1];
            size = 0;
            this.cmp = cmp;
        }

        boolean isEmpty() {
            return size == 0;
        }

        private int compare(PQItem<T> x, PQItem<T> y) {
            int sgn = cmp.applyAsInt(x.list.hd1(), y.list.hd1());

            if (sgn != 0) return sgn;

            if (x.source < y.source) return -1;
            if (x.source > y.source) return 1;
            return 0;
        }

        void insert(PQItem<T> item) {
            size += 1;
            heap[size] = item;

            int i = size;
            while (i > 1) {
                int p = i / 2;

                if (compare(heap[p], heap[i]) <= 0) break;

                PQItem<T> tmp = heap[p];
                heap[p] = heap[i];
                heap[i] = tmp;

                i = p;
            }
        }

        PQItem<T> deleteMin() {
            PQItem<T> res = heap[1];

            heap[1] = heap[size];
            heap[size] = null;
            size -= 1;

            int i = 1;

            while (true) {
                int left = 2 * i;
                int right = left + 1;
                int smallest = i;

                if (left <= size && compare(heap[left], heap[smallest]) < 0) {
                    smallest = left;
                }

                if (right <= size && compare(heap[right], heap[smallest]) < 0) {
                    smallest = right;
                }

                if (smallest == i) break;

                PQItem<T> tmp = heap[i];
                heap[i] = heap[smallest];
                heap[smallest] = tmp;

                i = smallest;
            }

            return res;
        }
    }

    public static<T> LnList<T>
	LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
	// HX: Given an array of (linear) lists (LnList), each of which is
	// ordered according to cmp, please implement a function to merge them
	// into one ordered (linear) list. Please note that you cannot create
	// new list nodes; you can only use existing nodes to form the returned
	// linear list. You are asked to use MyPQueueArray.java implemented in
	// Assigment#9 for finding the minimum of a collection of nodes.
        MinPQueue<T> pq = new MinPQueue<T>(xss.length + 5, cmp);

        for (int i = 0; i < xss.length; i += 1) {
            if (xss[i] != null && xss[i].consq1()) {
                pq.insert(new PQItem<T>(xss[i], i));
            }
        }

        LnList<T> result = new LnList<T>();
        LnList<T> tail = null;

        while (!pq.isEmpty()) {
            PQItem<T> item = pq.deleteMin();

            LnList<T> one = item.list;
            LnList<T> rest = one.unlink1();

            if (result.nilq1()) {
                result = one;
                tail = one;
            } else {
                tail.link1(one);
                tail = one;
            }

            xss[item.source] = rest;

            if (rest.consq1()) {
                pq.insert(new PQItem<T>(rest, item.source));
            }
        }

        return result;
    }

    private static<T> LnList<T>
    takePrefix(LnList<T>[] holder, int k) {
        LnList<T> xs = holder[0];

        LnList<T> result = new LnList<T>();
        LnList<T> tail = null;

        for (int i = 0; i < k && xs.consq1(); i += 1) {
            LnList<T> one = xs;
            LnList<T> rest = one.unlink1();

            if (result.nilq1()) {
                result = one;
                tail = one;
            } else {
                tail.link1(one);
                tail = one;
            }

            xs = rest;
        }

        holder[0] = xs;
        return result;
    }

    public static<T>
	LnList<T>
	LnList_mergeSort$100way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX: Please use LnList_n$way$merge to implement 100-way mergesort
	// on a linear list. That is, split each list evenly into 100 sublists;
	// recursely sort the 100 sublist and then use LnList_n$way$merge to merge
	// them into one sorted list.
	// Please make sure that your implementation of LnList_mergeSort$100way
	// does stable sorting!
        if (xs == null || xs.nilq1() || xs.length1() <= 1) {
            return xs;
        }

        int n = xs.length1();

        LnList<T>[] parts = (LnList<T>[]) new LnList[NMERGE];

        int base = n / NMERGE;
        int rem = n % NMERGE;

        LnList<T>[] holder = (LnList<T>[]) new LnList[1];
        holder[0] = xs;

        for (int i = 0; i < NMERGE; i += 1) {
            int k = base + (i < rem ? 1 : 0);

            if (k > 0) {
                parts[i] = takePrefix(holder, k);
                parts[i] = LnList_mergeSort$100way(parts[i], cmp);
            } else {
                parts[i] = new LnList<T>();
            }
        }

        return LnList_n$way$merge(parts, cmp);
    }

    private static void printFirst(LnList<Integer> xs, int n) {
        int i = 0;

        while (xs.consq1() && i < n) {
            System.out.print(xs.hd1());

            if (i + 1 < n) {
                System.out.print(", ");
            }

            xs = xs.tl1();
            i += 1;
        }

        System.out.println();
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to parity-sort the list [0,1,2,...,999998,999999]
	// of 1000000 elements.
        int n = 1_000_000;

        LnList<Integer> xs = new LnList<Integer>();

        for (int i = n - 1; i >= 0; i -= 1) {
            xs = new LnList<Integer>(i, xs);
        }

        LnList<Integer> ys =
            LnList_mergeSort$100way(
                xs,
                (x, y) -> {
                    int px = x % 2;
                    int py = y % 2;

                    if (px < py) return -1;
                    if (px > py) return 1;

                    return x.compareTo(y);
                }
            );

        printFirst(ys, 30);
    }
}
