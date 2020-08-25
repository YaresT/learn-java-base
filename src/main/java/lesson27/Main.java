package lesson27;

public class Main {
    public static void main(final String[] args) {
        System.out.println(fibonacciNative(10));
        System.out.println(fibonacciRecursive(10));
    }

    public static int fibonacciRecursive(final int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciNative(final int n) {
        if (n <= 1) {
            return n;
        }

        int previous = 0;
        int next = 1;
        int sum;

        for (int i = 2; i <= n; i++) {
            sum = previous;
            previous = next;
            next = sum + previous;
        }

        return next;
    }
}
