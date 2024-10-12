package com.ijse.posproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderReqDto {

    private List<Long> itemCodes;
    private String paymentMethod;
    private Double discount;
}
