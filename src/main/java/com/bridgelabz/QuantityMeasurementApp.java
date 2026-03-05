package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Add: " + length1.add(length2));
        System.out.println("Subtract: " + length1.subtract(length2));
        System.out.println("Divide: " +
                length1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("Weight Add: " + weight1.add(weight2));
        System.out.println("Weight Subtract: " + weight1.subtract(weight2));
        System.out.println("Weight Divide: " +
                weight1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume Add: " + volume1.add(volume2));
        System.out.println("Volume Subtract: " + volume1.subtract(volume2));
        System.out.println("Volume Divide: " +
                volume1.divide(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }
}