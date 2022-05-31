package com.letscode.ecsalesapi.service;

import com.letscode.ecsalesapi.domain.SaleEntity;
import com.letscode.ecsalesapi.domain.SaleRequest;
import com.letscode.ecsalesapi.gateway.CartsGateway;
import com.letscode.ecsalesapi.gateway.ProductsGateway;
import com.letscode.ecsalesapi.gateway.UsersGateway;
import com.letscode.ecsalesapi.repository.SalesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class SalesService {

    private final UsersGateway usersGateway;
    private final CartsGateway cartsGateway;
    private final ProductsGateway productsGateway;
    private final SalesRepository salesRepository;

    public SalesService(UsersGateway userGateway, CartsGateway cartsGateway, ProductsGateway productsGateway, SalesRepository salesRepository) {
        this.usersGateway = userGateway;
        this.cartsGateway = cartsGateway;
        this.productsGateway = productsGateway;
        this.salesRepository = salesRepository;
    }

    public Mono<SaleEntity> addSale(SaleRequest saleRequest) {
        return Mono.zip(
                Mono.just(saleRequest).flatMap(sale -> usersGateway.getUser(sale.getUserId())),
                Mono.just(saleRequest).flatMap(sale -> cartsGateway.getActiveCarts(sale.getUserId())),
                Mono.just(saleRequest).flatMap(sale -> productsGateway.checkProductSupply(sale.getCartId()))
        ).map(tupla -> new SaleEntity(tupla.getT2()));
    }

    public Flux<SaleEntity> getSalesByUser(SaleRequest saleRequest) {
        return Flux.just(saleRequest.getUserId())
                .flatMap(id -> salesRepository.findAllByUserId(id));
    }

    public Mono<SaleEntity> getSaleById(String id) {
        return Mono.just(id)
                .flatMap(saleId -> salesRepository.findById(saleId));
    }

    public Mono<Void> deleteSaleById(String saleId) {
        return Mono.just(saleId)
                .flatMap(id -> salesRepository.findById(id))
                .flatMap(entity -> Objects.isNull(entity) ? Mono.empty() : salesRepository.deleteById(entity.getId()));
    }


}
