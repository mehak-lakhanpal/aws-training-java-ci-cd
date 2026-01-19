package com.example.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsProperties {

    private String accessKey;
    private String secretKey;
    private String region;

    private DynamoDb dynamodb = new DynamoDb();

    @Data
    public static class DynamoDb {
        private String endpoint;
    }
}