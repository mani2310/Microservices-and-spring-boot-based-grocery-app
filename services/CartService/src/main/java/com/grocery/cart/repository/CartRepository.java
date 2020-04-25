package com.grocery.cart.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grocery.cart.entity.CartEntity;

@Repository
public interface CartRepository extends MongoRepository<CartEntity, Integer> {

	List<CartEntity> findByUserId(Integer userId);

}
