package com.letscode.ecsalesapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

@Getter
@Setter
public class SaleCartRequest {
    private String id;
    private String userId;
    private Boolean isActiveStatus;
    private BigDecimal totalPrice;
    private HashMap<String, Long> products;

}
