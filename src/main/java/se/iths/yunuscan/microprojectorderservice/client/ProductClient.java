package se.iths.yunuscan.microprojectorderservice.client;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final RestClient restClient;
    public List decreaseStock(List request){
        return restClient.post()
                .uri("/products/stock/decrease")
                .body(request)
                .retrieve()
                .body(List.class);
    }
}
