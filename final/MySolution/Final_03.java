/*
// HX: 50 points for Final_03
// HX: This one tests your hash map implementation
// In Final_02, pg2701_word$count$listize2() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_03, you are asked to implement the same functionality
// with a different approach.
*/


import MyLibrary.FnList.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;
import MyLibrary.MyMap00.*;

import java.util.function.BiConsumer;

// couldn't fix importing issues so i just copied and modified code from assign08_02
class Assign08_02<V> implements MyMap00<String, V> {
    private FnTupl2<String, V>[] table;
    private byte[] state;
    private int size;

    private static final int CAPACITY = 131071;

    public Assign08_02() {
        table = (FnTupl2<String, V>[]) new FnTupl2[CAPACITY];
        state = new byte[CAPACITY];
        size = 0;
    }

    private int hash(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private int findIndex(String key) {
        int h = hash(key);

        for (int j = 0; j < table.length; j += 1) {
            int i = (h + j * j) % table.length;

            if (state[i] == 0) {
                return -1;
            }

            if (state[i] == 1 && table[i].sub0.equals(key)) {
                return i;
            }
        }

        return -1;
    }

    private int findInsertIndex(String key) {
        int h = hash(key);
        int firstDeleted = -1;

        for (int j = 0; j < table.length; j += 1) {
            int i = (h + j * j) % table.length;

            if (state[i] == 1) {
                if (table[i].sub0.equals(key)) {
                    return i;
                }
            } else if (state[i] == 2) {
                if (firstDeleted < 0) {
                    firstDeleted = i;
                }
            } else {
                return firstDeleted >= 0 ? firstDeleted : i;
            }
        }

        return firstDeleted;
    }

    private LnStrm<FnTupl2<String, V>> table_to_strm() {
        FnList<FnTupl2<String, V>> res = FnListSUtil.nil();

        for (int i = 0; i < table.length; i += 1) {
            if (state[i] == 1) {
                res = FnListSUtil.cons(table[i], res);
            }
        }

        return FnListSUtil.strmize(res);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size == table.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public LnStrm<FnTupl2<String, V>> keyval_strmize() {
        return table_to_strm();
    }

    @Override
    public V search$old(String key) {
        return search$exn(key);
    }

    @Override
    public V search$exn(String key) {
        V res = search$opt(key);

        if (res == null) {
            throw new MyMap00NoKeyExn();
        }

        return res;
    }

    @Override
    public V search$opt(String key) {
        int i = findIndex(key);

        if (i < 0) {
            return null;
        }

        return table[i].sub1;
    }

    @Override
    public V insert$opt(String key, V val) {
        int i = findInsertIndex(key);

        if (i < 0) {
            throw new MyMap00FullExn();
        }

        if (state[i] == 1) {
            V old = table[i].sub1;
            table[i].sub1 = val;
            return old;
        }

        table[i] = new FnTupl2<String, V>(key, val);
        state[i] = 1;
        size += 1;

        return null;
    }

    @Override
    public void insert$new(String key, V val) {
        if (search$opt(key) != null) {
            throw new RuntimeException("key already exists");
        }

        insert$opt(key, val);
    }

    @Override
    public V remove$old(String key) {
        return remove$exn(key);
    }

    @Override
    public V remove$exn(String key) {
        V res = remove$opt(key);

        if (res == null) {
            throw new MyMap00NoKeyExn();
        }

        return res;
    }

    @Override
    public V remove$opt(String key) {
        int i = findIndex(key);

        if (i < 0) {
            return null;
        }

        V old = table[i].sub1;
        table[i] = null;
        state[i] = 2;
        size -= 1;

        return old;
    }

    @Override
    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < table.length; i += 1) {
            if (state[i] == 1) {
                work.accept(table[i].sub0, table[i].sub1);
            }
        }
    }
}

public class Final_03 {
    private static int wordCompare(FnList<Character> w1, FnList<Character> w2) {
        FnList<Character> xs = w1;
        FnList<Character> ys = w2;

        while (xs.consq() && ys.consq()) {
            char c1 = xs.hd();
            char c2 = ys.hd();

            if (c1 < c2) return -1;
            if (c1 > c2) return 1;

            xs = xs.tl();
            ys = ys.tl();
        }

        if (xs.nilq() && ys.nilq()) return 0;
        if (xs.nilq()) return -1;
        return 1;
    }

    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();

        while (word.consq()) {
            sb.append(word.hd());
            word = word.tl();
        }

        return sb.toString();
    }

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the hash map implemented in Assign08_02 (open addressing)
	//    to count the number of occurrences of each word in the stream of words
	// 3. Then figure out a way to turn the hash map into a list WNS (FnList) of
	//    word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize3()
        LnStrm<FnList<Character>> words =
            Final_01.pg2701_word$strmize();

        Assign08_02<FnTupl2<FnList<Character>, Integer>> map =
            new Assign08_02<FnTupl2<FnList<Character>, Integer>>();

        while (true) {
            LnStcn<FnList<Character>> cell = words.eval0();

            if (cell.nilq()) {
                break;
            }

            FnList<Character> word = cell.hd();
            String key = wordToString(word);

            FnTupl2<FnList<Character>, Integer> oldPair =
                map.search$opt(key);

            if (oldPair == null) {
                map.insert$opt(
                    key,
                    new FnTupl2<FnList<Character>, Integer>(word, 1)
                );
            } else {
                map.insert$opt(
                    key,
                    new FnTupl2<FnList<Character>, Integer>(
                        oldPair.sub0,
                        oldPair.sub1 + 1
                    )
                );
            }

            words = cell.tl();
        }

        FnList<FnTupl2<FnList<Character>, Integer>> WNS =
            FnListSUtil.nil();

        LnStrm<FnTupl2<String, FnTupl2<FnList<Character>, Integer>>> kvs =
            map.keyval_strmize();

        while (true) {
            LnStcn<FnTupl2<String, FnTupl2<FnList<Character>, Integer>>> cell =
                kvs.eval0();

            if (cell.nilq()) {
                break;
            }

            WNS = FnListSUtil.cons(cell.hd().sub1, WNS);

            kvs = cell.tl();
        }

        return FnListSUtil.mergeSort(
            WNS,
            (p1, p2) -> {
                int n1 = p1.sub1;
                int n2 = p2.sub1;

                if (n1 > n2) return -1;
                if (n1 < n2) return 1;

                return wordCompare(p1.sub0, p2.sub0);
            }
        );
    }

    private static void printWord(FnList<Character> word) {
        while (word.consq()) {
            System.out.print(word.hd());
            word = word.tl();
        }
    }

    private static void printPair(FnTupl2<FnList<Character>, Integer> pair) {
        printWord(pair.sub0);
        System.out.print(" ");
        System.out.print(pair.sub1);
        System.out.println();
    }

    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize3()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
        FnList<FnTupl2<FnList<Character>, Integer>> pairs =
            pg2701_word$count$listize3();

        int count = 0;

        while (pairs.consq() && count < 100) {
            printPair(pairs.hd());
            pairs = pairs.tl();
            count += 1;
        }

        return /*void*/;
    }
}
