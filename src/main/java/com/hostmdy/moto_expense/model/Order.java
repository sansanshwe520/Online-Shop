package com.hostmdy.moto_expense.model;

import java.time.LocalDateTime;

public class Order {
	private Long id;
	private Long user_id;
	private Long item_id;
	private Integer quantity;
	private Double subTotal;
	private LocalDateTime issuedDate;
	private String address;
	private String phone;
	private Boolean stated=true;
	private String color;
	private Boolean payment;
	private Item item;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order( Long user_id,Long item_id,Integer quantity, String address,String phone, Double subTotal,String color,Boolean payment) {
		super();
		this.user_id = user_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.address = address;
		this.phone = phone;
		this.subTotal= item.getPrice() * quantity.doubleValue();
		this.issuedDate = LocalDateTime.now();
		this.color = color;
		this.payment =payment;
		
	}
	
	public Order(Long user_id, Item item,Integer quantity, String address,String phone,String color) {
		super();
		this.user_id = user_id;
		this.item=item;
		this.quantity = quantity;
		this.address = address;
		this.phone = phone;
		this.color = color;
		
	}

	public Order(Long id, Long user_id, Item item, Integer quantity, Double subTotal, LocalDateTime issuedDate,
			String address,String phone,Boolean stated,String color,Boolean payment) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.item=item;
		//this.item_id = item_id;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.issuedDate = issuedDate;
		this.address = address;
		this.phone = phone;
		this.stated = stated;
		this.color = color;
		this.payment = payment;
		
		
	}

	
	public Boolean getPayment() {
		return payment;
	}

	public void setPayment(Boolean payment) {
		this.payment = payment;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getItem_id() {
		return item.getId();
		//return item_id;
		
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubTotal() {
		
		return this.item.getPrice()*this.quantity;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = item.getPrice() * quantity.doubleValue();
	}

	public LocalDateTime getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDateTime issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getStated() {
		return stated;
	}

	public void setStated(Boolean stated) {
		this.stated = stated;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user_id=" + user_id + ", item_id=" + item_id + ", quantity=" + quantity
				+ ", subTotal=" + subTotal + ", issuedDate=" + issuedDate + ", address=" + address + ", phone=" + phone
				+ ", item=" + item + ", stated=" + stated+", color=" + color+ ", payment=" + payment+"]";
	}

	
	
	
	
	
}
