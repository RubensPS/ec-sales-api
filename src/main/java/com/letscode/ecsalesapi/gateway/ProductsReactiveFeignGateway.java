package com.letscode.ecsalesapi.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@ReactiveFeignClient(name = "ec-products-service")
public interface ProductsReactiveFeignGateway {

    @GetMapping("/products/supply/{cartId}")
    Mono<Boolean> checkProductSupply(@PathVariable String cartId);

    @PatchMapping("/products/supply/products")
    Mono<String> subtractSaleFromSupply(@RequestBody HashMap<String, Long> products);

}
