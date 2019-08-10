package com.example.graphqlintro.repository;

import com.example.graphqlintro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByEmail(String email);

    User findByToken(String token);


}
