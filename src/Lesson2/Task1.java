package Lesson2;

/**
 * Подсчёт стоимости бензина
 */
public class Task1 {
    public static void main(String[] args) {
        if (args.length > 0) {
            int gasCount = Integer.parseInt(args[0]);
            int gasPrice = 55;
            System.out.println("Стоимость " + gasCount + " л. составляет " + (gasPrice * gasCount) + " рублей.");
        } else {
            System.out.println("Ошибка: Не указаны начальные данные");
        }
    }
}