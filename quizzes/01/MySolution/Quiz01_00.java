//
// HX: 10 points
//
/*
import MyLibrary.FnList.*;
// Add for imports as you see fit
*/
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnStrn.*;
import MyLibrary.MyQueue.*;
import MyLibrary.MyRefer.*;
import MyLibrary.MyStack.*;
import MyLibrary.Sorting.*;


public class Quiz01_00 {
    /*
     Please give a description of your MyLibrary
     What classes have you implemented? For each class
     you have implemented in MyLibrary, please create an
     object of that class as follows:
     */
    public static void main (String[] args) {
	// For instance, 
	// FnList<Integer> FnList_Integer_obj = new FnList<Integer>();

    /*
    In MyLibrary I have implemented the following classes:
1. FnA1sz<T> is a generic wrapper around an array with various methods 
2. FnGseq<XS, X0> is an abstract generic sequence that defines various common operations
3. FnList<T> is a simple generic linked-list class that stores elements in head-tail nodes and supports basic list operations
4. FnStrn is a lightweight string wrapper that stores characters in a private array and provides basic operations
5. MyQueueArray<T> is an array-based circular queue implementation that stores elements in FIFO order
6. MyRefer<T> is a generic mutable reference wrapper that stores a single value with basic methods
7. MyStackArray<T> is an array-based generic stack implementation that stores elements in LIFO order
8. MyStackList<T> is a linked-list-based generic stack implementation that stores elements in LIFO order using nodes
9. BubbleSort is a generic utility class that sorts an array by repeatedly swapping adjacent out-of-order elements until the array is sorted
9. HeapSort is a generic utility class that sorts an array in ascending order using the heap sort algorithm with heapify and swap helpers
10. InsertionSort is a generic utility class that sorts an array in ascending order by repeatedly inserting each element into its correct position in the sorted portion.
11. InsertionSortStable is a utility class that performs a stable insertion sort on a custom FnList<T> using a comparator function, especially suited for nearly sorted list
12. SelectionSort is a generic utility class that sorts an array by repeatedly finding the smallest remaining element and swapping it into place
    */
// 1. FnA1sz<T>
FnA1sz<Integer> arr = new FnA1sz<>(new Integer[]{1, 2, 3, 4});

// 2. FnGseq<XS, X0>
// Cannot create 

// 3. FnList<T>
FnList<Integer> list = new FnList<>(1,
    new FnList<>(2,
        new FnList<>(3, new FnList<>())
    )
);

// 4. FnStrn
FnStrn str = new FnStrn("hello");

// 5. MyQueueArray<T>
MyQueueArray<Integer> queue = new MyQueueArray<>(10);

// 6. MyRefer<T>
MyRefer<String> ref = new MyRefer<>("stored value");

// 7. MyStackArray<T>
MyStackArray<Integer> stackArray = new MyStackArray<>(10);

// 8. MyStackList<T>
MyStackList<Integer> stackList = new MyStackList<>();

// 9. BubbleSort
BubbleSort bubbleSort = new BubbleSort();

// 10. HeapSort
HeapSort heapSort = new HeapSort();

// 11. InsertionSort
InsertionSort insertionSort = new InsertionSort();

// 12. InsertionSortStable
InsertionSortStable insertionSortStable = new InsertionSortStable();

// 13. SelectionSort
SelectionSort selectionSort = new SelectionSort();
	return /*void*/;
    }
}
