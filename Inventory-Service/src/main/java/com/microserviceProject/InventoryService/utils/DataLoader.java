//package com.microserviceProject.InventoryService.utils;
//
//import com.microserviceProject.InventoryService.model.Inventory;
//import com.microserviceProject.InventoryService.repository.InventoryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//    private final InventoryRepository inventoryRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Inventory inventory = new Inventory();
//        inventory.setSkuCode("iphone_14");
//        inventory.setQuantity(100);
//
//        Inventory inventory1 = new Inventory();
//        inventory1.setSkuCode("iphone_14_blue");
//        inventory1.setQuantity(0);
//
//        inventoryRepository.save(inventory);
//        inventoryRepository.save(inventory1);
//    }
//}
