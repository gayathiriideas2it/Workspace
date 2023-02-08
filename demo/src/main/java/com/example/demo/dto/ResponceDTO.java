package com.example.demo.dto;

import lombok.Data;

@Data
public class ResponceDTO {

	private int code;
	private String message;
	private Object data;
	public ResponceDTO(int ok, String message, Object data) {
		this.code = ok;
		this.message = message;
		this.data = data;
	}
	
	
}
