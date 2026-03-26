package org.example.UC14;

public class UC14_TemperatureMeasurement {
    public static void main(String[] args) {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("Temp Equality: " + t1.equals(t2));
        System.out.println("Convert: " + t1.convertTo(TemperatureUnit.FAHRENHEIT));

        try {
            t1.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}