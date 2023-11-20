package com.kelaskoding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse {
    private Long id;
    private Product product;
    private int quantity;
    private double price;
}
