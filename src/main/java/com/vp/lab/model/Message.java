package com.vp.lab.model;

import lombok.Data;

@Data
public class Message {
	private String mid;
	private Integer seq;
	private String text;
	
	public Message(String s) {
		this.text = s;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}
}
