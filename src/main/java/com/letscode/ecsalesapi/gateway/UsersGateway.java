package com.letscode.ecsalesapi.gateway;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class UsersGateway {

    public Mono<String> getUser(Integer userId) {
        return WebClient
                .builder()
                .baseUrl(String.format("http://usersAPI:8080/users/user/%s", userId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, error -> error.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(error));
    }

}
