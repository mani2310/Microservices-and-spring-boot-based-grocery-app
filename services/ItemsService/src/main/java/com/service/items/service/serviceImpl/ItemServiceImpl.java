package com.service.items.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.items.entity.ItemCatalog;
import com.service.items.entity.ItemEntity;
import com.service.items.repository.ItemCatalogRepository;
import com.service.items.repository.ItemsRepository;
import com.service.items.service.ItemsService;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemsService{

	@Autowired
	private ItemsRepository itemsRepo;
	
	@Autowired
	private ItemCatalogRepository catalogRepo;
	
	
	@Override
	public List<ItemEntity> getAllItems() {
		
		return itemsRepo.findAll();
	}

	@Override
	public Optional<ItemEntity> getItemByid(int itemId) {
		
		return itemsRepo.findById(itemId);
	}

	@Override
	public ItemEntity getItemAvailableByid(int itemId) {
		
		ItemEntity item = itemsRepo.getItemAvailableByid(itemId);
		return item;
	}

	@Override
	public void addNewItem(ItemEntity item) {
		
		itemsRepo.save(item);
	}

	@Override
	public void addNewItemCatalog(ItemCatalog itemCatalog) {
		// TODO Auto-generated method stub
		catalogRepo.save(itemCatalog);
	}

}
