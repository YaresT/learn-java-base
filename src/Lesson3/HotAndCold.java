package Lesson3;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HotAndCold {

    private static final int RAND_TOP_BOUND = 100;

    protected int randDigit;

    protected Scanner inScanner;

    HotAndCold() {
        this.randDigit = new Random().nextInt(RAND_TOP_BOUND);
        System.out.println(this.randDigit);

        this.inScanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        HotAndCold game = new HotAndCold();
        game.start();
    }

    protected void outRedMessage(String message) {
        this.outColorMessage(ConsoleColor.RED, message);
    }

    protected void outBlueMessage(String message) {
        this.outColorMessage(ConsoleColor.BLUE, message);
    }

    protected void outColorMessage(ConsoleColor color, String message) {
        System.out.printf("%s%s%s", color, message, ConsoleColor.RESET);
    }

    protected void start() {
        int currentDistance;
        int previousDigitDistance = RAND_TOP_BOUND / 2;
        this.showHelpTooltip();

        do {
            currentDistance = Math.abs(this.getUserDigit() - this.randDigit);

            if (currentDistance == 0) {
                this.outRedMessage("Очень горячо! Угадал");
                return;
            }

            if (currentDistance <= previousDigitDistance) {
                this.outRedMessage("Горячо\n");
                previousDigitDistance = currentDistance;
            } else {
                this.outBlueMessage("Холодно\n");
            }
        } while (true);
    }

    protected int getUserDigit() {
        while (true) {
            System.out.print("\nВведите число: ");
            try {
                return this.inScanner.nextInt();
            } catch (InputMismatchException exception) {
                String str = inScanner.nextLine();

                if (str.equals("h") || str.equals("help") || str.equals("?")) {
                    this.showHelp();
                } else {
                    this.showHelpTooltip();
                }
            }
        }
    }

    protected void showHelpTooltip() {
        System.out.println("Введите h help ? для вызова помощи\n");
    }

    protected void showHelp() {
        System.out.println("Читай официальную документацию, бог в помощь\n");
    }

    enum ConsoleColor {
        RESET("\033[0m"),
        RED("\033[0;31m"),
        BLUE("\033[0;34m");

        private final String code;

        ConsoleColor(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
