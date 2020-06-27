package lesson7;

import java.util.Scanner;

public class CashPaymentStrategy extends PaymentWithChangeStrategy {
    int innerMoney;

    @Override
    boolean pay() {
        showPayInfo();
        Scanner scanner = new Scanner(System.in);
        int insertMoney = 0;

        try {
            do {
                System.out.println("Введите деньги в купюроприёмник...");
                insertMoney += scanner.nextInt();
                if (insertMoney > product.getPrice()) {
                    System.out.println("Оплата прошла успешно\n");
                    innerMoney = insertMoney - product.getPrice();
                    return true;
                } else {
                    System.out.printf("Внесено %d из %d рублей%n", insertMoney, product.getPrice());
                }
            } while (true);
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public void abortPay() {
        System.out.printf("Ошибка!%n");
        change();
    }

    @Override
    public String getName() {
        return "Оплата наличными";
    }

    @Override
    public boolean change() {
        if (innerMoney >= 0) {
            System.out.printf("Ваша сдача %d рублей%n%n", innerMoney);
            innerMoney = 0;
            return true;
        } else {
            return false;
        }
    }
}
