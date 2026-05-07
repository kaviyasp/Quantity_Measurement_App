package com.bridgelabz.controller;

import com.bridgelabz.Quantity;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service
    ) {
        this.service = service;
    }

    public void performComparison(
            QuantityDTO dto1,
            QuantityDTO dto2
    ) {

        boolean result =
                service.compare(dto1, dto2);

        System.out.println(
                "Comparison Result : " + result
        );
    }

    public void performConversion(
            QuantityDTO dto,
            String targetUnit
    ) {

        Quantity<?> result =
                service.convert(dto, targetUnit);

        System.out.println(
                "Conversion Result : " + result
        );
    }

    public void performAddition(
            QuantityDTO dto1,
            QuantityDTO dto2,
            String targetUnit
    ) {

        Quantity<?> result =
                service.add(
                        dto1,
                        dto2,
                        targetUnit
                );

        System.out.println(
                "Addition Result : " + result
        );
    }

    public void performSubtraction(
            QuantityDTO dto1,
            QuantityDTO dto2,
            String targetUnit
    ) {

        Quantity<?> result =
                service.subtract(
                        dto1,
                        dto2,
                        targetUnit
                );

        System.out.println(
                "Subtraction Result : " + result
        );
    }

    public void performDivision(
            QuantityDTO dto1,
            QuantityDTO dto2
    ) {

        double result =
                service.divide(dto1, dto2);

        System.out.println(
                "Division Result : " + result
        );
    }
}