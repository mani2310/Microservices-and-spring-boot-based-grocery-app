package com.service.items.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.items.entity.ItemCatalog;
import com.service.items.entity.ItemEntity;
import com.service.items.service.ItemsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	ItemsService itemService;
	
    @GetMapping
    public ResponseEntity<?> getAllItems() {
    	List<ItemEntity> list = itemService.getAllItems();
    	if(list != null)
    	{
    		return new ResponseEntity<>(list,HttpStatus.OK);
    	}
    	else
    		return new ResponseEntity<>(list,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemByid(@PathVariable int itemId) {
        Optional<ItemEntity> item = itemService.getItemByid(itemId);
        if(item.isPresent())
        {
        	return new ResponseEntity<>(item,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/available/{itemId}")
    public ResponseEntity<?> checkItemAvailability(@PathVariable int itemId) {
    	ItemEntity item = itemService.getItemAvailableByid(itemId);
        if(item != null)
        {
        	return new ResponseEntity<>(item,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public void addNewItem(@RequestBody ItemEntity item) {
    	itemService.addNewItem(item);
    }


    @PostMapping("/catalog")
    public void addNewItemCatalog(@RequestBody ItemCatalog itemCatalog)
    {
    	itemService.addNewItemCatalog(itemCatalog);
    }

}
