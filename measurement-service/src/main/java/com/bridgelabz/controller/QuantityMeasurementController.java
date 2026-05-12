package com.bridgelabz.controller;

import com.bridgelabz.dto.OperationRequestDTO;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public boolean compare(@RequestBody OperationRequestDTO request) {

        return service.compare(
                request.getDto1(),
                request.getDto2()
        );
    }

    @PostMapping("/convert/{targetUnit}")
    public String convert(
            @RequestBody QuantityDTO dto,
            @PathVariable String targetUnit
    ) {

        return service.convert(dto, targetUnit).toString();
    }

    @PostMapping("/add/{targetUnit}")
    public String add(
            @RequestBody OperationRequestDTO request,
            @PathVariable String targetUnit
    ) {

        return service.add(
                request.getDto1(),
                request.getDto2(),
                targetUnit
        ).toString();
    }

    @PostMapping("/subtract/{targetUnit}")
    public String subtract(
            @RequestBody OperationRequestDTO request,
            @PathVariable String targetUnit
    ) {

        return service.subtract(
                request.getDto1(),
                request.getDto2(),
                targetUnit
        ).toString();
    }

    @PostMapping("/divide")
    public double divide(@RequestBody OperationRequestDTO request) {

        return service.divide(
                request.getDto1(),
                request.getDto2()
        );
    }
}