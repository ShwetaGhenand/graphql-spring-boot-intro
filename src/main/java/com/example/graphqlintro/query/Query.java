package com.example.graphqlintro.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphqlintro.common.ErrorConstants;
import com.example.graphqlintro.entity.Item;
import com.example.graphqlintro.entity.ProductDetails;
import com.example.graphqlintro.exception.IMSException;
import com.example.graphqlintro.repository.ItemRepository;
import com.example.graphqlintro.repository.ProductDetailsRepository;
import com.example.graphqlintro.util.CommonUtil;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private static final Logger logger = LoggerFactory.getLogger(Query.class);

    @Autowired
    ItemRepository repo;

    @Autowired
    CommonUtil util;

    @Autowired
    ProductDetailsRepository productRepo;

    public List<Item> getItems(DataFetchingEnvironment environment) {
        logger.debug("get all item query is started");

        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        logger.debug("get all item query is finished");

        return repo.findAll();
    }

    public Optional<Item> getItem(Long id, DataFetchingEnvironment environment) {

        logger.debug("get item by id query is started");


        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        logger.debug("get item by id is finished");

        return repo.findById(id);
    }

    public List<ProductDetails> getProductDetails(DataFetchingEnvironment environment) {

        logger.debug("get all product details query is started");


        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        logger.debug("get all product details query is finished");

        return productRepo.findAll();
    }

    public Optional<ProductDetails> getProductDetail(Long id, DataFetchingEnvironment environment) {

        logger.debug("get product details query is started");

        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        logger.debug("get product detail query is finished");

        return productRepo.findById(id);
    }

}
