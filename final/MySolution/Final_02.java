/*
// HX: 50 points for Final_02
// HX: This one tests your quicksort and mergesort
// In Final_01, pg2701_word$strmize() is implemented
// that lists all the words in pg2701.txt. Here, you
// are asked to generate FnList of pairs; each pair consists
// of a word (FnList<Character>) and a count (Integer) such that
// the count is the number of occurrences of the word in pg2701.txt.
// Note that a lower case letter is considered the same as its
// corresponding upper case. For instance, "Whale" and "whale"
// are considered the same word.
*/


import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.Sorting.*;
import MyLibrary.LnStrm.*;


public class Final_02 {
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

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Turn this stream into an array A1 of words (FnList<Character>[])
	// 3. Call the quicksort in MyLibrary to sort A1
	// 4. Use sorted A1 to generate a list L2 of word-count pairs
	// 5. Use the mergesort (mergeSort) in MyLibrary to sort L2 using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 6. The sorted L2 is the return value of pg2701_word$count$listize2()
        LnStrm<FnList<Character>> wordStream =
            Final_01.pg2701_word$strmize();

        FnList<FnList<Character>> words =
            LnStrmSUtil.toFnList0(wordStream);

        int n = words.length();

        FnList<Character>[] A1 =
            (FnList<Character>[]) new FnList[n];

        FnList<FnList<Character>> walk = words;

        for (int i = 0; i < n; i += 1) {
            A1[i] = walk.hd();
            walk = walk.tl();
        }

        QuickSortArray.quickSort_array(
            A1,
            (w1, w2) -> wordCompare(w1, w2)
        );

        FnList<FnTupl2<FnList<Character>, Integer>> pairsRev =
            FnListSUtil.nil();

        int i = 0;

        while (i < n) {
            FnList<Character> current = A1[i];
            int count = 1;
            int j = i + 1;

            while (j < n && wordCompare(current, A1[j]) == 0) {
                count += 1;
                j += 1;
            }

            pairsRev =
                FnListSUtil.cons(
                    new FnTupl2<FnList<Character>, Integer>(current, count),
                    pairsRev
                );

            i = j;
        }

        FnList<FnTupl2<FnList<Character>, Integer>> pairs =
            FnListSUtil.reverse(pairsRev);

        FnList<FnTupl2<FnList<Character>, Integer>> sortedPairs =
            FnListSUtil.mergeSort(
                pairs,
                (p1, p2) -> {
                    int n1 = p1.sub1;
                    int n2 = p2.sub1;

                    if (n1 > n2) return -1;
                    if (n1 < n2) return 1;

                    return wordCompare(p1.sub0, p2.sub0);
                }
            );

        return sortedPairs;
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
	// Please write minimal testing code for pg2701_word$count$listize2()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
        FnList<FnTupl2<FnList<Character>, Integer>> pairs =
            pg2701_word$count$listize2();

        int count = 0;

        while (pairs.consq() && count < 100) {
            printPair(pairs.hd());
            pairs = pairs.tl();
            count += 1;
        }

        return /*void*/;
    }
}
