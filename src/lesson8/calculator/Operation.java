package lesson8.calculator;

public interface Operation {
    double operate(double left, double right);

    String getSymbol();

    String getTooltip();

    int getPriority();
}
