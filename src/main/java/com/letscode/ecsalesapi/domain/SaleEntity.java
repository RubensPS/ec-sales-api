package com.letscode.ecsalesapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

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
    private ZonedDateTime dateCreated;

    @Field
    private Map<String, Long> products;

    public SaleEntity(SaleCartRequest saleCartRequest) {
        this.userId = saleCartRequest.getUserId();
        this.cartId = saleCartRequest.getCartId();
        this.totalSalePrice = saleCartRequest.getTotalPrice();
        this.dateCreated = ZonedDateTime.now();
        this.products = saleCartRequest.getProducts();
    }

}
