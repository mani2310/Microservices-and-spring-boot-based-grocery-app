package com.service.items.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name="itemcatalog")
@JsonIgnoreProperties
public class ItemCatalog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="item_id")
	private Integer itemId;
	@Column(name="price")
	private Integer price;
	@Column(name = "currency_short")
    @Pattern(regexp = "INR")
    private String currencyShort;
	@Column(name="qty")
	private Integer quantity;
	@Column(name="qty_type")
	@Pattern(regexp = "kg|gm|l|kl|psc")
	private String quantityType;
	@Column(name="time_of_entry")
	private Date timeOfEntry;
	@Column(name = "time_of_expire")
	private Date timeOfExpire;
	

}
