//
// HX-2026-04-28: 50 points
//
// This question tests your understanding
// of recursion and time analysis involving
// recursion.
// Given a sequence xs, a subsequence of xs
// can be represented as a list of integers
// (representing indices). For instance, given
// xs = "Hello", (0, 2, 4) refers to the subeqence
// "Hlo" (since xs[0] = 'H', xs[2] = 'l', and
// xs[4] = 'o'); (0, 3, 4) also refers to "Hlo".
// The subsequece (0, 2, 4) is to the left of
// the subsequece (0, 3, 4) as (0, 2, 4) is less
// than (0, 3, 4) according to the lexicographic
// ordering.
//
// Here you are asked to implement a function that
// finds the longest leftmost ascending subsequence
// of a given sequence.
// For instance, suppose xs = [1,2,1,2,3,1,2,3,4],
// the longest leftmost ascending subsequence of xs
// is represented by (0, 1, 3, 4, 7, 8) (which refers
// to [1,2,2,3,3,4] in xs).
//
// In order to receive 50 points, your implementation
// should be quadratic time, that is, O(n^2) time and
// you MUST give a brief explanation as to why it is so.
// Otherwise, a working solution receives at most 60%, that
// is, 30 points out of 50 points.
//
import Library00.FnList.*;
// Please see Library00/FnList for FnList.java
import Library00.FnA1sz.*;
// Please see Library00/FnA1sz for FnA1sz.java
public class Quiz02_01 {
    public static
	<T extends Comparable<T>>
	FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
	// HX-2025-11-19:
	// This method finds the leftmost longest ascending subsequence
	// of xs. Note that the returned list consists of the indices of
	// the elements of the subsequence.
        int n = xs.length();

        if (n <= 0) {
            return FnListSUtil.nil();
        }

        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i -= 1) {
            dp[i] = 1;

            for (int j = i + 1; j < n; j += 1) {
                if (xs.getAt(i).compareTo(xs.getAt(j)) <= 0) {
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = 1 + dp[j];
                    }
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < n; i += 1) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }

        FnList<Integer> ansRev = FnListSUtil.nil();

        int prevIndex = -1;
        T prevValue = null;
        int need = maxLen;

        while (need > 0) {
            for (int i = prevIndex + 1; i < n; i += 1) {
                boolean valueOK =
                    (prevIndex < 0) ||
                    (prevValue.compareTo(xs.getAt(i)) <= 0);

                boolean lengthOK = dp[i] >= need;

                if (valueOK && lengthOK) {
                    ansRev = FnListSUtil.cons(i, ansRev);
                    prevIndex = i;
                    prevValue = xs.getAt(i);
                    need -= 1;
                    break;
                }
            }
        }

        return FnListSUtil.reverse(ansRev);
    }
    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for FnA1szLongestMonoSubsequence
        Integer[] arr = {1, 2, 1, 2, 3, 1, 2, 3, 4};
        FnA1sz<Integer> xs = new FnA1sz<Integer>(arr);

        FnList<Integer> ans = FnA1szLongestMonoSubsequence(xs);

        ans.System$out$print();
        System.out.println();


	return /*void*/;
    }
} // end of [public class Quiz02_01{...}]
