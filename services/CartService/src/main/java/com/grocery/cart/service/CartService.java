package com.grocery.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.cart.entity.CartEntity;
import com.grocery.cart.entity.Item;


@Service
public interface CartService {
	

	public List<Item> getListOfItems(Integer userId);

	public void removeItemFromCart(Integer userId, Integer itemId);

	public void save(CartEntity c);

}
