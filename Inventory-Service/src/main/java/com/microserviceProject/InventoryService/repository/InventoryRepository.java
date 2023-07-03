package com.microserviceProject.InventoryService.repository;

import com.microserviceProject.InventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//    boolean existsBySkuCode(List<String> skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}