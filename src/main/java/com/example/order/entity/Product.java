package com.example.order.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey;

import java.math.BigDecimal;
import java.time.Instant;

@DynamoDbBean
@Data
public class Product {

    private String productId;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock;
    private Instant createdAt;

    @DynamoDbPartitionKey
    public String getProductId() {
        return productId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "category-price-index")
    public String getCategory() {
        return category;
    }

    @DynamoDbSecondarySortKey(indexNames = "category-price-index")
    public BigDecimal getPrice() {
        return price;
    }

    public void setProductId(String id){
        this.productId=id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

// getters and setters
}