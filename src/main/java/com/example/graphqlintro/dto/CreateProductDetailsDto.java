package com.example.graphqlintro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductDetailsDto {

	private String productDescription;
	
	private String productQuantity;

	
}
