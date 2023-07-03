package com.microserviceProject.notificationservice;

import com.microserviceProject.notificationservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
@RequiredArgsConstructor
public class NotificationServiceApplication {

//	private final ObservationRegistry observationRegistry;
//	private final Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
//		Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
//			log.info("Got message <{}>", orderPlacedEvent);
//			log.info("TraceId- {}, Received Notification for Order - {}", this.tracer.currentSpan().context().traceId(),
//					orderPlacedEvent.getOrderNumber());
//		});
        // send out an email notification
        log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
    }

}
