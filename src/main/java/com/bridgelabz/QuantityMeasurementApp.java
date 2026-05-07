package com.bridgelabz;

import com.bridgelabz.controller.QuantityMeasurementController;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.repository.QuantityMeasurementDatabaseRepository;
import com.bridgelabz.service.IQuantityMeasurementService;
import com.bridgelabz.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO length1 =
                new QuantityDTO(
                        1.0,
                        "FEET",
                        "LENGTH"
                );

        QuantityDTO length2 =
                new QuantityDTO(
                        12.0,
                        "INCH",
                        "LENGTH"
                );

        controller.performComparison(
                length1,
                length2
        );

        controller.performConversion(
                length1,
                "INCH"
        );

        controller.performAddition(
                length1,
                length2,
                "FEET"
        );

        System.out.println(
                "Total Measurements Stored : "
                        + repository.getTotalCount()
        );
    }
}