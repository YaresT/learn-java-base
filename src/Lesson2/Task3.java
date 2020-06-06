package Lesson2;

/**
 * Перевод секунд в часы
 */
public class Task3 {
    public static void main(String[] args) {
        if (args.length > 0) {
            int secondsCount = Integer.parseInt(args[0]);
            System.out.println("Итого: " + (secondsCount / (60.0 * 60.0)) + " час");
        } else {
            System.out.println("Ошибка: Не указаны начальные данные");
        }
    }
}