package com.microserviceProject.OrderService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @JsonProperty("order_line_items")
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
