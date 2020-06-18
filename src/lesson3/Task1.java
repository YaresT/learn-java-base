package lesson3;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task1 {

    protected Scanner inScanner;

    protected Task1() {
        this.inScanner = new Scanner(System.in);
    }

    enum Menu {
        CONVERT_SECONDS(1, "Конвертация в секунды") {
            public void action(Task1 program) {
                program.convertSecondsToHours();
            }
        },
        CALCULATE_SALARY(2, "Подсчёт ЗП") {
            public void action(Task1 program) {
                program.calculateSalary();
            }
        },
        CALCULATE_GAS(3, "Подсчёт стоимости бензина") {
            public void action(Task1 program) {
                program.calculateGasPrice();
            }
        },
        EXIT(0, "Выход из программы") {
            public void action(Task1 program) {
                throw new UnsupportedOperationException();
            }
        };

        private final int num;

        private final String description;

        public abstract void action(Task1 program);

        Menu(int num, String description) {
            this.num = num;
            this.description = description;
        }

        public int getNum() {
            return this.num;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static void main(String[] args) {
        new Task1().run();
    }

    protected void run() {
        int operation;
        Menu menuAction;

        do {
            this.showMenu();
            operation = this.<Integer>readFromInputDefault(
                    "\nВыберите нужную программу:",
                    Menu.EXIT.getNum(),
                    Integer.class
            );

            menuAction = null;
            for (Menu item : Menu.values()) {
                if (operation == item.getNum()) {
                    menuAction = item;
                    break;
                }
            }

            if (menuAction == null || menuAction == Menu.EXIT) {
                return;
            }

            menuAction.action(this);
        } while (true);
    }

    protected void showMenu() {
        System.out.println("\nМеню");
        this.showCurrentDate();
        for (Menu item : Menu.values()) {
            System.out.println(item.getNum() + " " + item.getDescription());
        }
    }

    protected void showCurrentDate() {
        Instant nowUtc = Instant.now();
        ZoneId europeMoscow = ZoneId.of("Europe/Moscow");
        ZoneId asiaSingapore = ZoneId.of("Asia/Singapore");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy HH:mm");
        System.out.printf(
                "Текущее время:%n%s - %s%n%s - %s%n%n",
                europeMoscow.toString(),
                ZonedDateTime.ofInstant(nowUtc, europeMoscow).format(formatter),
                asiaSingapore.toString(),
                ZonedDateTime.ofInstant(nowUtc, asiaSingapore).format(formatter)
        );
    }

    protected void calculateSalary() {
        double salary = this.<Double>readFromInputDefault(
                "Введите зарплату:",
                1000.0,
                Double.class
        );

        System.out.printf("Итоговая зарплата: %.2f рублей%n", salary * (1.0 - 0.13));
    }

    protected void convertSecondsToHours() {
        Integer secondsCount = this.<Integer>readFromInputDefault(
                "Введите количество секунд:",
                3600,
                Integer.class
        );

        System.out.printf("Итого: %d секунд = %.2f часов%n", secondsCount, secondsCount / (60.0 * 60.0));
    }

    protected void calculateGasPrice() {
        int gasPrice = 55;
        System.out.printf("Стоимость за 1 литр составляет %d рублей%n", gasPrice);

        Integer gasCount = this.<Integer>readFromInputDefault(
                "Введите количество литров:",
                100,
                Integer.class
        );

        System.out.printf("Стоимость %d л. составляет %d рублей%n", gasCount, gasPrice * gasCount);
    }

    protected <T> T readFromInputDefault(String message, T defaultValue, Class<T> c) {
        System.out.println(message);
        try {
            if (c == Integer.class) {
                return c.cast(this.inScanner.nextInt());
            }
            if (c == String.class) {
                return c.cast(this.inScanner.nextLine());
            }
            if (c == Double.class) {
                return c.cast(this.inScanner.nextDouble());
            }
            if (c == Float.class) {
                return c.cast(this.inScanner.nextFloat());
            }
        } catch (Exception ignored) {
        }
        return defaultValue;
    }
}