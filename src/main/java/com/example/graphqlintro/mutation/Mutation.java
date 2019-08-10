package com.example.graphqlintro.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphqlintro.common.ErrorConstants;
import com.example.graphqlintro.dto.*;
import com.example.graphqlintro.entity.Item;
import com.example.graphqlintro.entity.ProductDetails;
import com.example.graphqlintro.entity.User;
import com.example.graphqlintro.exception.IMSException;
import com.example.graphqlintro.repository.ItemRepository;
import com.example.graphqlintro.repository.ProductDetailsRepository;
import com.example.graphqlintro.repository.UserRepository;
import com.example.graphqlintro.util.CommonUtil;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class Mutation implements GraphQLMutationResolver {

    private static final Logger logger = LoggerFactory.getLogger(Mutation.class);

    @Autowired
    private ItemRepository repo;

    @Autowired
    private CommonUtil util;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductDetailsRepository productDetailsRepo;

    @Autowired
    private UserRepository userRepo;

    public Mutation(ItemRepository repo) {
        this.repo = repo;
    }

    public Item createItem(CreateItemDto createItemDto, DataFetchingEnvironment environment) {

        logger.debug("create item mutation is started");

        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        Item itemObj = new Item();

        if (createItemDto.getProductDetailsId() == null) {
            throw new IMSException(HttpStatus.BAD_REQUEST, ErrorConstants.NOT_EMPTY);
        }

        if (!util.validateProductDetails(createItemDto.getProductDetailsId())) {
            throw new IMSException(HttpStatus.NOT_FOUND, ErrorConstants.PRODUCT_NOT_FOUND);
        }

        if (StringUtils.isNotEmpty(createItemDto.getItemName())) {
            itemObj.setItemName(createItemDto.getItemName());
        }

        if (StringUtils.isNotEmpty(createItemDto.getItemBrand())) {
            itemObj.setItemBrand(createItemDto.getItemBrand());
        }

        if (StringUtils.isNotEmpty(createItemDto.getItemCategory())) {
            itemObj.setItemCategory(createItemDto.getItemCategory());
        }
        if (createItemDto.getProductDetailsId() != null) {
            itemObj.setProductDetailsId(createItemDto.getProductDetailsId());
        }

        logger.debug("create item mutation is finished");
        return repo.save(itemObj);
    }

    public Item updateItem(UpdateItemDto updateItemDto, DataFetchingEnvironment environment) {

        logger.debug("update item mutation is started");

        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        Item item = em.find(Item.class, updateItemDto.getId());
        if (item == null) {
            throw new IMSException(HttpStatus.NOT_FOUND, ErrorConstants.ITEM_NOT_FOUND);
        }
        if (StringUtils.isNotEmpty(updateItemDto.getItemName())) {
            item.setItemName(updateItemDto.getItemName());
        }
        if (StringUtils.isNotEmpty(updateItemDto.getItemBrand())) {
            item.setItemBrand(updateItemDto.getItemBrand());
        }
        if (StringUtils.isNotEmpty(updateItemDto.getItemCategory())) {
            item.setItemCategory(updateItemDto.getItemCategory());
        }

        logger.debug("create item mutation is finished");
        return repo.save(item);
    }

    public Long deleteItem(Long id, DataFetchingEnvironment environment) {

        logger.debug("delete item mutation is started");

        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        Item item = em.find(Item.class, id);
        if (item == null) {
            throw new IMSException(HttpStatus.NOT_FOUND, ErrorConstants.ITEM_NOT_FOUND);
        }
        repo.deleteById(id);

        logger.debug("update item mutation is finished");
        return id;
    }

    public ProductDetails createProductDetails(CreateProductDetailsDto createPdDto, DataFetchingEnvironment environment) {

        logger.debug("create product details mutation is finished");

        if (!util.isAuthenticated(environment)) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }
        ProductDetails pdObj = new ProductDetails();
        if (StringUtils.isNotEmpty(createPdDto.getProductDescription())) {
            pdObj.setProductDescription(createPdDto.getProductDescription());
        }
        if (StringUtils.isNotEmpty(createPdDto.getProductQuantity())) {
            pdObj.setProductQuantity(createPdDto.getProductQuantity());
        }

        logger.debug("create product detail mutation is finished");
        return productDetailsRepo.save(pdObj);

    }

    public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {

        logger.debug("signin user mutation is started");

        User user = userRepo.findByEmail(auth.getEmail());

        if (user == null) {
            throw new IMSException(HttpStatus.NOT_FOUND, ErrorConstants.USER_NOT_FOUND);
        }

        if (!user.getPassword().equals(auth.getPassword())) {
            throw new IMSException(HttpStatus.NOT_FOUND, ErrorConstants.INVALID_CREDENTIALS);
        }

        String token = util.generateNewToken();
        user.setToken(token);
        User userResult = userRepo.save(user);

        logger.debug("signin user mutation is finished");
        return new SigninPayload(userResult.getToken(), userResult);


    }
}
