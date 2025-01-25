package org.example.kafkaspringbootdemo.dto;

import java.math.BigDecimal;

public record CreateProductDto(String title, BigDecimal price, Integer quantity) {

    public CreateProductDto {
        if (title == null || price == null || quantity == null) {
            throw new IllegalArgumentException("Constructor arguments cannot be null");
        }
    }

    @Override
    public String toString() {
        return "CreateProductDto{" +
               "title='" + title + '\'' +
               ", price=" + price +
               ", quantity=" + quantity +
               '}';
    }
}
