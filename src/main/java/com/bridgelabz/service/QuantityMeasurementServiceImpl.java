package com.bridgelabz.service;

import com.bridgelabz.*;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.exception.QuantityMeasurementException;
import com.bridgelabz.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        try {

            Quantity quantity1 = createQuantity(q1);
            Quantity quantity2 = createQuantity(q2);

            boolean result = quantity1.equals(quantity2);

            repository.save(
                    new QuantityMeasurementEntity(
                            "COMPARE",
                            String.valueOf(result)
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());

        }
    }

    @Override
    public QuantityDTO convert(
            QuantityDTO quantity,
            String targetUnit
    ) {

        try {

            Quantity q = createQuantity(quantity);

            Quantity converted =
                    convertQuantity(q, targetUnit);

            repository.save(
                    new QuantityMeasurementEntity(
                            "CONVERT",
                            converted.toString()
                    )
            );

            return new QuantityDTO(
                    extractValue(converted),
                    targetUnit,
                    quantity.getMeasurementType()
            );

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());

        }
    }

    @Override
    public QuantityDTO add(
            QuantityDTO q1,
            QuantityDTO q2,
            String targetUnit
    ) {

        try {

            Quantity result =
                    createQuantity(q1)
                            .add(
                                    createQuantity(q2),
                                    getUnit(
                                            q1.getMeasurementType(),
                                            targetUnit
                                    )
                            );

            repository.save(
                    new QuantityMeasurementEntity(
                            "ADD",
                            result.toString()
                    )
            );

            return new QuantityDTO(
                    extractValue(result),
                    targetUnit,
                    q1.getMeasurementType()
            );

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());

        }
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO q1,
            QuantityDTO q2,
            String targetUnit
    ) {

        try {

            Quantity result =
                    createQuantity(q1)
                            .subtract(
                                    createQuantity(q2),
                                    getUnit(
                                            q1.getMeasurementType(),
                                            targetUnit
                                    )
                            );

            repository.save(
                    new QuantityMeasurementEntity(
                            "SUBTRACT",
                            result.toString()
                    )
            );

            return new QuantityDTO(
                    extractValue(result),
                    targetUnit,
                    q1.getMeasurementType()
            );

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());

        }
    }

    @Override
    public double divide(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        try {

            double result =
                    createQuantity(q1)
                            .divide(
                                    createQuantity(q2)
                            );

            repository.save(
                    new QuantityMeasurementEntity(
                            "DIVIDE",
                            String.valueOf(result)
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());

        }
    }

    private Quantity createQuantity(
            QuantityDTO dto
    ) {

        return new Quantity<>(
                dto.getValue(),
                getUnit(
                        dto.getMeasurementType(),
                        dto.getUnit()
                )
        );
    }

    private IMeasurable getUnit(
            String measurementType,
            String unit
    ) {

        return switch (measurementType.toUpperCase()) {

            case "LENGTH" ->
                    LengthUnit.valueOf(
                            unit.toUpperCase()
                    );

            case "WEIGHT" ->
                    WeightUnit.valueOf(
                            unit.toUpperCase()
                    );

            case "VOLUME" ->
                    VolumeUnit.valueOf(
                            unit.toUpperCase()
                    );

            case "TEMPERATURE" ->
                    TemperatureUnit.valueOf(
                            unit.toUpperCase()
                    );

            default ->
                    throw new IllegalArgumentException(
                            "Invalid Measurement Type"
                    );
        };
    }

    private Quantity convertQuantity(
            Quantity quantity,
            String targetUnit
    ) {

        IMeasurable target =
                getUnit(
                        detectType(quantity),
                        targetUnit
                );

        return quantity.convertTo(target);
    }

    private String detectType(
            Quantity quantity
    ) {

        String unitName = quantity.toString();

        if (unitName.contains("FEET")
                || unitName.contains("INCHES")) {

            return "LENGTH";
        }

        if (unitName.contains("KILOGRAM")
                || unitName.contains("GRAM")) {

            return "WEIGHT";
        }

        if (unitName.contains("LITRE")
                || unitName.contains("GALLON")) {

            return "VOLUME";
        }

        return "TEMPERATURE";
    }

    private double extractValue(
            Quantity quantity
    ) {

        String text = quantity.toString();

        String number =
                text.substring(
                        text.indexOf("(") + 1,
                        text.indexOf(",")
                );

        return Double.parseDouble(number);
    }
}