package com.letscode.ecsalesapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class SaleCartRequest {
    private String id;
    private Integer userId;
    private Boolean isActiveStatus;
    private BigDecimal totalPrice;
    private Map<String, Long> products;

}
