package com.letscode.ecsalesapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;

@Document(collection = "sales")
@Getter
@Setter
@NoArgsConstructor
public class SaleEntity {
    @Id
    private String id;
    private String userId;
    private String cartId;
    private BigDecimal totalSalePrice;
    private Instant dateCreated;

    @Field
    private HashMap<String, Long> products;

    public SaleEntity(SaleCartRequest saleCartRequest) {
        this.userId = saleCartRequest.getUserId();
        this.cartId = saleCartRequest.getId();
        this.totalSalePrice = saleCartRequest.getTotalPrice();
        this.dateCreated = Instant.now();
        this.products = saleCartRequest.getProducts();
    }

}
