package com.letscode.ecsalesapi.gateway;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersGateway {

    private final UsersReactiveFeignGateway usersReactiveFeignGateway;

    public Mono<String> getUser(String userId) {
        return usersReactiveFeignGateway.getUser(userId)
                .onErrorResume(FeignException.NotFound.class, error -> Mono.empty());
    }

}

