package com.bridgelabz.dto;

public class OperationRequestDTO {

    private QuantityDTO dto1;
    private QuantityDTO dto2;

    public OperationRequestDTO() {
    }

    public QuantityDTO getDto1() {
        return dto1;
    }

    public void setDto1(QuantityDTO dto1) {
        this.dto1 = dto1;
    }

    public QuantityDTO getDto2() {
        return dto2;
    }

    public void setDto2(QuantityDTO dto2) {
        this.dto2 = dto2;
    }
}