package lesson7;

import java.util.Scanner;

public class CardPaymentStrategy extends PaymentStrategy {
    @Override
    boolean pay() {
        showPayInfo();
        System.out.println("Введите карту...");
        System.out.println("Введите пин код...");
        Scanner scanner = new Scanner(System.in);

        try {
            scanner.nextInt();
            System.out.println("Оплата прошла успешно\n");
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public void abortPay() {
        System.out.println("Ошибка при оплате");
    }

    @Override
    public String getName() {
        return "Оплата банковской картой";
    }
}
