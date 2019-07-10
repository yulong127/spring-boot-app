package com.phamngoclong.lab.model;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
	private long id;
    private String content;
    private Date time;
}
