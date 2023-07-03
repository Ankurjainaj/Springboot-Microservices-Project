package com.microserviceProject.OrderService.service;

import com.microserviceProject.OrderService.dto.OrderLineItemsDto;
import com.microserviceProject.OrderService.dto.OrderRequest;
import com.microserviceProject.OrderService.event.OrderPlacedEvent;
import com.microserviceProject.OrderService.model.InventoryResponse;
import com.microserviceProject.OrderService.model.Order;
import com.microserviceProject.OrderService.model.OrderLineItems;
import com.microserviceProject.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    //    private final ObservationRegistry observationRegistry;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        // Call Inventory Service, and place order if product is in stock

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        Span inventoryServiceLookup = tracer.nextSpan().name("Inventory Service Lookup");

        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup)) {
            InventoryResponse[] inventoryResponseArray =
                    webClientBuilder.build().get()
                            .uri("http://inventory-service/api/inventory/is_in_stock",
                                    uriBuilder -> uriBuilder.queryParam("sku_code", skuCodes).build())
                            .retrieve()
                            .bodyToMono(InventoryResponse[].class)
                            .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);
            if (allProductsInStock) {
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "Order Placed Successfully";
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        } finally {
            inventoryServiceLookup.end();
        }


//        Observation inventoryServiceObservation = Observation.createNotStarted("inventory-service-lookup",
//                this.observationRegistry);
//        inventoryServiceObservation.lowCardinalityKeyValue("call", "inventory-service");
//        return inventoryServiceObservation.observe(() -> {
//            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
//                    .uri("http://inventory-service/api/inventory",
//                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                    .retrieve()
//                    .bodyToMono(InventoryResponse[].class)
//                    .block();
//
//            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
//                    .allMatch(InventoryResponse::isInStock);
//
//            if (allProductsInStock) {
//                orderRepository.save(order);
//                // publish Order Placed Event
//                applicationEventPublisher.publishEvent(new OrderPlacedEvent(this, order.getOrderNumber()));
//                return "Order Placed";
//            } else {
//                throw new IllegalArgumentException("Product is not in stock, please try again later");
//            }
//        });

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}