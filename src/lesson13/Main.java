package lesson13;

public class Main {
    public static void main(String[] args) {
        Runnable first = new FirstThread();
        Thread firstThread = new Thread(first);

        Runnable second = new SecondThread(firstThread);

        new Thread(second).start();

        firstThread.start();
    }
}
