package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuantityMeasurementApp {

    public static void main(String[] args) {
        SpringApplication.run(
                QuantityMeasurementApp.class,
                args
        );
    }
}