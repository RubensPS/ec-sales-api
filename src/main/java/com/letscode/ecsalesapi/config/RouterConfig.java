package com.letscode.ecsalesapi.config;

import com.letscode.ecsalesapi.handler.SalesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SalesHandler salesHandler) {
        return RouterFunctions
                .route(POST("/sales/add").and(contentType(APPLICATION_JSON)), salesHandler::addSale)
                .andRoute(GET("/sales/user").and(contentType(APPLICATION_JSON)), salesHandler::getSalesByUser)
                .andRoute(GET("/sales/{saleId}").and(contentType(APPLICATION_JSON)), salesHandler::getSaleById);
    }

}
