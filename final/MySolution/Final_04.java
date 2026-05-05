/*
// HX: 50 points for Final_04
// HX: This one tests your RBST implementation done in Quiz02_06.
// In Final_02, pg2701_word$count$listize1() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_04, you are asked to implement the same functionality
// with a different approach.
*/


import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;

import java.util.Random;
import java.util.function.ToIntBiFunction;


public class Final_04 {
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

    private static class RBSTMap<K extends Comparable<K>, V> {
        private class Node {
            K key;
            V val;
            int size;
            Node parent;
            Node lchild;
            Node rchild;
        }

        private Node root = null;
        private Random rand = new Random();

        private int size(Node x) {
            return x == null ? 0 : x.size;
        }

        private void updateSize(Node x) {
            if (x != null) {
                x.size = 1 + size(x.lchild) + size(x.rchild);
            }
        }

        private Node makeNode(K key, V val) {
            Node x = new Node();
            x.key = key;
            x.val = val;
            x.size = 1;
            x.parent = null;
            x.lchild = null;
            x.rchild = null;
            return x;
        }

        public V search(K key) {
            Node curr = root;

            while (curr != null) {
                int cmp = key.compareTo(curr.key);

                if (cmp == 0) {
                    return curr.val;
                } else if (cmp < 0) {
                    curr = curr.lchild;
                } else {
                    curr = curr.rchild;
                }
            }

            return null;
        }

        public void insertOrUpdate(K key, V val) {
            if (root == null) {
                root = makeNode(key, val);
                return;
            }

            Node curr = root;
            Node parent = null;

            while (curr != null) {
                parent = curr;

                int cmp = key.compareTo(curr.key);

                if (cmp == 0) {
                    curr.val = val;
                    return;
                } else if (cmp < 0) {
                    curr = curr.lchild;
                } else {
                    curr = curr.rchild;
                }
            }

            Node newNode = makeNode(key, val);
            newNode.parent = parent;

            if (key.compareTo(parent.key) < 0) {
                parent.lchild = newNode;
            } else {
                parent.rchild = newNode;
            }

            Node walk = parent;
            while (walk != null) {
                updateSize(walk);
                walk = walk.parent;
            }

            if (rand.nextInt(size(root)) == 0) {
                reroot(newNode);
            }
        }

        private void rotateLeft(Node x) {
            Node y = x.rchild;
            if (y == null) return;

            Node p = x.parent;
            Node beta = y.lchild;

            y.parent = p;
            if (p == null) {
                root = y;
            } else if (p.lchild == x) {
                p.lchild = y;
            } else {
                p.rchild = y;
            }

            y.lchild = x;
            x.parent = y;

            x.rchild = beta;
            if (beta != null) {
                beta.parent = x;
            }

            updateSize(x);
            updateSize(y);
        }

        private void rotateRight(Node x) {
            Node y = x.lchild;
            if (y == null) return;

            Node p = x.parent;
            Node beta = y.rchild;

            y.parent = p;
            if (p == null) {
                root = y;
            } else if (p.lchild == x) {
                p.lchild = y;
            } else {
                p.rchild = y;
            }

            y.rchild = x;
            x.parent = y;

            x.lchild = beta;
            if (beta != null) {
                beta.parent = x;
            }

            updateSize(x);
            updateSize(y);
        }

        private void reroot(Node picked) {
            while (picked.parent != null) {
                Node p = picked.parent;

                if (p.lchild == picked) {
                    rotateRight(p);
                } else {
                    rotateLeft(p);
                }
            }

            root = picked;
        }

        public FnList<FnTupl2<K, V>> listize() {
            return listize(root, FnListSUtil.nil());
        }

        private FnList<FnTupl2<K, V>>
        listize(Node node, FnList<FnTupl2<K, V>> acc) {
            if (node == null) {
                return acc;
            }

            acc = listize(node.rchild, acc);
            acc = FnListSUtil.cons(
                new FnTupl2<K, V>(node.key, node.val),
                acc
            );
            acc = listize(node.lchild, acc);

            return acc;
        }
    }

    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
	// HX-2026-05-04:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the RBST implemented in Quiz02_06 to count the number of
	//    occurrences of each word in the stream of words.
	//    Note that you need to modify your Quiz02_06 implementation to turn
	//    it into an generic associative map for this part.
	// 3. Then figure out a way to turn the RBST-based map into a list WNS
	//    (FnList) of word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize4()
        LnStrm<FnList<Character>> words =
            Final_01.pg2701_word$strmize();

        RBSTMap<String, FnTupl2<FnList<Character>, Integer>> map =
            new RBSTMap<String, FnTupl2<FnList<Character>, Integer>>();

        while (true) {
            LnStcn<FnList<Character>> cell = words.eval0();

            if (cell.nilq()) {
                break;
            }

            FnList<Character> word = cell.hd();
            String key = wordToString(word);

            FnTupl2<FnList<Character>, Integer> oldPair =
                map.search(key);

            if (oldPair == null) {
                map.insertOrUpdate(
                    key,
                    new FnTupl2<FnList<Character>, Integer>(word, 1)
                );
            } else {
                map.insertOrUpdate(
                    key,
                    new FnTupl2<FnList<Character>, Integer>(
                        oldPair.sub0,
                        oldPair.sub1 + 1
                    )
                );
            }

            words = cell.tl();
        }

        FnList<FnTupl2<String, FnTupl2<FnList<Character>, Integer>>> entries =
            map.listize();

        FnList<FnTupl2<FnList<Character>, Integer>> WNS =
            FnListSUtil.nil();

        while (entries.consq()) {
            WNS = FnListSUtil.cons(entries.hd().sub1, WNS);
            entries = entries.tl();
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
	// Please write minimal testing code for pg2701_word$count$listize4()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
        FnList<FnTupl2<FnList<Character>, Integer>> pairs =
            pg2701_word$count$listize4();

        int count = 0;

        while (pairs.consq() && count < 100) {
            printPair(pairs.hd());
            pairs = pairs.tl();
            count += 1;
        }

        return /*void*/;
    }
}
