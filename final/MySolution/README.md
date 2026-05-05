## Final_00

N/A

## Final_01

Solved with testing

Scaned the character stream from Final_00 one character at a time and grouped consecutive word characters into words. Each word is built using FnList.cons, so it is built backward and then reversed when a separator is reached. Completed words are collected into a list, the full list is reversed back into the original order, and then converted into an LnStrm.

## Final_02 (50 points)

Solved with testing

First, turned the word stream from Final_01 into an array. Then used quicksort to sort the words alphabetically, which makes identical words appear next to each other. After that, scan the sorted array once to count each equal words. Finally, use merge sort on the word-count pairs so the most frequent words appear first, with alphabetical order breaking ties.

## Final_03 (50 points)

Solved with testing

Final_03 uses Final_01 to stream all words. Each word is converted into a String key, then counted using the Assign08_02 open-addressing hash map. After counting, the hash-map entries are converted into a FnList of word-count pairs, then sorted by highest count first, with alphabetical order used to break ties.

## Final_04 (50 points)

Solved with testing

Used Final_01 to produce the word stream. For each word, convert it to a String key and store it in a randomized BST-style map. If the word is already in the map, update its count; otherwise, insert it with count 1. After all words are counted, convert the tree into a list of word-count pairs, then use mergeSort to sort by highest count first, breaking ties alphabetically.

## Final_05 (50 points)

Solved with testing

Split the linear list into 100 consecutive sublists. Recursively sort each sublist, then merge the 100 sorted lists using a priority queue that always selects the smallest current head. To avoid creating new linear-list nodes, each selected head node is detached from its original list with unlink1() and linked directly onto the result with link1(). Stability is preserved by breaking ties using the original sublist index, so equal elements from earlier sublists stay before equal elements from later sublists.

## Final_06 (50 BONUS points)

incomplete