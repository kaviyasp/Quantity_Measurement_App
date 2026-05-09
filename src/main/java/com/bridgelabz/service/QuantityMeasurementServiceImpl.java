package com.bridgelabz.service;

import com.bridgelabz.IMeasurable;
import com.bridgelabz.LengthUnit;
import com.bridgelabz.Quantity;
import com.bridgelabz.TemperatureUnit;
import com.bridgelabz.VolumeUnit;
import com.bridgelabz.WeightUnit;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.exception.QuantityMeasurementException;
import com.bridgelabz.repository.QuantityMeasurementRepository;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            QuantityMeasurementRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public boolean compare(
            QuantityDTO dto1,
            QuantityDTO dto2
    ) {

        try {

            IMeasurable unit1 =
                    getUnit(
                            dto1.getMeasurementType(),
                            dto1.getUnit()
                    );

            IMeasurable unit2 =
                    getUnit(
                            dto2.getMeasurementType(),
                            dto2.getUnit()
                    );

            Quantity<IMeasurable> q1 =
                    new Quantity<>(
                            dto1.getValue(),
                            unit1
                    );

            Quantity<IMeasurable> q2 =
                    new Quantity<>(
                            dto2.getValue(),
                            unit2
                    );

            boolean result = q1.equals(q2);

            repository.save(
                    new QuantityMeasurementEntity(
                            "COMPARE",
                            dto1.getMeasurementType(),
                            dto1.getValue(),
                            dto1.getUnit(),
                            dto2.getValue(),
                            dto2.getUnit(),
                            String.valueOf(result)
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    e.getMessage()
            );
        }
    }

    @Override
    public Quantity<?> convert(
            QuantityDTO dto,
            String targetUnit
    ) {

        try {

            IMeasurable sourceUnit =
                    getUnit(
                            dto.getMeasurementType(),
                            dto.getUnit()
                    );

            IMeasurable target =
                    getUnit(
                            dto.getMeasurementType(),
                            targetUnit
                    );

            Quantity<IMeasurable> quantity =
                    new Quantity<>(
                            dto.getValue(),
                            sourceUnit
                    );

            Quantity<?> result =
                    quantity.convertTo(target);

            repository.save(
                    new QuantityMeasurementEntity(
                            "CONVERT",
                            dto.getMeasurementType(),
                            dto.getValue(),
                            dto.getUnit(),
                            0.0,
                            targetUnit,
                            result.toString()
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    e.getMessage()
            );
        }
    }

    @Override
    public Quantity<?> add(
            QuantityDTO dto1,
            QuantityDTO dto2,
            String targetUnit
    ) {

        try {

            IMeasurable unit1 =
                    getUnit(
                            dto1.getMeasurementType(),
                            dto1.getUnit()
                    );

            IMeasurable unit2 =
                    getUnit(
                            dto2.getMeasurementType(),
                            dto2.getUnit()
                    );

            IMeasurable target =
                    getUnit(
                            dto1.getMeasurementType(),
                            targetUnit
                    );

            Quantity<IMeasurable> q1 =
                    new Quantity<>(
                            dto1.getValue(),
                            unit1
                    );

            Quantity<IMeasurable> q2 =
                    new Quantity<>(
                            dto2.getValue(),
                            unit2
                    );

            Quantity<?> result =
                    q1.add(q2, target);

            repository.save(
                    new QuantityMeasurementEntity(
                            "ADD",
                            dto1.getMeasurementType(),
                            dto1.getValue(),
                            dto1.getUnit(),
                            dto2.getValue(),
                            dto2.getUnit(),
                            result.toString()
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    e.getMessage()
            );
        }
    }

    @Override
    public Quantity<?> subtract(
            QuantityDTO dto1,
            QuantityDTO dto2,
            String targetUnit
    ) {

        try {

            IMeasurable unit1 =
                    getUnit(
                            dto1.getMeasurementType(),
                            dto1.getUnit()
                    );

            IMeasurable unit2 =
                    getUnit(
                            dto2.getMeasurementType(),
                            dto2.getUnit()
                    );

            IMeasurable target =
                    getUnit(
                            dto1.getMeasurementType(),
                            targetUnit
                    );

            Quantity<IMeasurable> q1 =
                    new Quantity<>(
                            dto1.getValue(),
                            unit1
                    );

            Quantity<IMeasurable> q2 =
                    new Quantity<>(
                            dto2.getValue(),
                            unit2
                    );

            Quantity<?> result =
                    q1.subtract(q2, target);

            repository.save(
                    new QuantityMeasurementEntity(
                            "SUBTRACT",
                            dto1.getMeasurementType(),
                            dto1.getValue(),
                            dto1.getUnit(),
                            dto2.getValue(),
                            dto2.getUnit(),
                            result.toString()
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    e.getMessage()
            );
        }
    }

    @Override
    public double divide(
            QuantityDTO dto1,
            QuantityDTO dto2
    ) {

        try {

            IMeasurable unit1 =
                    getUnit(
                            dto1.getMeasurementType(),
                            dto1.getUnit()
                    );

            IMeasurable unit2 =
                    getUnit(
                            dto2.getMeasurementType(),
                            dto2.getUnit()
                    );

            Quantity<IMeasurable> q1 =
                    new Quantity<>(
                            dto1.getValue(),
                            unit1
                    );

            Quantity<IMeasurable> q2 =
                    new Quantity<>(
                            dto2.getValue(),
                            unit2
                    );

            double result =
                    q1.divide(q2);

            repository.save(
                    new QuantityMeasurementEntity(
                            "DIVIDE",
                            dto1.getMeasurementType(),
                            dto1.getValue(),
                            dto1.getUnit(),
                            dto2.getValue(),
                            dto2.getUnit(),
                            String.valueOf(result)
                    )
            );

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(
                    e.getMessage()
            );
        }
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
}