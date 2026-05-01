package MyLibrary.LnList;

import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class LnList<T> {
    Node root;
//
    public final
    LnListSUtil SU = new LnListSUtil();
//    
    private class Node {
	T head;
	Node tail;
	Node(T x0, Node xs) {
	    head = x0; tail = xs;
	}
    }
//
    public LnList() {
	root = null;
    }
    public LnList
	(FnList<T> xs) {
	Node ys = null;
	Node tl = null;
	while (!xs.nilq()) {
	    ys = new Node(xs.hd(), ys);
	    xs = xs.tl();
	}
	root = null;
	while (ys != null) {
	    tl = ys.tail;
	    ys.tail = root; root = ys; ys = tl;
	}
    }
    public LnList
	(FnA1sz<T> xs) {
	T x0 = null;
	Node ys = null;
	int n = xs.length();
	for (int i = n; i > 0; i -= 1) {
	    x0 = xs.getAt(i-1);
	    ys = new Node(x0, ys); 
	}
	root = ys;
    }
//
    public LnList
	(T x0, LnList<T> xs) {
	root = new Node(x0, xs.root);
    }
//
    private LnList(Node xs) {
	root = xs;
    }
//
    public void free() {
	root = null; return;
    }
//
    public boolean nilq1() {
	return (root == null);
    }
    public boolean consq1() {
	return (root != null);
    }
//
    public T hd1() {
	return root.head;
    }
    public LnList<T> tl1() {
        Node tail = root.tail;
	return new LnList(tail);
    }
    public LnList<T> tl0() {
	Node tail = root.tail;
	root = null;
	return new LnList(tail);
    }
//
    public void
	link1(LnList<T> tail) {
	assert(root.tail==null);
	root.tail = tail.root; return /*void*/;
    }
    public LnList<T> unlink1() {
	Node tail = root.tail;
	root.tail = null; return new LnList(tail);
    }
//
    public int length1() {
	int res = 0;
	Node xs = root;
	while (xs != null) {
	    res += 1; xs = xs.tail;
	}
	return res;
    }
//
    public LnList<T>
	append0(LnList<T> ys) {
	append1(ys); return this;
    }
    public void
	append1(LnList<T> ys) {
	Node xs = root;
	if (xs == null) {
	    root = ys.root; return;
	}
	while (xs.tail != null) {
	    xs = xs.tail;
	}
	xs.tail/*null*/ = ys.root; return;
    }
//
    public void reverse1() {
	LnList<T>
	xs = this.reverse0();
	root = xs.root; return;
    }
    public LnList<T> reverse0() {
	Node xs = root;
	Node ys = null;
	Node tl = null; root = null;
	while (xs != null) {
	    tl = xs.tail;
	    xs.tail = ys; ys = xs; xs = tl;
	}
	return new LnList<T>(ys);
    }
//
    public Node copyNodes(Node xs) {
	Node head = null;
	Node tail = null;
	while (xs != null) {
	    Node node = new Node(xs.head, null);
	    if (head == null) {
		head = node;
		tail = node;
	    } else {
		tail.tail = node;
		tail = node;
	    }
	    xs = xs.tail;
	}
	return head;
    }
//
    private Node splitNodes(Node xs) {
	Node slow = xs;
	Node fast = xs.tail;
	while (fast != null && fast.tail != null) {
	    slow = slow.tail;
	    fast = fast.tail.tail;
	}
	Node second = slow.tail;
	slow.tail = null;
	return second;
    }
//
    private Node mergeNodes(Node xs, Node ys, ToIntBiFunction<T,T> cmp) {
	Node dummy = new Node(null, null);
	Node tail = dummy;
	while (xs != null && ys != null) {
	    if (cmp.applyAsInt(xs.head, ys.head) <= 0) {
		tail.tail = xs;
		xs = xs.tail;
	    } else {
		tail.tail = ys;
		ys = ys.tail;
	    }
	    tail = tail.tail;
	}
	tail.tail = (xs != null) ? xs : ys;
	return dummy.tail;
    }
//
    private Node mergeSortNodes(Node xs, ToIntBiFunction<T,T> cmp) {
	if (xs == null || xs.tail == null) return xs;
	Node ys = splitNodes(xs);
	return mergeNodes(mergeSortNodes(xs, cmp), mergeSortNodes(ys, cmp), cmp);
    }
//
    private Node insertNode(Node sorted, Node x0, ToIntBiFunction<T,T> cmp) {
	if (sorted == null || cmp.applyAsInt(x0.head, sorted.head) < 0) {
	    x0.tail = sorted;
	    return x0;
	}
	Node curr = sorted;
	while (curr.tail != null && cmp.applyAsInt(curr.tail.head, x0.head) <= 0) {
	    curr = curr.tail;
	}
	x0.tail = curr.tail;
	curr.tail = x0;
	return sorted;
    }
//
    private Node insertSortNodes(Node xs, ToIntBiFunction<T,T> cmp) {
	Node sorted = null;
	while (xs != null) {
	    Node next = xs.tail;
	    xs.tail = null;
	    sorted = insertNode(sorted, xs, cmp);
	    xs = next;
	}
	return sorted;
    }
//
    public LnList<T> mergeSort1(ToIntBiFunction<T,T> cmp) {
	return new LnList<T>(mergeSortNodes(copyNodes(root), cmp));
    }
    public LnList<T> mergeSort0(ToIntBiFunction<T,T> cmp) {
	Node xs = root;
	root = null;
	return new LnList<T>(mergeSortNodes(xs, cmp));
    }
    public void mergeSort1$raw(ToIntBiFunction<T,T> cmp) {
	root = mergeSortNodes(root, cmp);
	return;
    }
//
    public LnList<T> insertSort1(ToIntBiFunction<T,T> cmp) {
	return new LnList<T>(insertSortNodes(copyNodes(root), cmp));
    }
    public LnList<T> insertSort0(ToIntBiFunction<T,T> cmp) {
	Node xs = root;
	root = null;
	return new LnList<T>(insertSortNodes(xs, cmp));
    }
    public void insertSort1$raw(ToIntBiFunction<T,T> cmp) {
	root = insertSortNodes(root, cmp);
	return;
    }
//
    public boolean orderedq1(ToIntBiFunction<T,T> cmp) {
	Node xs = root;
	if (xs == null) return true;
	while (xs.tail != null) {
	    if (cmp.applyAsInt(xs.head, xs.tail.head) > 0) return false;
	    xs = xs.tail;
	}
	return true;
    }

//
    public void System$out$print1() {
    	System.out.print("LnList(");
	this.iforitm1
	(
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    public void
	foritm1(Consumer<? super T> work) {
	Node xs = root;
	while (xs != null) {
	    work.accept(xs.head); xs = xs.tail;
	}
	return /*void*/;
    }
    public void
	foritm0(Consumer<? super T> work) {
	foritm1(work); root = null; return /*void*/;
    }
//
    public void
	rforitm1(Consumer<? super T> work) {
	Node xs = root;
	Node ys = null;
	while (xs != null) {
	    ys = new Node(xs.head, ys);
	    xs = xs.tail;
	}
	while (ys != null) {
	    work.accept(ys.head); ys = ys.tail;
	}
	return /*void*/;
    }
    public void
	rforitm0(Consumer<? super T> work) {
	rforitm1(work); root = null; return /*void*/;
    }
//
    public void
	iforitm1
	(BiConsumer<Integer, ? super T> work) {
	int i0 = 0;
	Node xs = root;
	while (xs != null) {
	    work.accept(i0, xs.head); i0 += 1; xs = xs.tail;
	}
	return /*void*/;
    }
    public void
	iforitm0
	(BiConsumer<Integer, ? super T> work) {
	iforitm1(work); root = null; return /*void*/;
    }
//
} // end of [public class LnList<T>{...}]
