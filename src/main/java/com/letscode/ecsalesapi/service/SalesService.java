package com.letscode.ecsalesapi.service;

import com.letscode.ecsalesapi.domain.SaleEntity;
import com.letscode.ecsalesapi.domain.SaleRequest;
import com.letscode.ecsalesapi.gateway.CartsGateway;
import com.letscode.ecsalesapi.gateway.UsersGateway;
import com.letscode.ecsalesapi.repository.SalesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SalesService {

    private final UsersGateway usersGateway;
    private final CartsGateway cartsGateway;
    private final SalesRepository salesRepository;

    public SalesService(UsersGateway userGateway, CartsGateway cartsGateway, SalesRepository salesRepository) {
        this.usersGateway = userGateway;
        this.cartsGateway = cartsGateway;
        this.salesRepository = salesRepository;
    }

    public Mono<SaleEntity> addSale(SaleRequest saleRequest) {
        return Mono.zip(
                Mono.just(saleRequest).flatMap(sale -> usersGateway.getUser(sale.getUserId())),
                Mono.just(saleRequest).flatMap(sale -> cartsGateway.getActiveCarts(sale.getUserId()))
        ).map(tupla ->  new SaleEntity(tupla.getT2()));
    }

    public Mono<List<SaleEntity>> getSalesByUser(SaleRequest saleRequest) {
        return Mono.just(saleRequest.getUserId())
                .flatMap(id -> salesRepository.findAllByUserId(id));
    }

    public Mono<SaleEntity> getSaleById(String id) {
        return Mono.just(id)
                .flatMap(saleId -> salesRepository.findById(saleId));
    }
}
