package lesson7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachine {

    PaymentStrategy[] availablePayments;

    public VendingMachine(final PaymentStrategy[] availablePayments) {
        this.availablePayments = availablePayments;
    }

    public static void main(String[] args) {
        PaymentStrategy[] strategies = new PaymentStrategy[]{new CashPaymentStrategy(), new CardPaymentStrategy()};
        VendingMachine vendingMachine = new VendingMachine(strategies);
        vendingMachine.run();
    }

    protected void showProductsMenu() {
        System.out.println("Товары:");
        for (VendingProduct product : VendingProduct.values()) {
            System.out.printf("%s - %d рублей, код - %d%n", product.getName(), product.getPrice(), product.getCode());
        }
        System.out.println();
    }

    protected VendingProduct getChosenProduct() {
        showProductsMenu();

        int productCode;

        do {
            productCode = scanRequiredInt("Введите код продукта:");

            for (VendingProduct product : VendingProduct.values()) {
                if (product.getCode() == productCode) {
                    return product;
                }
            }
        } while (true);
    }

    public void run() {
        do {
            try {
                sellProduct();
            } catch (Exception ignored) {
                return;
            }
        } while (true);
    }

    protected void sellProduct() {
        VendingProduct chosenProduct = getChosenProduct();

        PaymentStrategy paymentStrategy = getChoosePaymentStrategy();
        paymentStrategy.setProduct(chosenProduct);

        boolean successful = paymentStrategy.pay();

        if (successful && paymentStrategy instanceof PaymentWithChangeStrategy) {
            successful = ((PaymentWithChangeStrategy) paymentStrategy).change();
        }

        if (successful) {
            vendChosenProduct(chosenProduct);
        } else {
            paymentStrategy.abortPay();
        }
    }

    protected void vendChosenProduct(VendingProduct chosenProduct) {
        System.out.printf("Выдача товара №%d - %s...%n", chosenProduct.getCode(), chosenProduct.getName());
        System.out.printf("Успешно. Приходите ещё!%n%n");
    }

    protected PaymentStrategy getChoosePaymentStrategy() {
        showPaymentStrategiesMenu();

        int strategyNum;

        do {
            strategyNum = scanRequiredInt("Введите номер способа оплаты:") - 1;
            for (int i = 0; i < availablePayments.length; i++) {
                if (strategyNum == i) {
                    return availablePayments[i];
                }
            }
        } while (true);
    }

    protected int scanRequiredInt(final String infoMessage) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(infoMessage);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException ignored) {
            }
            System.out.println("\n");
        } while (true);
    }

    protected void showPaymentStrategiesMenu() {
        System.out.println("Способы оплаты:");
        for (int i = 0; i < availablePayments.length; i++) {
            System.out.printf("%d - %s%n", i + 1, availablePayments[i].getName());
        }
        System.out.println();
    }
}
