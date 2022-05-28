package com.letscode.ecsalesapi.gateway;

import com.letscode.ecsalesapi.domain.SaleCartRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class CartsGateway {

    public Mono<SaleCartRequest> getActiveCarts(Integer userId) {
        return WebClient
                .builder()
                .baseUrl(String.format("http://cartsAPI:8081/carts/cart/user/%s", userId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(SaleCartRequest.class)
                .log()
                .onErrorResume(WebClientResponseException.class, error -> error.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(error));
    }

}
