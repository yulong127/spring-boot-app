package com.vp.lab.model;

import java.util.List;

import lombok.Data;

@Data
public class Entry {
	private String id;
	private Long time;
	private List<Messaging> messaging;

}