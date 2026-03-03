package com.bridgelabz;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        validateValue(value);
        validateUnit(unit);
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------------- EQUALITY ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), unit.convertToBaseUnit(value));
    }

    // ---------------- CONVERSION ----------------

    public Quantity<U> convertTo(U targetUnit) {

        validateUnit(targetUnit);

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------------- ADDITION (Implicit Target) ----------------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    // ---------------- ADDITION (Explicit Target) ----------------

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        validateUnit(targetUnit);

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(result), targetUnit);
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.6f, %s)", value, unit.getUnitName());
    }

    private void validateValue(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
    }

    private void validateUnit(U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}