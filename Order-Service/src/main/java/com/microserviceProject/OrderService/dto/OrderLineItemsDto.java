package com.microserviceProject.OrderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

    @JsonProperty("sku_code")
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
