package com.letscode.ecsalesapi.gateway;

import com.letscode.ecsalesapi.domain.SaleCartRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "ec-carts-service")
public interface CartsReactiveFeignGateway {

    @GetMapping("/carts/cart/user/{userId}")
    Mono<SaleCartRequest>getActiveCart(@PathVariable Integer userId);

}
