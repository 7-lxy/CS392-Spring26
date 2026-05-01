//
// HX: 20 points
//
/*
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
// Add more imports as you see fit
*/
public class Quiz02_00 {
    /*
     Please give a description of your MyLibrary
     What classes have you implemented? For each class
     you have implemented in MyLibrary, please create an
     object of that class as follows:
     */

    /*
     MyLibrary is a Java utility/data-structures library.
     There are implemtnations of functional style lists/arrays/strings/tuples, lazy streams, stacks, queues, maps, tree traversal helpers, references, and sorting algorithms.
    */

    public static void main (String[] args) {
	// For instance, 
	// FnList<Integer> FnList_Integer_obj = new FnList<Integer>();
        // FnA1sz 
        FnA1sz<Integer> FnA1sz_Integer_obj =
            new FnA1sz<Integer>(new Integer[]{1, 2, 3});

        FnA1szSUtil FnA1szSUtil_obj =
            new FnA1szSUtil();

        FnA1szUtil<Integer> FnA1szUtil_Integer_obj =
            new FnA1szUtil<Integer>();


        // FnList
        FnList<Integer> FnList_Integer_obj =
            new FnList<Integer>();

        FnListSUtil FnListSUtil_obj =
            new FnListSUtil();


        // FnStrn
        FnStrn FnStrn_obj =
            new FnStrn("hello world");

        FnStrnSUtil FnStrnSUtil_obj =
            new FnStrnSUtil();


        // FnTuple
        FnTupl2<Integer, String> FnTupl2_Integer_String_obj =
            new FnTupl2<Integer, String>(1, "one");

        FnTupl2SUtil FnTupl2SUtil_obj =
            new FnTupl2SUtil();

        FnTupl3<Integer, String, Double> FnTupl3_Integer_String_Double_obj =
            new FnTupl3<Integer, String, Double>(1, "one", 1.0);

        FnTupl3SUtil FnTupl3SUtil_obj =
            new FnTupl3SUtil();


        // LnList
        LnList<Integer> LnList_Integer_obj =
            new LnList<Integer>();

        LnListSUtil LnListSUtil_obj =
            new LnListSUtil();


        // LnStrm
        LnStcn<Integer> LnStcn_Integer_obj =
            new LnStcn<Integer>();

        LnStrm<Integer> LnStrm_Integer_obj =
            new LnStrm<Integer>();

        LnStrmSUtil LnStrmSUtil_obj =
            new LnStrmSUtil();


        // MyQueue (interface)
        MyQueueArray<Integer> MyQueueArray_Integer_obj =
            new MyQueueArray<Integer>(10);

        MyQueueList<Integer> MyQueueList_Integer_obj =
            new MyQueueList<Integer>();

        MyQueueEmptyExn MyQueueEmptyExn_obj =
            new MyQueueEmptyExn();

        MyQueueFullExn MyQueueFullExn_obj =
            new MyQueueFullExn();


        // MyStack (interface)

        MyStackArray<Integer> MyStackArray_Integer_obj =
            new MyStackArray<Integer>(10);

        MyStackList<Integer> MyStackList_Integer_obj =
            new MyStackList<Integer>();

        MyStackEmptyExn MyStackEmptyExn_obj =
            new MyStackEmptyExn();

        MyStackFullExn MyStackFullExn_obj =
            new MyStackFullExn();


        //  MyRefer
        MyRefer<Integer> MyRefer_Integer_obj =
            new MyRefer<Integer>();

        MyReferNullExn MyReferNullExn_obj =
            new MyReferNullExn();


        // MyMap00 (interface)

        MyMap00FullExn MyMap00FullExn_obj =
            new MyMap00FullExn();

        MyMap00NoKeyExn MyMap00NoKeyExn_obj =
            new MyMap00NoKeyExn();

        // FnGtree (interface)

        FnGtreeSUtil FnGtreeSUtil_obj =
            new FnGtreeSUtil();


        //  Sorting 
        BubbleSort BubbleSort_obj =
            new BubbleSort();

        HeapSort HeapSort_obj =
            new HeapSort();

        InsertionSort InsertionSort_obj =
            new InsertionSort();
            
        InsertionSortStable InsertionSortStable_obj =
            new InsertionSortStable();

        QuickSortArray QuickSortArray_obj =
            new QuickSortArray();

        SelectionSort SelectionSort_obj =
            new SelectionSort();

        SortingUtils SortingUtils_obj =
            new SortingUtils();
	return /*void*/;
    }
} // end of [class Quiz01_00{...}]
