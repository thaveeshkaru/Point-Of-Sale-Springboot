package com.ijse.posproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ItemReqDto {
    private String itemName;
    private Double price;
    private String description;
    private Long categoryId;
}
