package lesson2;

import java.util.Scanner;

/**
 * Перевод секунд в часы
 */
public class Task3 {
    public static void main(String[] args) {
        Scanner systemInScanner = new Scanner(System.in);
        System.out.println("Введите количество секунд:");
        int secondsCount = systemInScanner.nextInt();

        System.out.println("Итого: " + secondsCount + " секунд = " + (secondsCount / (60.0 * 60.0)) + " час");
    }
}