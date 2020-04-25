package com.grocery.cart.service.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grocery.cart.entity.CartEntity;
import com.grocery.cart.entity.Item;
import com.grocery.cart.repository.CartRepository;
import com.grocery.cart.service.CartService;

@Component
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public List<Item> getListOfItems(Integer userId) {
		
		List<CartEntity> list= cartRepo.findByUserId(userId);
		
		if(list != null)
			return list.get(0).getItems();
		return null;
	}
	
	public void addItemToCart(CartEntity cart,Integer userId)
	{
		List<CartEntity> currentCartItems= cartRepo.findByUserId(cart.getUserId());
        currentCartItems.get(0).getItems().addAll(cart.getItems());
        cartRepo.save(currentCartItems.get(0));
	}

	@Override
	public void removeItemFromCart(Integer userId, Integer itemId) {
		List<CartEntity> cart= cartRepo.findByUserId(userId);
        Optional<Item> item=cart.get(0).getItems().stream().filter(it -> it.getItemId() == itemId).findFirst();
        		//collect(Collectors.toList());
        
        for(int i =0 ;i <cart.get(0).getItems().size() ;i++)
        {
        	//int id = cart.get(0).getItems().get(i).getItemId();
            if(cart.get(0).getItems().get(i).getItemId() == itemId)
            {
            	cart.get(0).getItems().remove(i);
                System.out.println("after removing item : "+cart);
                cartRepo.save(cart.get(0));
            }
        }
		
	}

	@Override
	public void save(CartEntity c) {
		
		cartRepo.save(c);
		
	}

}
