package org.example.UC11;


public interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double v);
    double convertFromBaseUnit(double v);
    String getUnitName();
}