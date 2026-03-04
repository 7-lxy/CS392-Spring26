package MyLibrary.Sorting;

public class SortingTest {
    public static void main(String[] args) {

         // Bubble Sort Test
        Integer[] numbers = {5, 1, 4, 2, 8};

        System.out.println("Before sorting numbers:");
        BubbleSort.printArray(numbers);
        BubbleSort.bubbleSort(numbers);
        System.out.println("After sorting numbers:");
        BubbleSort.printArray(numbers);
        System.out.println();

        // Heap Sort Test
        Integer[] heapNumbers = {3, 9, 2, 1, 4};
        System.out.println("Before sorting heap numbers:");
        HeapSort.printArray(heapNumbers);

        HeapSort.heapSort(heapNumbers);
        System.out.println("After sorting heap numbers:");
        HeapSort.printArray(heapNumbers);
        System.out.println();

        // Insertion Sort Test
        Integer[] insertionNumbers = {12, 11, 13, 5, 6};
        System.out.println("Before sorting insertion numbers:");
        InsertionSort.printArray(insertionNumbers);

        InsertionSort.insertionSort(insertionNumbers);
        System.out.println("After sorting insertion numbers:");
        InsertionSort.printArray(insertionNumbers);
        System.out.println();

        // Selection Sort Test
        Integer[] selectionNumbers = {64, 25, 12, 22, 11};
        System.out.println("Before sorting selection numbers:");
        SelectionSort.printArray(selectionNumbers);

        SelectionSort.selectionSort(selectionNumbers);
        System.out.println("After sorting selection numbers:");
        SelectionSort.printArray(selectionNumbers);
    }
}
