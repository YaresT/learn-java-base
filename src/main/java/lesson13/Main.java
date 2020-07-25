package lesson13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Main {
    static volatile int sharedValue;

    static Logger log = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
        log.trace("Start main");

        try {
            Thread thread1 = getFirstThread();
            Thread thread2 = getSecondThread();

            thread2.start();

            thread1.start();
            log.trace("Thread stared");

            thread1.join();
            log.trace("Thread 1 interrupted");

            thread2.interrupt();
            log.trace("Thread 2 interrupted");
        } catch (InterruptedException e) {
            log.fatal(e);
        }

        log.trace("End main");
    }

    public static Thread getFirstThread() {
        return new Thread() {
            @Override
            public void run() {
                log.trace("Thread 1 started");

                int attempt = 0;
                try {
                    while (!isInterrupted()) {
                        try {
                            if (sharedValue > 1) {
                                sharedValue = sharedValue - 1;
                                System.out.println("Некая полезная информация: " + 10 / sharedValue);
                            }
                        } catch (ArithmeticException e) {
                            log.error("Race conditional error");
                            if (attempt < 5) {
                                sleep(2000);
                                log.warn("Thread 1 new attempt");
                                attempt++;
                            } else {
                                interrupt();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    log.fatal(e);
                }

                log.trace("Thread 1 exit");
            }
        };
    }

    public static Thread getSecondThread() {
        return new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    sharedValue = new Random().nextInt(10);
                }
            }
        };
    }
}