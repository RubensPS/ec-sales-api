package com.letscode.ecsalesapi.handler;

import com.letscode.ecsalesapi.domain.SaleRequest;
import com.letscode.ecsalesapi.domain.SaleResponse;
import com.letscode.ecsalesapi.repository.SalesRepository;
import com.letscode.ecsalesapi.service.SalesService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class SalesHandler {

    private final SalesService salesService;
    private final SalesRepository salesRepository;

    public SalesHandler(SalesService salesService, SalesRepository salesRepository) {
        this.salesService = salesService;
        this.salesRepository = salesRepository;
    }

    public Mono<ServerResponse> addSale(ServerRequest request) {
        return request.bodyToMono(SaleRequest.class)
                .flatMap(salesService::addSale)
                .flatMap(salesRepository::save)
                .flatMap(salesService::closeCartAndProducts)
                .flatMap(saleEntity -> ServerResponse
                        .created(URI.create(String.format("/sales/%s", saleEntity.getId())))
                        .bodyValue(new SaleResponse(saleEntity)))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("AddSale returned empty. Check if the sale was finished properly."));
    }

    public Mono<ServerResponse> getSalesByUser(ServerRequest request) {
        return request.bodyToFlux(SaleRequest.class)
                .flatMap(salesService::getSalesByUser)
                .collectList()
                .flatMap(saleEntities -> ServerResponse
                        .ok().bodyValue(saleEntities))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Couldn´t return sale with the user imput."));

    }

    public Mono<ServerResponse> getSaleById(ServerRequest request) {
        return Mono.just(request.pathVariable("saleId"))
                .flatMap(salesService::getSaleById)
                .flatMap(saleEntity -> ServerResponse
                        .ok().bodyValue(saleEntity))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Invalid sale. Check data imput."));
    }

    public Mono<ServerResponse> deleteSale(ServerRequest request) {
        return Mono.just(request.pathVariable("saleId"))
                .flatMap(salesService::deleteSaleById)
                .flatMap(r -> ServerResponse.noContent().build());
    }

}
