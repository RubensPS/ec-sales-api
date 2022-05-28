package com.letscode.ecsalesapi.config;

import com.letscode.ecsalesapi.handler.SalesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SalesHandler salesHandler) {
        return RouterFunctions.route()
                .POST("/sales/add", accept(APPLICATION_JSON), salesHandler::addSale)
                .GET("/sales/user", accept(APPLICATION_JSON), salesHandler::getSalesByUser)
                .GET("/sales/{saleId}", salesHandler::getSaleById)
                .DELETE("/sales/remove/{saleId}", salesHandler::deleteSale)
                .build();
    }

}
