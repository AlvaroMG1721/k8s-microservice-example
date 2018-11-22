package com.datahack.product.productQuery.infrastructure;

import com.datahack.product.domain.model.CommandEvent;
import com.datahack.product.domain.model.ProductQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class QueryListener implements ApplicationListener<CommandEvent> {

    private final QueryDas queryDas;
    private final EntityMapper mapper;

    QueryListener(QueryDas queryDas, EntityMapper mapper) {
        this.queryDas = queryDas;
        this.mapper = mapper;
    }

    @Override
    public void onApplicationEvent(CommandEvent commandEvent) {
        log.info("Query model receive command event: {}", commandEvent.getEventBody());
        ProductQuery productQuery = mapper.domain2Query(commandEvent.getEventBody());
        queryDas.saveQueryDocument(productQuery);
        log.info("Document Saved: {}", productQuery);
    }

}
