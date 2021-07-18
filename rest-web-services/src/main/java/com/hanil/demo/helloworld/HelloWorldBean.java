package com.hanil.demo.helloworld;

public class HelloWorldBean {

	public String getMessage() {
		return message;
	}

	private String message;
	
	public HelloWorldBean(String message) {
		this.message=message;
	}

}
