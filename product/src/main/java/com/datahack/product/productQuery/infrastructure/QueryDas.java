package com.datahack.product.productQuery.infrastructure;

import com.datahack.product.domain.model.ProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class QueryDas {
    private final QueryRepository queryRepository;
    private final DocumentMapper mapper;

    @Autowired
    QueryDas(QueryRepository queryRepository, DocumentMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    public ProductQuery saveQueryDocument(ProductQuery productQuery){
        ProductDocument queryToDocument = mapper.doamin2Document(productQuery);

        Integer quantityInDB =
                queryRepository.findById(Long.parseLong(queryToDocument.getProductId()))
                        .map(p -> p.getQuantity()).orElse(0);

        queryToDocument.setQuantity(quantityInDB + queryToDocument.getQuantity());

        ProductDocument docSaved = queryRepository.save(queryToDocument);
        return mapper.document2Doamin(docSaved);
    }
}
