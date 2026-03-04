package MyLibrary.Sorting;

public class HeapSort {
    public static <T extends Comparable<? super T>> void heapSort(T[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    private static <T extends Comparable<? super T>> void heapify(T[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < heapSize && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        if (right < heapSize && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        if (largest != rootIndex) {
            swap(array, rootIndex, largest);
            heapify(array, heapSize, largest);
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static <T> void printArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
