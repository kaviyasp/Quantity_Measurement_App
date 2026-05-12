package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> measurements =
            new ArrayList<>();

    private QuantityMeasurementCacheRepository() {
    }

    public static QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {

            instance =
                    new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity
    ) {

        measurements.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity>
    getAllMeasurements() {

        return measurements;
    }

    @Override
    public void deleteAllMeasurements() {

        measurements.clear();
    }

    @Override
    public long getTotalCount() {

        return measurements.size();
    }
}