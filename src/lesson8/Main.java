package lesson8;

import lesson8.calculator.Calculator;
import lesson8.calculator.DefaultOperation;

public class Main {
    public static void main(String[] args) {
        new Calculator(DefaultOperation.values()).run();
    }
}
