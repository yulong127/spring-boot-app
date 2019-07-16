package com.vp.lab.model;

import java.util.List;

import lombok.Data;

@Data
public class WebhookEvent {
	private String object;
	private List<Entry> entry;

}
