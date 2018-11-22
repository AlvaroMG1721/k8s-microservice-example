package com.datahack.product.productApi.infrastructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductApiRequest {
    private String productName;
    private String vendorName;
    private String productId;
    private Integer vendId;
    private Integer quantity;
    private Date expirationDate;
}
