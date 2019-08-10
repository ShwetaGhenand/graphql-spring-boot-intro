package com.example.graphqlintro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphqlintro.entity.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

	List<ProductDetails> findAll();


}