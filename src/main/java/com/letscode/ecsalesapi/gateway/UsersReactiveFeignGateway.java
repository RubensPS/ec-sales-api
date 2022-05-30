package com.letscode.ecsalesapi.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "ec-users-service")
public interface UsersReactiveFeignGateway {

    @GetMapping("/users/user/{userId}")
    Mono<String> getUser(@PathVariable Integer userId);
}
