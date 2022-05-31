package com.letscode.ecsalesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
public class EcSalesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcSalesApiApplication.class, args);
    }

}
