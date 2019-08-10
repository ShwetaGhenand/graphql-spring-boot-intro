package com.example.graphqlintro.resolver;

import com.example.graphqlintro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.graphqlintro.entity.User;
import com.example.graphqlintro.dto.SigninPayload;
import com.coxautodev.graphql.tools.GraphQLResolver;

import java.util.Optional;

public class SigninResolver implements GraphQLResolver<SigninPayload> {

    @Autowired
    private UserRepository repo;

    public Optional<User> user(SigninPayload payload) {
        return repo.findById(payload.getUser().getId());
    }
}