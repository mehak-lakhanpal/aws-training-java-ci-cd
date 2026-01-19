package com.example.order.respository;

import com.example.order.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.time.Instant;
import java.util.List;

@Repository
public class ProductRepository {

    private final DynamoDbTable<Product> table;
    private final DynamoDbIndex<Product> categoryIndex;

    public ProductRepository(
            DynamoDbEnhancedClient enhancedClient,
            @Value("${aws.dynamodb.table-name}") String tableName) {

        this.table = enhancedClient.table(
                tableName,
                TableSchema.fromBean(Product.class)
        );
        this.categoryIndex = table.index("category-price-index");
    }

    public void save(Product product) {
        product.setCreatedAt(Instant.now());
        table.putItem(product);
    }

    public Product findById(String id) {
        return table.getItem(Key.builder()
                .partitionValue(id)
                .build());
    }

    public List<Product> findByCategory(String category) {
        QueryConditional condition = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(category).build());

        return categoryIndex.query(condition).stream().flatMap(p->p.items().stream())
                .toList();
    }
}