package com.datahack.product.productApi.infrastructure.rest;

import com.datahack.product.domain.model.ProductCommand;
import com.datahack.product.domain.model.ProductResponse;
import com.datahack.product.productApi.application.ProductOrderProcessor;
import com.datahack.product.productApi.infrastructure.rest.model.ProductApiRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api(value = "K8s Orders Product Command API")
@RequestMapping(path = "/product")
@Controller
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", maxAge = 3600)
public class ProductCommandApi {

    private final ProductOrderProcessor productOrderProcessor;
    private final RestMapper mapper;

    @Autowired
    public ProductCommandApi(ProductOrderProcessor productOrderProcessor, RestMapper mapper) {
        this.productOrderProcessor = productOrderProcessor;
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<ProductCommand> postProduct(
            @ApiParam("Request Body") @Valid @RequestBody ProductApiRequest request)  {

        ProductCommand domainObject = mapper.request2Domain(request);

        return new ResponseEntity<>(productOrderProcessor.processProductPost(domainObject), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}", method = GET)
    @ResponseBody
    ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) {
        return ;
    }

}
