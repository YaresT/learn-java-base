package lesson2;

import java.util.Scanner;

/**
 * Подсчёт зарплаты с учётом НДФЛ
 */
public class Task2 {
    public static void main(String[] args) {
        double ndflCorrection = 1.0 - 0.13;

        Scanner systemInScanner = new Scanner(System.in);
        System.out.println("Введите зарплату:");
        double salary = systemInScanner.nextDouble();

        System.out.println("Итоговая зарплата: " + (salary * ndflCorrection) + " рублей.");
    }
}