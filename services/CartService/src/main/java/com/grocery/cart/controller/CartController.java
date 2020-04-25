package com.grocery.cart.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.cart.entity.CartEntity;
import com.grocery.cart.entity.Item;
import com.grocery.cart.service.CartService;



@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/all/items")
	public ResponseEntity<?> getListOfItemsInCart(@QueryParam(value = "userId") Integer userId) {
		
		List<Item> list = cartService.getListOfItems(userId);
		
		if(list != null)
			return new ResponseEntity<>(list,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/remove/item")
	public ResponseEntity<?> removeItemFromCart(@QueryParam(value = "userId") Integer userId,
			@QueryParam(value = "itemId") Integer itemId) {
		
		cartService.removeItemFromCart(userId,itemId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/create")
	 public void createDBentry(@RequestBody CartEntity cart) {
			/*
			 * CartEntity c = new CartEntity(); c.setUserId(1); List<Item> l = new
			 * ArrayList(); l.add(new Item(1,10,"kg",new BigDecimal(20),"INR")); l.add(new
			 * Item(2,10,"kg",new BigDecimal(20),"INR")); l.add(new Item(3,10,"kg",new
			 * BigDecimal(20),"INR")); c.setItems(l);
			 */
	        cartService.save(cart);
	    }

}
