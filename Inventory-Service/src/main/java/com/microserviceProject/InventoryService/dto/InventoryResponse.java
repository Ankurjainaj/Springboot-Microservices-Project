package com.microserviceProject.InventoryService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    @JsonProperty("sku_code")
    private String skuCode;
    @JsonProperty("is_in_stock")
    private boolean isInStock;
}