package com.vp.lab.model;

import lombok.Data;

@Data
public class User {
	private String id;

	public User(String id) {
		this.id = id;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
}
