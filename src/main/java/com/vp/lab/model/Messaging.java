package com.vp.lab.model;

import lombok.Data;

@Data
public class Messaging {
	private String messaging_type;
	private String tag;
	private User sender;
	private User recipient;
	private Long timestamp;
	private Message message;
}