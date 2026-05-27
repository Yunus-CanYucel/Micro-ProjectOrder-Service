package se.iths.yunuscan.microprojectorderservice.config;

import se.iths.yunuscan.microprojectorderservice.client.ProductClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestClient;

@Configuration
public class ProductClientConfig {

    @Bean
    public RestClient productRestClient(
            @Value("${product-app.url}") String baseUrl) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public ProductClient productClient(RestClient productRestClient) {
        return new ProductClient(productRestClient);
    }
}