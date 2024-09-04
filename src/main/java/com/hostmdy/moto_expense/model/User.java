package com.hostmdy.moto_expense.model;

import java.time.LocalDateTime;

public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String role;
	private Boolean enable=true;
	private LocalDateTime createdAt;
	private String image;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email, String password, String role, String image) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.image = image;
		this.createdAt = LocalDateTime.now();
	}

	public User(Long id, String name, String email, String password, String role, String image) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.image = image;
	}

	public User(Long id, String name, String email,  String role, String image) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.image = image;
	}
	
	public User(Long id,String password) {
		super();
		this.id = id;
		this.password = password;
		
	}

	
	public User(Long id, String name, String email, String password, String role, Boolean enable,
			LocalDateTime createdAt,String image) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enable = enable;
		this.createdAt = createdAt;
		this.image = image;
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enable=" + enable + ", createdAt=" + createdAt + ", image=" + image + "]";
	}

	
	
	
	
}
