/*
HX-2026-02-13: 20 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

public class Assign04_02 {
	static FnStrn
	FnList$FnStrn_concate(FnList<FnStrn> xs) {
	// Given a list of strings, this method return the
	// concatenation of these string. For instance, given
	// ("a", "bc", "def"), the returned string is "abcdef"
	// You implementation is NOT allowed to use loops or
	// reccursion. Try to use the 'foritm' method in FnList
	// and FnStrn to accomplish this task.
	
		FnStrn[] result = {new FnStrn("")};
		FnListSUtil.foritm(xs, x -> {
			int len1 = result[0].length();
			int len2 = x.length();
			char[] combined = new char[len1 + len2];
			for (int i = 0; i < len1; i++) {
				combined[i] = result[0].getAt(i);
			}
			for (int i = 0; i < len2; i++) {
				combined[len1 + i] = x.getAt(i);
			}
			result[0] = new FnStrn(combined);
		});
		return result[0];
	}

    public static void main(String[] argv) {
	// Please write some testing code for your 'FnList$FnStrn_concate"
		FnList<FnStrn> list1 = new FnList<>(new FnStrn("a"), new FnList<>(new FnStrn("bc"), new FnList<>(new FnStrn("def"), new FnList<>())));
		System.out.println(FnList$FnStrn_concate(list1)); // "abcdef"

		FnList<FnStrn> list2 = new FnList<>(new FnStrn("Hello, "), new FnList<>(new FnStrn("world!"), new FnList<>()));
		System.out.println(FnList$FnStrn_concate(list2)); // "Hello, world!"

		FnList<FnStrn> list3 = new FnList<>();
		System.out.println(FnList$FnStrn_concate(list3)); // ""
    }
} // end of [public class Assign04_02{...}]
