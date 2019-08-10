package com.example.graphqlintro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "productDetailsId")
	private Long productDetailsId;

	@Column(name = "itemName")
	private String itemName;

	@Column(name = "itemCategory")
	private String itemCategory;

	@Column(name = "itemBrand")
	private String itemBrand;

}
