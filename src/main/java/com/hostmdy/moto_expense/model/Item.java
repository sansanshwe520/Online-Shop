package com.hostmdy.moto_expense.model;

public class Item {
	private Long id;
	private String title;
	private Double price;
	private String description;
	private String image;
	private Long category_id;
	private String color;
	
	
	public Item() {
		// TODO Auto-generated constructor stub
		
	}
//insert
	public Item(String title, Double price,  String description, String image,Long category_id,String color) {
		super();
		this.title = title;
		this.price = price;
		this.description = description;
		this.image = image;
		this.category_id = category_id;
		this.color =color;
	}
	public Item(Long id, String title, Double price,  String description, String image, Long category_id,
		String color) {
	super();
	this.id = id;
	this.title = title;
	this.price = price;
	this.description = description;
	this.image = image;
	this.category_id = category_id;
	this.color = color;
}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", price=" + price 
				+ ", description=" + description + ", image=" + image + ", category_id=" + category_id + ", color="
				+ color + "]";
	}

	
	

	
	
	
}
