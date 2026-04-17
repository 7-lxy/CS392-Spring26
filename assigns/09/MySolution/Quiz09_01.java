//
// HX-2026-04-09: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 20 points for insert and 30 points for remove
//
public class Quiz09_01 {
    Node root = null;
    public class Node {
		int key; // key stored in the node
		int size; // size of the tree rooted as the node
		Node parent; // parent of the node
		Node lchild; // left-child of the node
		Node rchild; // right-child of the node

		Node(int key, Node parent) {
            this.key = key;
            this.size = 1;
            this.parent = parent;
            this.lchild = null;
            this.rchild = null;
		}
    }

	private int getSize(Node node) {
		if (node == null) {
			return 0;
		} else {
			return node.size;
		}
	}

	private void updateSize(Node node) {
		if (node != null) {
			node.size = 1 + getSize(node.lchild) + getSize(node.rchild);
		}
	}

    private void updateSizeUpward(Node node) {
        while (node != null) {
            updateSize(node);
            node = node.parent;
        }
    }

    private Node findNode(int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) {
                return cur;
            } else if (key < cur.key) {
                cur = cur.lchild;
            } else {
                cur = cur.rchild;
            }
        }
        return null;
    }

    private Node minimum(Node node) {
        while (node != null && node.lchild != null) {
            node = node.lchild;
        }
        return node;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.lchild) {
            u.parent.lchild = v;
        } else {
            u.parent.rchild = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public boolean insert(int key) {
	// HX-2026-04-09: 20 points
	// If key is in the tree stored at [root],
	// [insert] does nothing and just returns false.
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
        if (root == null) {
            root = new Node(key, null);
            return true;
        }

        Node cur = root;
        Node parent = null;

        while (cur != null) {
            parent = cur;
            if (key == cur.key) {
                return false;
            } else if (key < cur.key) {
                cur = cur.lchild;
            } else {
                cur = cur.rchild;
            }
        }

        Node newNode = new Node(key, parent);
        if (key < parent.key) {
            parent.lchild = newNode;
        } else {
            parent.rchild = newNode;
        }

        updateSizeUpward(parent);
        return true;
    }

    public boolean remove(int key) {
	// HX-2026-04-09: 20 points
	// If key is in the tree stored at [root],
	// [remove] removes the key and the new tree
	// obtained is still a binary search tree and
	// [remove] returns true to indicate the removal
	// is done.
	// If key is not in the tree stored at [root],
	// [remove] does nothing and returns false to
	// indicate that no removal of the key k is done.
        Node z = findNode(key);
        if (z == null) {
            return false;
        }

        Node sizeFixStart = null;

        if (z.lchild == null) {
            sizeFixStart = z.parent;
            transplant(z, z.rchild);
            if (sizeFixStart == null && root != null) {
                updateSize(root);
            } else {
                updateSizeUpward(sizeFixStart);
            }
        } else if (z.rchild == null) {
            sizeFixStart = z.parent;
            transplant(z, z.lchild);
            if (sizeFixStart == null && root != null) {
                updateSize(root);
            } else {
                updateSizeUpward(sizeFixStart);
            }
        } else {
            Node y = minimum(z.rchild);

            if (y.parent != z) {
                Node oldParentOfY = y.parent;

                transplant(y, y.rchild);
                y.rchild = z.rchild;
                if (y.rchild != null) {
                    y.rchild.parent = y;
                }

                transplant(z, y);
                y.lchild = z.lchild;
                if (y.lchild != null) {
                    y.lchild.parent = y;
                }

                updateSize(oldParentOfY);
                updateSize(y);
                updateSizeUpward(y.parent);
            } else {
                transplant(z, y);
                y.lchild = z.lchild;
                if (y.lchild != null) {
                    y.lchild.parent = y;
                }

                updateSize(y);
                updateSizeUpward(y.parent);
            }
        }

        return true;
    }

    private void printInorder(Node node) {
        if (node == null) return;
        printInorder(node.lchild);
        System.out.print(node.key + "(" + node.size + ") ");
        printInorder(node.rchild);
    }

    private void printTree() {
        printInorder(root);
        System.out.println();
    }
	
    public static void main (String[] args) {
	// Please add minimal testing code for insert()
	// Please add minimal testing code for remove()
        Quiz09_01 tree = new Quiz09_01();

        // insert()
        System.out.println(tree.insert(50)); // true
        System.out.println(tree.insert(30)); // true
        System.out.println(tree.insert(70)); // true
        System.out.println(tree.insert(20)); // true
        System.out.println(tree.insert(40)); // true
        System.out.println(tree.insert(60)); // true
        System.out.println(tree.insert(80)); // true
        System.out.println(tree.insert(30)); // false
        tree.printTree();

        // remove()
        System.out.println(tree.remove(20)); // true, remove leaf
        tree.printTree();

        System.out.println(tree.remove(30)); // true, remove node with one child
        tree.printTree();

        System.out.println(tree.remove(50)); // true, remove node with two children
        tree.printTree();

        System.out.println(tree.remove(999)); // false
        tree.printTree();
    }
}
