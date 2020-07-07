package lesson13;

public class SecondThread implements Runnable {
    private final Thread first;

    public SecondThread(Thread first) {
        this.first = first;
    }

    @Override
    public void run() {
        int i = 0;
        do {
            if (i > 5) {
                System.out.println("Interrupt first thread");
                first.interrupt();
            } else {
                i++;
            }

            System.out.println("Second");
        } while (true);
    }
}
