package com.letscode.ecsalesapi.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "ec-products-service")
public interface ProductsReactiveFeignGateway {

    @GetMapping("/products/supply/{cartId}")
    Mono<Boolean> checkProductSupply(@PathVariable String cartId);
}
