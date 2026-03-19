package org.example.UC7;

public class UC7 {
    public static Length demonstrateLengthAddition(Length l1, Length l2, Length.LengthUnit targetUnit){
        if(l1 == null || l2 == null){
            throw new IllegalArgumentException("Length cannot be null");
        }

        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {
        Length l1 = new Length(1, Length.LengthUnit.FEET);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        System.out.println(demonstrateLengthAddition(l1, l2, Length.LengthUnit.YARDS));
    }
}

class Length{
    private double v;
    private LengthUnit unit;

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

    public Length(double v, Length.LengthUnit unit){
        this.v=v;
        this.unit=unit;
    }

    public double convertToBaseUnit(){
        return v * unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

        Length l = (Length) obj;
        return Double.compare(this.convertToBaseUnit() , l.convertToBaseUnit()) == 0;
    }

    public Length convertTo(Length.LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target Unit cannot be null");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = convertFromBaseToTargetUnit(baseValue, targetUnit);

        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length thatLength, LengthUnit targetUnit){
        if(this == null || thatLength == null){
            throw new IllegalArgumentException("Length cannot be null");
        }
        double i1 = this.convertToBaseUnit();
        double i2 = thatLength.convertToBaseUnit();

        double sum = i1 + i2;

        double ans = sum / targetUnit.getConversionFactor();
        return new Length(ans, targetUnit);
    }

    public double convertFromBaseToTargetUnit(double baseValue, Length.LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target Unit cannot be null");
        }
        return baseValue / targetUnit.getConversionFactor();
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", v, unit);
    }
}
