package com.example.graphqlintro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateItemDto {

	private long id;

	private String itemName;

	private String itemCategory;

	private String itemBrand;
	
	private Long productDetailsId;


}
