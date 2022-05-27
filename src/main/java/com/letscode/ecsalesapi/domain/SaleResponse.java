package com.letscode.ecsalesapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@Setter
public class SaleResponse {
    private String id;
    private String userId;
    private String cartId;
    private BigDecimal totalSalePrice;
    private ZonedDateTime dateCreated;
    private Map<String, Long> products;

    public SaleResponse(SaleEntity saleEntity) {
        this.id = saleEntity.getId();
        this.userId = saleEntity.getUserId();
        this.cartId = saleEntity.getCartId();
        this.totalSalePrice = saleEntity.getTotalSalePrice();
        this.dateCreated = saleEntity.getDateCreated();
        this.products = saleEntity.getProducts();
    }
}
