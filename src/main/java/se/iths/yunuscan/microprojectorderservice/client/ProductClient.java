package se.iths.yunuscan.microprojectorderservice.client;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import se.iths.yunuscan.microprojectorderservice.dto.CreateOrderItemRequestsDTO;
import se.iths.yunuscan.microprojectorderservice.dto.ProductStockResponseDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final RestClient restClient;
    public List<ProductStockResponseDTO>decreaseStock
            (List<CreateOrderItemRequestsDTO> item, String token) {
        return restClient.post()
                .uri("/products/stock/decrease")
                .header("Authorization", token)
                .body(item)
                .retrieve()
                .body(List.class);
    }
}
