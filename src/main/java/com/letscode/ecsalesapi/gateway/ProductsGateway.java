package com.letscode.ecsalesapi.gateway;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductsGateway {

    private final ProductsReactiveFeignGateway productsReactiveFeignGateway;

    public Mono<Boolean> checkProductSupply(String cartId) {
        return productsReactiveFeignGateway.checkProductSupply(cartId)
                .onErrorResume(FeignException.NotFound.class, error -> Mono.empty());
    }

    public Mono<String> subtractSaleFromSupply(Map<String, Long> products) {
        return productsReactiveFeignGateway.subtractSaleFromSupply(products)
                .onErrorResume(FeignException.NotFound.class, error -> Mono.empty());
    }

}
