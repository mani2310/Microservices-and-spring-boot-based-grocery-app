package com.service.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.items.entity.ItemCatalog;


@Repository
public interface ItemCatalogRepository extends JpaRepository<ItemCatalog, Integer>{

}
