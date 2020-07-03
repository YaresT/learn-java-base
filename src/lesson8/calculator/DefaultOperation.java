package lesson8.calculator;

public enum DefaultOperation implements Operation {
    PLUS("+", "сложение", 2) {
        @Override
        public double operate(double left, double right) {
            return left + right;
        }
    },
    MINUS("-", "вычитание", 2) {
        @Override
        public double operate(double left, double right) {
            return left - right;
        }
    },
    DIVISION("/", "деление", 5) {
        @Override
        public double operate(double left, double right) {
            return left / right;
        }
    },
    ABS("^", "деление", 5) {
        @Override
        public double operate(double left, double right) {
            return left / right;
        }
    },
    MULTIPLICATION("*", "умножение", 5) {
        @Override
        public double operate(double left, double right) {
            return left * right;
        }
    };

    private final String symbol;

    private final String tooltip;

    private final int priority;

    DefaultOperation(String symbol, String tooltip, int priority) {
        this.symbol = symbol;
        this.tooltip = tooltip;
        this.priority = priority;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
