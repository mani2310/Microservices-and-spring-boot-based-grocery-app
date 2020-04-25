package com.grocery.cart.entity;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Item {
	
	private Integer itemId;
	private Integer qty;
    private String qtyType;
    private BigDecimal cost;
    @Pattern(regexp = "INR")
    private String currency;
    
	public Item(int itemId, int qty, String qtyType, BigDecimal cost, String currency) {
		
		this.itemId = itemId;
		this.qty = qty;
		this.qtyType = qtyType;
		this.cost = cost;
		this.currency = currency;
	}

}
