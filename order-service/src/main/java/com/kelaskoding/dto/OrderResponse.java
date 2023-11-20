package com.kelaskoding.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private String orderNumber;
    private Date orderDate;
    private Customer customer;
    private List<OrderLineResponse> orderLines;
}
