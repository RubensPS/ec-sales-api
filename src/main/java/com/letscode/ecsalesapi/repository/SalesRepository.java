package com.letscode.ecsalesapi.repository;

import com.letscode.ecsalesapi.domain.SaleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SalesRepository extends ReactiveMongoRepository<SaleEntity, String> {
    Flux<SaleEntity> findAllByUserId(Integer userId);
}
