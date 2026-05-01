//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
import Library00.LnStrm.*;
//
public class Quiz02_04 {
    public static class AVLnode {
        int key;
        AVLnode lchild;
        AVLnode rchild;

        AVLnode(int key) {
            this.key = key;
            this.lchild = null;
            this.rchild = null;
        }
    }

    private static class Info {
        boolean isAVL;
        int height;

        Info(boolean isAVL, int height) {
            this.isAVL = isAVL;
            this.height = height;
        }
    }

    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [avl] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you have already
    // visited each node once.
    //
    public static boolean isAVL (AVLnode avl) {
	// HX: Please implement a function that
	// tests whether a given AVLnode is a valid
	// AVL tree. If it is unclear what an
	// AVL tree, you can readily find it on-line
	// Note that you are not asked to check if avl is
	// a binary search tree in this case.
        return checkAVL(avl).isAVL;
    }

    private static Info checkAVL(AVLnode node) {
        if (node == null) {
            return new Info(true, -1);
        }

        Info left = checkAVL(node.lchild);
        Info right = checkAVL(node.rchild);

        boolean ok =
            left.isAVL &&
            right.isAVL &&
            Math.abs(left.height - right.height) <= 1;

        int height = 1 + Math.max(left.height, right.height);

        return new Info(ok, height);
    }

    private static int height(AVLnode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.lchild), height(node.rchild));
    }

    private static int assignKeysInorder(AVLnode node, int nextKey) {
        if (node == null) return nextKey;

        nextKey = assignKeysInorder(node.lchild, nextKey);

        node.key = nextKey;
        nextKey += 1;

        nextKey = assignKeysInorder(node.rchild, nextKey);

        return nextKey;
    }

    private static AVLnode buildMaxHeightAVLShape(int n) {
        if (n <= 0) return null;

        AVLnode root = new AVLnode(0);

        if (n == 1) return root;

        int leftN = (n - 1) / 2;
        int rightN = n - 1 - leftN;

        root.lchild = buildMaxHeightAVLShape(rightN);
        root.rchild = buildMaxHeightAVLShape(leftN);

        return root;
    }

    //
    // HX: 20 points
    // This is largely about understanding AVL trees.
    // Please explain BRIEFLY as to why the generated AVL is
    // of maximal height (not minimal height). Note that this
    // is different from what is asked in Quiz02_05.
    //
    public static boolean genAVLBST() {
	// Please genenerate a binary search RBT that
	// contains exactly 1 million keys: 0, 1, 2, ..., 999999
	// such that the height of this tree is maximal (that is,
	// as large as possible). What is this height? Please give
	// a brief explanation on your implementation strategy.
        int n = 1_000_000;

        AVLnode root = buildMaxHeightAVLShape(n);

        assignKeysInorder(root, 0);

        boolean ok = isAVL(root);
        int h = height(root);

        System.out.println("Generated AVL BST with " + n + " keys.");
        System.out.println("Is AVL: " + ok);
        System.out.println("Height of generated tree: " + h);

        return ok;
    }
    public static void main (String[] args) {
	// Please add minimal testing code for isRBT()
	// Please add minimal testing code for genAVLBST()
        genAVLBST();

    AVLnode good = new AVLnode(2);
    good.lchild = new AVLnode(1);
    good.rchild = new AVLnode(3);

    System.out.println(isAVL(good)); 
    // true

    AVLnode bad = new AVLnode(1);
    bad.rchild = new AVLnode(2);
    bad.rchild.rchild = new AVLnode(3);

    System.out.println(isAVL(bad)); 
    // false

	    return /*void*/;
    }
}
