package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.Quantity;

public interface IQuantityMeasurementService {

    boolean compare(
            QuantityDTO dto1,
            QuantityDTO dto2
    );

    Quantity<?> convert(
            QuantityDTO dto,
            String targetUnit
    );

    Quantity<?> add(
            QuantityDTO dto1,
            QuantityDTO dto2,
            String targetUnit
    );

    Quantity<?> subtract(
            QuantityDTO dto1,
            QuantityDTO dto2,
            String targetUnit
    );

    double divide(
            QuantityDTO dto1,
            QuantityDTO dto2
    );
}