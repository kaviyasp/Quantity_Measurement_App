package com.bridgelabz.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operation;
    private String result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation, String result) {
        this.operation = operation;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operation, String errorMessage, boolean error) {
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.error = error;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {

        if (error) {
            return "ERROR : " + errorMessage;
        }

        return operation + " => " + result;
    }
}