package org.example.kafkaspringbootdemo.service.impl;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.kafkaspringbootdemo.dto.CreateProductDto;
import org.example.kafkaspringbootdemo.event.ProductCreatedEvent;
import org.example.kafkaspringbootdemo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //TODO: save to database
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId,
                createProductDto.getTitle(),
                createProductDto.getPrice(),
                createProductDto.getQuantity()
        );

        SendResult<String, ProductCreatedEvent> sendResult = kafkaTemplate
                .send("product-created-events-topic", productId, productCreatedEvent)
                .get();
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();

        logger.info(
                """
                Topic: {}
                Partition: {}
                Offset: {}
                """, recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        logger.info("Return: {}", productId);

        return productId;
    }
}
