package com.example.graphqlintro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphqlintro.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findAll();

	Optional<Item> findById(Long id);

}
