package com.grocery.cart.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cart")
public class CartEntity {
	
	private Integer userId;
	
	private List<Item> items;

}
