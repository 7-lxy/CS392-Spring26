/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus aprostrophe (')
// And words are separated by non-letters-aprostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/


import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;

public class Final_01 {
    private static boolean isWordChar(char c) {
        return
            ('a' <= c && c <= 'z') ||
            ('A' <= c && c <= 'Z') ||
            c == '\'';
    }

    private static char toLower(char c) {
        if ('A' <= c && c <= 'Z') {
            return (char)(c - 'A' + 'a');
        } else {
            return c;
        }
    }
	
	static LnStrm<FnList<Character>> pg2701_word$strmize() {
	// HX-2026-05-04:
	// Please construct a stream of words contained in the
	// file Data/pg2701.txt
	// Note that a word is represented as a list of characters
	// in the English alphabet plus aprostrophe (')
	// Also every upper case letter in the original text should
	// be turned into its corresponding lower case.
	// This stream should be built on top of pg2701_char$strmize
	// which is already implemented in Final_00.
	// In particular, you should NOT use Java library functions
	// for processing files!
        LnStrm<Character> chars = Final_00.pg2701_char$strmize();

        FnList<FnList<Character>> wordsRev = FnListSUtil.nil();
        FnList<Character> wordRev = FnListSUtil.nil();

        LnStcn<Character> cell = chars.eval0();

        while (cell.consq()) {
            char c = cell.hd();

            if (isWordChar(c)) {
                wordRev = FnListSUtil.cons(toLower(c), wordRev);
            } else {
                if (wordRev.consq()) {
                    FnList<Character> word = FnListSUtil.reverse(wordRev);
                    wordsRev = FnListSUtil.cons(word, wordsRev);
                    wordRev = FnListSUtil.nil();
                }
            }

            chars = cell.tl();
            cell = chars.eval0();
        }

        if (wordRev.consq()) {
            FnList<Character> word = FnListSUtil.reverse(wordRev);
            wordsRev = FnListSUtil.cons(word, wordsRev);
        }

        FnList<FnList<Character>> words =
            FnListSUtil.reverse(wordsRev);

        return FnListSUtil.strmize(words);
    }

    private static void printWord(FnList<Character> word) {
        while (word.consq()) {
            System.out.print(word.hd());
            word = word.tl();
        }
    }

    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$strmize()
        LnStrm<FnList<Character>> words = pg2701_word$strmize();

        for (int i = 0; i < 30; i += 1) {
            LnStcn<FnList<Character>> cws =
                words.eval0();

            if (cws.nilq()) {
                break;
            }

            printWord(cws.hd());
            System.out.println();

            words = cws.tl();
        }

        return /*void*/;
    }
}
