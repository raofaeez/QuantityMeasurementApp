package org.example.UC14;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 1e-6;
    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }
    public Quantity<U> convertTo(U targetUnit) {
        if (unit instanceof TemperatureUnit) {
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity<>(round(converted), targetUnit);
        }
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(converted), targetUnit);
    }
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new  Quantity<>(round(result), targetUnit);
    }
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }
    public Quantity<U> subtract( Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new  Quantity<>(round(result), targetUnit);
    }
    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean isTargetRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (isTargetRequired) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            if (!this.unit.getClass().equals(targetUnit.getClass())) {
                throw new IllegalArgumentException("Target unit must match category");
            }
        }
    }
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        this.unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());
        double baseThis = this.unit.convertToBaseUnit(this.value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseThis, baseOther);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Math.abs(base1 - base2) < EPSILON;
    }
    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Double.hashCode(Math.round(base / EPSILON));
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}