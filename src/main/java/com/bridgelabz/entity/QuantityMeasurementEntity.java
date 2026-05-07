package com.bridgelabz.entity;

public class QuantityMeasurementEntity {

    private String operationType;
    private String measurementType;

    private double value1;
    private String unit1;

    private double value2;
    private String unit2;

    private String result;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(
            String operationType,
            String measurementType,
            double value1,
            String unit1,
            double value2,
            String unit2,
            String result
    ) {

        this.operationType = operationType;
        this.measurementType = measurementType;
        this.value1 = value1;
        this.unit1 = unit1;
        this.value2 = value2;
        this.unit2 = unit2;
        this.result = result;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}