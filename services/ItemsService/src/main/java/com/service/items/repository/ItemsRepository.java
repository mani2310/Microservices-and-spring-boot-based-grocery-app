package com.service.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.items.entity.ItemEntity;

@Repository
public interface ItemsRepository extends JpaRepository<ItemEntity, Integer> {
	
	
	@Transactional(readOnly = true)
	@Query(value="select * from item i where in_stock=1",nativeQuery = true)
	ItemEntity getItemAvailableByid(int itemId);

}
