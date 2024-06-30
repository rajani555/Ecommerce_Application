package com.vgnit.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Category")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long id;
	private String name;
	private String description;
	
	public Category(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
