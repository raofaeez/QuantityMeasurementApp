package org.example.UC12;

public class Quantity<U extends IMeasurable> {
    private final double v;
    private final U unit;

    public Quantity(double v, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.v = v;
        this.unit = unit;
    }

    public double getValue() {
        return v;
    }
    public U getUnit() {
        return unit;
    }

    public double convertTo(U targetUnit) {
        if (!unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Incompatible units");
        }

        double baseValue = this.unit.convertToBaseUnit(this.v);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return Math.round(converted * 100.0) / 100.0;
    }

    public Quantity<U> add(Quantity<U> v) {
        return add(v, this.unit);
    }

    public Quantity<U> add(Quantity<U> v2, U targetUnit) {
        if (!this.unit.getClass().equals(v2.unit.getClass())) {
            throw new IllegalArgumentException("Cannot add different unit types");
        }

        double base1 = this.unit.convertToBaseUnit(this.v);
        double base2 = v2.unit.convertToBaseUnit(v2.v);

        double sumBase = base1 + base2;

        double ans = targetUnit.convertFromBaseUnit(sumBase);
        ans = Math.round(ans * 100.0) / 100.0;

        return new Quantity<>(ans, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> v) {
        return subtract(v, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> v2, U targetUnit) {
        if (!this.unit.getClass().equals(v2.unit.getClass())) {
            throw new IllegalArgumentException("Cannot subtract different unit types");
        }

        double base1 = this.unit.convertToBaseUnit(this.v);
        double base2 = v2.unit.convertToBaseUnit(v2.v);

        double diffBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(diffBase);
        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    public double divide(Quantity<U> v2) {
        if (!this.unit.getClass().equals(v2.unit.getClass())) {
            throw new IllegalArgumentException("Cannot divide different unit types");
        }
        double base1 = this.unit.convertToBaseUnit(this.v);
        double base2 = v2.unit.convertToBaseUnit(v2.v);
        if (base2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return base1 / base2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof UC10.Quantity<?>)) return false;

        Quantity<?> that = (Quantity<?>) obj;

        double base1 = this.unit.convertToBaseUnit(this.v);
        double base2 = that.unit.convertToBaseUnit(that.v);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(this.v);
        long rounded = Math.round(baseValue * 100);
        return Long.hashCode(rounded);
    }

    @Override
    public String toString(){
        return String.format("%.2f %s", v, unit);
    }
}