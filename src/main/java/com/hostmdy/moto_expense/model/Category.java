package com.hostmdy.moto_expense.model;

public class Category {
	private Long id;
	private String title;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(String title) {
		super();
		this.title = title;
	}

	public Category(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + "]";
	}
	
	
	
	
}
