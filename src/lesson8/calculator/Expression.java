package lesson8.calculator;

public class Expression {
    private final Operation operation;

    private double leftOperator;

    private double rightOperator;

    private Expression leftExpression;

    private Expression rightExpression;

    private Double result;

    public Expression(final Operation operation, final double leftOperator, final double rightOperator) {
        this.operation = operation;
        this.leftOperator = leftOperator;
        this.rightOperator = rightOperator;
    }

    public double getResult() {
        if (this.result == null) {
            calc();
        }

        return this.result;
    }

    public void calc() {
        result = operation.operate(leftOperator, rightOperator);
        updateDependence();
    }

    public Operation getOperation() {
        return operation;
    }

    public double getLeftOperator() {
        return leftOperator;
    }

    public double getRightOperator() {
        return rightOperator;
    }

    protected void updateDependence() {
        if (leftExpression != null) {
            leftExpression.rightOperator = result;
            leftExpression.setRightExpression(rightExpression);
        }
        if (rightExpression != null) {
            rightExpression.leftOperator = result;
            rightExpression.setLeftExpression(leftExpression);
        }
    }

    public void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }
}
