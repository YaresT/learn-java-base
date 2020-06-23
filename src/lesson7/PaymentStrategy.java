package lesson7;

abstract class PaymentStrategy {
    VendingProduct product;

    void setProduct(VendingProduct product) {
        this.product = product;
    }

    void showPayInfo() {
        System.out.printf("Оплата %s - %d рублей%n", product.getName(), product.getPrice());
    }

    abstract boolean pay();

    abstract void abortPay();

    abstract String getName();
}
