package org.example.kafkaspringbootdemo.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class CreateProductDto {

    private final String title;
    private final BigDecimal price;
    private final Integer quantity;

    public CreateProductDto(String title, BigDecimal price, Integer quantity) {
        if (title == null || price == null || quantity == null) {
            throw new IllegalArgumentException("Constructor arguments cannot be null");
        }
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateProductDto that = (CreateProductDto) o;
        return Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(quantity);
        return result;
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
