package com.letscode.ecsalesapi.repository;

import com.letscode.ecsalesapi.domain.SaleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SalesRepository extends ReactiveMongoRepository<SaleEntity, String> {
    Mono<List<SaleEntity>> findAllByUserId(String userId);
}
