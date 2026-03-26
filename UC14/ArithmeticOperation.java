package org.example.UC14;

import java.util.function.DoubleBinaryOperator;

public enum ArithmeticOperation {
    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    DIVIDE((a, b) -> {
        if (Math.abs(b) < 1e-9) {
            throw new ArithmeticException("Cannot divide by zero quantity");
        }
        return a / b;
    });
    private final DoubleBinaryOperator operation;
    ArithmeticOperation(DoubleBinaryOperator operation) {
        this.operation = operation;
    }
    public double compute(double a, double b) {
        return operation.applyAsDouble(a, b);
    }
}