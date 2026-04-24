package Library.MyPQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyPQueueArray<T extends Comparable<? super T>> extends MyPQueueBase<T> {
    private final T[] heap;
    private int n;

    public MyPQueueArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be non-negative");
        }
        this.heap = (T[]) new Comparable[capacity];
        this.n = 0;
    }

    public MyPQueueArray() {
        this(1024);
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isFull() {
        return n >= heap.length;
    }

    @Override
    public T top$raw() {
        return heap[0];
    }

    @Override
    public T deque$raw() {
        T ans = heap[0];
        n -= 1;

        if (n > 0) {
            heap[0] = heap[n];
            heap[n] = null;
            siftDown(0);
        } else {
            heap[0] = null;
        }

        return ans;
    }

    @Override
    public void enque$raw(T itm) {
        if (itm == null) {
            throw new NullPointerException("priority queue does not support null items");
        }

        heap[n] = itm;
        siftUp(n);
        n += 1;
    }

    private void siftUp(int child) {
        while (child > 0) {
            int parent = (child - 1) / 2;

            if (heap[parent].compareTo(heap[child]) >= 0) {
                break;
            }

            swap(parent, child);
            child = parent;
        }
    }

    private void siftDown(int parent) {
        while (true) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int largest = parent;

            if (left < n && heap[left].compareTo(heap[largest]) > 0) {
                largest = left;
            }

            if (right < n && heap[right].compareTo(heap[largest]) > 0) {
                largest = right;
            }

            if (largest == parent) {
                break;
            }

            swap(parent, largest);
            parent = largest;
        }
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
