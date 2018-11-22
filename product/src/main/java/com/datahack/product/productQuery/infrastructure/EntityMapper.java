package com.datahack.product.productQuery.infrastructure;

import com.datahack.product.domain.model.ProductCommand;
import com.datahack.product.domain.model.ProductQuery;
import com.datahack.product.domain.model.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface EntityMapper {
    ProductQuery domain2Query(ProductCommand productCommand);
    ProductResponse document2Response(ProductDocument productDocument);
}
