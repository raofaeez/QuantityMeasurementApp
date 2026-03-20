package org.example.UC8;

public class Length {
    private final double v;
    private final
    LengthUnit unit;

    public Length(double v, LengthUnit unit){
        this.v=v;
        this.unit=unit;
    }

    public Length convertTo(LengthUnit targetUnit){
        double baseValue = this.unit.convertToBaseUnit(this.v);
        double convertedValue = targetUnit.converFromBaseUnit(baseValue);

        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length thatLength){
        return addAndConvert(thatLength, this.unit);
    }

    public Length add(Length l, LengthUnit targetUnit){
        return addAndConvert(l, targetUnit);
    }

    private Length addAndConvert(Length l, LengthUnit targetUnit){
        double f1 = this.unit.convertToBaseUnit(this.v);
        double f2 = l.unit.convertToBaseUnit(l.v);

        double sum = f1 + f2;

        double ans = targetUnit.converFromBaseUnit(sum);

        return new Length(ans, targetUnit);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || !(obj instanceof Length)) return false;

        Length l = (Length) obj;
        return Double.compare(this.unit.convertToBaseUnit(this.v), l.unit.convertToBaseUnit(l.v)) == 0;
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
