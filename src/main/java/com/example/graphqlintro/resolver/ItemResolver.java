package com.example.graphqlintro.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphqlintro.entity.Item;
import com.example.graphqlintro.entity.ProductDetails;
import com.example.graphqlintro.repository.ProductDetailsRepository;

@Component
public class ItemResolver implements GraphQLResolver<Item> {

	@Autowired
	ProductDetailsRepository repo;

	public Optional<ProductDetails> getProductDetails(Item item) {
		return repo.findById(item.getProductDetailsId());
	}
}
