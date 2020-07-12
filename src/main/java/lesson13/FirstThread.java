package lesson13;

public class FirstThread implements Runnable {
    @Override
    public void run() {
        do {
            System.out.println("First");
        } while (true);
    }
}
