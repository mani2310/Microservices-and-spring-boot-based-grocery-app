package com.service.items.service;


import org.springframework.stereotype.Service;

import com.service.items.entity.ItemCatalog;
import com.service.items.entity.ItemEntity;
import java.util.*;

@Service
public interface ItemsService {

	List<ItemEntity> getAllItems();

	Optional<ItemEntity> getItemByid(int itemId);

	ItemEntity getItemAvailableByid(int itemId);

	void addNewItem(ItemEntity item);

	void addNewItemCatalog(ItemCatalog itemCatalog);

}
