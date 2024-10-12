package com.ijse.posproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockReqDto {

    private Long itemCode;
    private Long quantityOnHand;
    private String location;

}
