package MyLibrary.MyHeap;

public class MyMinHeap<T extends Comparable<T>> {
    private T[] data;
    private int size;

    public MyMinHeap(int capacity) {
        data = (T[]) new Comparable[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size >= data.length;
    }

    public T min() {
        if (isEmpty()) return null;
        return data[0];
    }

    public boolean insert(T x) {
        if (isFull()) return false;

        data[size] = x;
        bubbleUp(size);
        size += 1;

        return true;
    }

    public T deleteMin() {
        if (isEmpty()) return null;

        T result = data[0];

        size -= 1;
        data[0] = data[size];
        data[size] = null;

        bubbleDown(0);

        return result;
    }

    private void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (data[parent].compareTo(data[i]) <= 0) {
                break;
            }

            swap(parent, i);
            i = parent;
        }
    }

    private void bubbleDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && data[left].compareTo(data[smallest]) < 0) {
                smallest = left;
            }

            if (right < size && data[right].compareTo(data[smallest]) < 0) {
                smallest = right;
            }

            if (smallest == i) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void printHeap() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyMinHeap<Integer> heap = new MyMinHeap<Integer>(10);

        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        heap.printHeap();

        System.out.println(heap.deleteMin()); // 1
        System.out.println(heap.deleteMin()); // 3
        System.out.println(heap.deleteMin()); // 5
        System.out.println(heap.deleteMin()); // 8
    }
}