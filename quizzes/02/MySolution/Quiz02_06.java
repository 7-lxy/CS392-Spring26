//
// HX-2026-04-28: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
import java.util.Random;

public class Quiz02_06 {
    Node root = null;
	private static final Random rand = new Random();
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    private void updateSize(Node x) {
        if (x != null) {
            x.size = 1 + size(x.lchild) + size(x.rchild);
        }
    }

    private Node makeNode(int key) {
        Node x = new Node();
        x.key = key;
        x.size = 1;
        x.parent = null;
        x.lchild = null;
        x.rchild = null;
        return x;
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

    private Node randomNode(Node x) {
        int leftSize = size(x.lchild);
        int r = rand.nextInt(x.size);

        if (r < leftSize) {
            return randomNode(x.lchild);
        } else if (r == leftSize) {
            return x;
        } else {
            return randomNode(x.rchild);
        }
    }

    public void reroot() {
	// HX-2025-11-20: 30 points
	// [reroot] picks a node RANDOMLY and
	// uses rotations to turn this picked node
	// into the root of a new binary search tree
	// (containing the same set of keys)
        if (root == null) return;

        Node picked = randomNode(root);

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
    public boolean insert(int key) {
	// HX-2025-11-20: 20 points
	// If key is in the tree stored at [root],
	// [insert] does no nothing and just returns false
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
        if (root == null) {
            root = makeNode(key);
            return true;
        }

        Node curr = root;
        Node parent = null;

        while (curr != null) {
            parent = curr;

            if (key == curr.key) {
                return false;
            } else if (key < curr.key) {
                curr = curr.lchild;
            } else {
                curr = curr.rchild;
            }
        }

        Node newNode = makeNode(key);
        newNode.parent = parent;

        if (key < parent.key) {
            parent.lchild = newNode;
        } else {
            parent.rchild = newNode;
        }

        Node walk = parent;
        while (walk != null) {
            updateSize(walk);
            walk = walk.parent;
        }

        return true;
    }

    private void printInorder(Node x) {
        if (x == null) return;
        printInorder(x.lchild);
        System.out.print(x.key + " ");
        printInorder(x.rchild);
    }

    private void printInorder() {
        printInorder(root);
        System.out.println();
    }
    public static void main (String[] args) {
	// Please add minimal testing code for reroot()
	// Please add minimal testing code for insert()
        Quiz02_06 tree = new Quiz02_06();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println("Initial:");
        tree.printInorder();

        System.out.println("Root before reroot: " + tree.root.key);
        tree.reroot();
        System.out.println("Root after reroot: " + tree.root.key);

        System.out.println("After reroot:");
        tree.printInorder();

        System.out.println("Insert 10: " + tree.insert(10)); // true
        System.out.println("Insert 4: " + tree.insert(4)); // false

        System.out.println("Final:");
        tree.printInorder();
	return /*void*/;
    }
}
