package Lesson2;

/**
 * Подсчёт зарплаты с учётом НДФЛ
 */
public class Task2 {
    public static void main(String[] args) {
        if (args.length > 0) {
            double salary = Double.parseDouble(args[0]);
            double ndflCorrection = 1.0 - 0.13;
            System.out.println("Итоговая зарплата: " + (salary * ndflCorrection) + " рублей.");
        } else {
            System.out.println("Ошибка: Не указаны начальные данные");
        }
    }
}