package lesson8.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    protected Operation[] availableOperation;

    public Calculator(Operation[] availableOperation) {
        this.availableOperation = availableOperation;
    }

    public double calc(String expressionLine) {
        expressionLine = cleanExpression(expressionLine);

        try {
            return calcBracketLine(expressionLine);
        } catch (CalculationException e) {
            System.out.println(e.getMessage());
        }

        return 0.0;
    }

    private String cleanExpression(String expression) {
        return expression.trim().replaceAll("\\s+", " ");
    }

    private double calcBracketLine(String startExpressionLine) throws CalculationException {
        ArrayDeque<Integer> bracketPositions = new ArrayDeque<>();
        StringBuilder expressionLine = new StringBuilder(startExpressionLine);
        double bracketResult;
        int leftBracketPos;
        int rightBracketPos;

        do {
            rightBracketPos = 0;
            for (int i = 0; i < expressionLine.length(); i++) {
                if (expressionLine.charAt(i) == '(') {
                    bracketPositions.push(i);
                } else if (expressionLine.charAt(i) == ')') {
                    rightBracketPos = i;
                    break;
                }
            }
            if (rightBracketPos > 0) {
                if (bracketPositions.isEmpty()) {
                    throw new CalculationException("Ошибка! В выражении количество ')' больше чем '('");
                }

                leftBracketPos = bracketPositions.pop();

                bracketResult = calcSimpleLine(expressionLine.substring(leftBracketPos + 1, rightBracketPos));
                expressionLine.delete(leftBracketPos, rightBracketPos + 1);
                expressionLine.insert(leftBracketPos, bracketResult);

                bracketPositions.clear();
            } else {
                if (!bracketPositions.isEmpty()) {
                    throw new CalculationException("Ошибка! В выражении количество '(' больше чем ')'");
                }
            }
        } while (rightBracketPos > 0);

        return calcSimpleLine(expressionLine.toString());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Введите выражение(каждый операнд должен быть отделён через пробел):");
                System.out.printf("Результат = %.2f%n%n", calc(scanner.nextLine()));
            } catch (Exception exception) {
                System.out.println("Ошибка! Некорректное выражение");
            }
        } while (true);
    }

    private double calcSimpleLine(String expressionLine) throws CalculationException {
        if (expressionLine == null || expressionLine.isEmpty()) {
            return 0.0;
        }

        Operation currentOperation;

        String[] lineParts = expressionLine.split("\\s+");

        if (lineParts.length != 3 && (lineParts.length - 3) % 2 != 0) {
            throw new CalculationException("Ошибка! Некорректное выражение");
        }

        int length = ((lineParts.length - 3) / 2) + 1;

        ArrayList<Expression> lineExpressions = new ArrayList<>(length);

        {
            Expression currentExpression;
            Expression previousExpression = null;

            for (int i = 0; i < (lineParts.length - 1); i += 2) {
                if (!operatorIsNumber(lineParts[i]) || !operatorIsNumber(lineParts[i + 2])) {
                    throw new CalculationException("Ошибка! Некорректное выражение");
                }

                currentOperation = null;
                for (Operation operation : availableOperation) {
                    if (lineParts[i + 1].equals(operation.getSymbol())) {
                        currentOperation = operation;
                    }
                }
                if (currentOperation == null) {
                    throw new CalculationException("Ошибка! Неподдерживаемая операция");
                }

                currentExpression = new Expression(
                        currentOperation,
                        Double.parseDouble(lineParts[i]),
                        Double.parseDouble(lineParts[i + 2])
                );

                if (previousExpression != null) {
                    previousExpression.setRightExpression(currentExpression);
                    currentExpression.setLeftExpression(previousExpression);
                }

                lineExpressions.add(currentExpression);

                previousExpression = currentExpression;
            }
        }


        lineExpressions.sort((o1, o2) -> o2.getOperation().getPriority() - o1.getOperation().getPriority());

        for (Expression expression : lineExpressions) {
            expression.calc();
        }

        return lineExpressions.get(lineExpressions.size() - 1).getResult();
    }

    private static boolean operatorIsNumber(String operator) {
        return operator.matches("[0-9]+\\.?[0-9]*");
    }
}
