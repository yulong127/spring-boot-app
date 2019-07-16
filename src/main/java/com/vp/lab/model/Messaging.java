package com.vp.lab.model;

import lombok.Data;

@Data
public class Messaging {
	private User sender;
	private User recipient;
}