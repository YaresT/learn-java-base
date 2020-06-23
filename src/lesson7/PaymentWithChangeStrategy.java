package lesson7;

abstract class PaymentWithChangeStrategy extends PaymentStrategy {
    @Override
    public abstract void abortPay();

    public abstract boolean change();
}
