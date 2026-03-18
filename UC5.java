package org.example.UC5;

public class UC5 {
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length length = new Length(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        if (length == null || toUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {
        Length l1 = new Length(12, Length.LengthUnit.INCHES);
        Length l2 = new Length(1, Length.LengthUnit.FEET);

        System.out.println("Equality Check:");
        System.out.println(l1 + " == " + l2 + " - " + demonstrateLengthEquality(l1, l2));

        Length converted = demonstrateLengthConversion(2, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);

        System.out.println("Conversion:");
        System.out.println("2 FEET = " + converted);

        Length l3 = new Length(36, Length.LengthUnit.INCHES);

        Length yardConversion = demonstrateLengthConversion(l3, Length.LengthUnit.YARDS);

        System.out.println("Overloaded Conversion:");
        System.out.println(l3 + " = " + yardConversion);
    }
}

class Length{
    private final double v;
    private final LengthUnit unit;

    public enum LengthUnit{
        INCHES(1),
        FEET(12),
        YARDS(36),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }

        public double getConversionFactor(){
            return this.conversionFactor;
        }
    }

    public Length(double v, LengthUnit unit){
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.v=v;
        this.unit=unit;
    }

    private double convertToBaseUnit(){
        double inches = v * unit.getConversionFactor();
        return Math.round(inches);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

        Length l = (Length) obj;
        return Double.compare(this.convertToBaseUnit(), l.convertToBaseUnit()) == 0;
    }

    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        convertedValue = Math.round(convertedValue);

        return new Length(convertedValue, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", v, unit);
    }
}
