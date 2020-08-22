package lesson31;

public class Main {
    public static void main(String[] args) {
        String test = "123321";
        System.out.println(StringPalindromeChecker.isPalindrome(test));
        test = "Test";
        System.out.println(StringPalindromeChecker.isPalindromeWithBuilder(test));
        test = "мадам";
        System.out.println(StringPalindromeChecker.isPalindrome(test));
    }
}
