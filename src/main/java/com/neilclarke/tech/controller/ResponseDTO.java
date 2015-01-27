package com.neilclarke.tech.controller;

public class ResponseDTO {

	private Integer number;

	public ResponseDTO(Integer number) {
		super();
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
