package com.hostmdy.moto_expense.model;

public class Review {

	private Long id;
	private String reviewText;
	private Long userId;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(String reviewText, Long userId) {
		super();
		this.reviewText = reviewText;
		this.userId = userId;
	}

	public Review(Long id, String reviewText, Long userId) {
		super();
		this.id = id;
		this.reviewText = reviewText;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Long getUser_id() {
		return userId;
	}

	public void setUser_id(Long user_id) {
		this.userId = user_id;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewText=" + reviewText + ",userId=" + userId + "]";
	}
	
	
}
