package com.letscode.ecsalesapi.gateway;

import com.letscode.ecsalesapi.domain.SaleCartRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartsGateway {

    private final CartsReactiveFeignGateway cartsReactiveFeignGateway;

    public Mono<SaleCartRequest> getActiveCarts(Integer userId) {
        return cartsReactiveFeignGateway.getActiveCart(userId)
                .onErrorResume(FeignException.NotFound.class, error -> Mono.empty());
    }

    public Mono<String> changeCartStatus(String cartId) {
        return cartsReactiveFeignGateway.changeCartStatusonSale(cartId)
                .onErrorResume(FeignException.NotFound.class, error -> Mono.empty());
    }

}

