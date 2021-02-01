public class Palindrome {

    /** Stores each character of a word in a deque
     * @param word of which characters to be stored in a deque
     * @return a deque to store these characters
     * */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }

        return dq;
    }

    /** Check whether a word is palindrome
     * @param word to be analysed
     * @return true if the word is palindrome; false otherwise
     * */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() == 1 || word.size() == 0) {
            return true;
        }

        if (word.removeFirst() == word.removeLast()) {
            return isPalindrome(word);
        }

        return false;
    }

    /** Check whether a word is palindrome with a char comparator
     * @param word to be analysed
     * @param cc to be used to compare characters in word
     * @return true if the word is palindrome; false otherwise
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || cc == null) {
            return false;
        }

        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }

        if (cc.equalChars(word.removeFirst(), word.removeLast())) {
            return isPalindrome(word, cc);
        }

        return false;
    }
}
