package com.example.graphqlintro.util;

import com.example.graphqlintro.common.ErrorConstants;
import com.example.graphqlintro.entity.ProductDetails;
import com.example.graphqlintro.entity.User;
import com.example.graphqlintro.exception.IMSException;
import com.example.graphqlintro.repository.UserRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class CommonUtil {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public boolean validateProductDetails(Long id) {
        boolean isExist = false;
        ProductDetails productDetails = em.find(ProductDetails.class, id);
        if (productDetails != null) {
            isExist = true;
        }
        return isExist;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[100];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public boolean isAuthenticated(DataFetchingEnvironment environment) {
        boolean isValidToken = false;
        GraphQLContext context = environment.getContext();
        HttpServletRequest request = context.getHttpServletRequest().get();
        if (request.getHeader("Authorization")==null) {
            throw new IMSException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_TOKEN);
        }

        String token = request.getHeader("Authorization").toString();
        String jwt = token.replace("Bearer", " ").trim();
        User user = userRepository.findByToken(jwt);

        if (user != null) {
            isValidToken = true;
        }
        return isValidToken;

    }
}
