package org.example.UC8;

public class UC8_StandaloneEnum {
    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit unit1, double v2, LengthUnit unit2){
        Length l1 = new Length(v1, unit1);
        Length l2 = new Length(v2, unit2);

        return demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(double v, LengthUnit fromUnit, LengthUnit toUnit){
        Length l = new Length(v, fromUnit);

        return l.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length l, LengthUnit toUnit){
        return l.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2){
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit){
        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);

        System.out.println("Equality = " + demonstrateLengthEquality(l1, l2));

        System.out.println("Comparison = " + demonstrateLengthComparison(3, LengthUnit.FEET, 1, LengthUnit.YARDS));

        System.out.println("Conversion = " + demonstrateLengthConversion(10, LengthUnit.YARDS, LengthUnit.INCHES));

        System.out.println("Conversion = " + demonstrateLengthConversion(l1, LengthUnit.CENTIMETERS));

        System.out.println("Addition = " + demonstrateLengthAddition(l1 , l2));

        System.out.println("Addition = " + demonstrateLengthAddition(l1, l2, LengthUnit.YARDS));
    }
}