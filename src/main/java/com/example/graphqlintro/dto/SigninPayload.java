package com.example.graphqlintro.dto;

import com.example.graphqlintro.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninPayload {

    private  String token;
    private  User user;

    public SigninPayload(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
