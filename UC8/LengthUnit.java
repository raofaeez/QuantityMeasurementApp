package org.example.UC8;

public enum LengthUnit {
    FEET(1),
    INCHES(1.0/12),
    YARDS(3),
    CENTIMETERS(0.0328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor){
        this.conversionFactor=conversionFactor;
    }

    public double getConversionFactor(){
        return conversionFactor;
    }

    public double convertToBaseUnit(double v){
        return v * this.getConversionFactor();
    }

    public double converFromBaseUnit(double v){
        return v / this.getConversionFactor();
    }
}
