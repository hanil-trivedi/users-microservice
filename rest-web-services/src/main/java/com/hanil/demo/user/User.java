package com.hanil.demo.user;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

	private Integer id;
	@Size(min = 2, message = "Name should have atleast 2 characters") // customized message instead of giving lots of
	@NotNull																	// details to client
	private String name;
	@Past // the birth date will always be in the past as per this validation
	private Date birthday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public User(Integer id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}

	protected User() {
	}
}
