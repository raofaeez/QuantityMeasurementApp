package org.example.UC11;

public class UC11_VolumeMeasurement {
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        double convertedValue = quantity.convertTo(targetUnit);
        return new Quantity<>(convertedValue, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Volume Equality = " + demonstrateEquality(v1, v2));

        System.out.println("Volume Conversion = " + demonstrateConversion(v1, VolumeUnit.MILLILITRE));

        System.out.println("Volume Addition = " + demonstrateAddition(v1, v2));

        System.out.println("Volume Addition (mL) = " + demonstrateAddition(v1, v3, VolumeUnit.MILLILITRE));
    }
}