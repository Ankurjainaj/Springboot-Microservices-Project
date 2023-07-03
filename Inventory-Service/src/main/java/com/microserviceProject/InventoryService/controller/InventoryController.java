package com.microserviceProject.InventoryService.controller;

import com.microserviceProject.InventoryService.dto.InventoryResponse;
import com.microserviceProject.InventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory/iphone-13,iphone13-red

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping("/is_in_stock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam(name = "sku_code") List<String> skuCode) {
        log.info("Received inventory check request for sku_code: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
}