package com.service.items.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="item")
@Data
@JsonIgnoreProperties
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="category")
	private String category;
	@Column(name="item_details")
	private String itemDetails;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="first_enrollement_date")
	private String firstEnrolmentDate;
	@Column(name="in_stock")
	private Integer inStock;
	@OneToMany(mappedBy= "itemId")
	private List<ItemCatalog> catalog;
	

}
