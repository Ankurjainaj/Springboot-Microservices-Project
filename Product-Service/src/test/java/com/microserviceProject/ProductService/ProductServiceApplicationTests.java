package com.microserviceProject.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microserviceProject.ProductService.dto.ProductRequest;
import com.microserviceProject.ProductService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    //Writing Integration Tests

//    @Container
//    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.19");
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
//        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    @Test
//    void shouldCreateProduct() throws Exception {
//        ProductRequest productRequest = getProductRequest();
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/createProduct")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(productRequest)))
//                .andExpect(status().isCreated());
//        Assertions.assertEquals(productRepository.findAll().size(), 1);
//    }
//
//    private ProductRequest getProductRequest() {
//        return ProductRequest.builder().name("iPhone 14").description("Latest iPhone on the market").price(BigDecimal.valueOf(1200)).build();
//    }

}
