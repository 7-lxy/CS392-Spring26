package MyLibrary.LnList;

import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class LnListSUtil {
//
    public static<T>
	LnList<T> nil() {
	return new LnList<T>();
    }
    public static<T>
	LnList<T>
	cons(T x0, LnList<T> xs) {
	return new LnList<T>(x0, xs);
    }
//
    public static<T>
	boolean nilq1(LnList<T> xs) {
	return xs.nilq1();
    }
    public static<T>
	boolean consq1(LnList<T> xs) {
	return xs.consq1();
    }
//
    public static<T>
	LnList<T> reverse0(LnList<T> xs) {
	return xs.reverse0();
    }
//
    public static<T>
	boolean orderedq1(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	return xs.orderedq1(cmp);
    }
    public static
	<T extends Comparable<T>>
	boolean orderedq1(LnList<T> xs) {
	return orderedq1(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T>
	LnList<T> mergeSort1(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	return xs.mergeSort1(cmp);
    }
    public static<T>
	LnList<T> mergeSort0(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	return xs.mergeSort0(cmp);
    }
    public static
	<T extends Comparable<T>>
	LnList<T> mergeSort1(LnList<T> xs) {
	return mergeSort1(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static
	<T extends Comparable<T>>
	LnList<T> mergeSort0(LnList<T> xs) {
	return mergeSort0(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T>
	LnList<T> insertSort1(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	return xs.insertSort1(cmp);
    }
    public static<T>
	LnList<T> insertSort0(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	return xs.insertSort0(cmp);
    }
    public static
	<T extends Comparable<T>>
	LnList<T> insertSort1(LnList<T> xs) {
	return insertSort1(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static
	<T extends Comparable<T>>
	LnList<T> insertSort0(LnList<T> xs) {
	return insertSort0(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
} // end of [public class LnListSUtil{...}]
