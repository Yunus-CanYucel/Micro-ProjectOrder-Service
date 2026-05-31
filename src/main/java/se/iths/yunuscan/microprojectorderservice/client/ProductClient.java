package se.iths.yunuscan.microprojectorderservice.client;


import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import se.iths.yunuscan.microprojectorderservice.dto.ProductStockResponseDTO;


import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient restClient;

    public List<ProductStockResponseDTO> decreaseStock(
            List<ProductStockResponseDTO> request,
            String token
    ) {
        return restClient.post()
                .uri("/products/stock/decrease")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductStockResponseDTO>>() {});
    }
}