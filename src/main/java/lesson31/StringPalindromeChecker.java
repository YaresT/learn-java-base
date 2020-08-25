package lesson31;

public class StringPalindromeChecker {
    private StringPalindromeChecker() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isPalindrome(final String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != string.charAt(string.length() - (i + 1))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindromeWithBuilder(final String string) {
        return (new StringBuilder(string).reverse().toString()).equals(string);
    }
}
