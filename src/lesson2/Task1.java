package lesson2;

import java.util.Scanner;

/**
 * Подсчёт стоимости бензина
 */
public class Task1 {
    public static void main(String[] args) {
        int gasPrice = 55;
        System.out.println("Стоимость за 1 литр составляет " + gasPrice + " рублей.");

        Scanner systemInScanner = new Scanner(System.in);
        System.out.println("Введите количество литров:");
        int gasCount = systemInScanner.nextInt();

        System.out.println("Стоимость " + gasCount + " л. составляет " + (gasPrice * gasCount) + " рублей.");
    }
}