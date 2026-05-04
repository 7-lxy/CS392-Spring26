package MyLibrary.MyBSTMap;

public class MyBSTMap<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V val;
        Node lchild;
        Node rchild;
        int size;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
        }
    }

    private Node root;

    public MyBSTMap() {
        root = null;
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
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

    public V insert(K key, V val) {
        V old = search(key);
        root = insert(root, key, val);
        return old;
    }

    private Node insert(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.lchild = insert(node.lchild, key, val);
        } else if (cmp > 0) {
            node.rchild = insert(node.rchild, key, val);
        } else {
            node.val = val;
        }

        node.size = 1 + size(node.lchild) + size(node.rchild);

        return node;
    }

    public V remove(K key) {
        V old = search(key);

        if (old != null) {
            root = remove(root, key);
        }

        return old;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.lchild = remove(node.lchild, key);
        } else if (cmp > 0) {
            node.rchild = remove(node.rchild, key);
        } else {
            if (node.lchild == null) return node.rchild;
            if (node.rchild == null) return node.lchild;

            Node successor = minNode(node.rchild);

            node.key = successor.key;
            node.val = successor.val;

            node.rchild = removeMin(node.rchild);
        }

        node.size = 1 + size(node.lchild) + size(node.rchild);

        return node;
    }

    private Node minNode(Node node) {
        while (node.lchild != null) {
            node = node.lchild;
        }

        return node;
    }

    private Node removeMin(Node node) {
        if (node.lchild == null) {
            return node.rchild;
        }

        node.lchild = removeMin(node.lchild);
        node.size = 1 + size(node.lchild) + size(node.rchild);

        return node;
    }

    public void printInorder() {
        printInorder(root);
        System.out.println();
    }

    private void printInorder(Node node) {
        if (node == null) return;

        printInorder(node.lchild);
        System.out.print("(" + node.key + ", " + node.val + ") ");
        printInorder(node.rchild);
    }

    public static void main(String[] args) {
        MyBSTMap<Integer, String> map =
            new MyBSTMap<Integer, String>();

        map.insert(5, "five");
        map.insert(3, "three");
        map.insert(7, "seven");
        map.insert(4, "four");

        map.printInorder();
        // Expected sorted order:
        // (3, three) (4, four) (5, five) (7, seven)

        System.out.println(map.search(4)); // four

        map.remove(3);
        map.printInorder();
        // Expected:
        // (4, four) (5, five) (7, seven)
    }
}