package com.hanil.demo.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	//A user can have many posts but a post is tied to one User hence only one User object , npt a list of Users inside Post
	//hence its a many to one relationship done in JPA using - @ManyToOne(fetch=FetchType.LAZY)
	//also , by default Post will directly try to fetch post and post will try to fetch User directly when we are NOT calling getPost() 
	//ie. getting the Post using getter
	//hence it will be a kind of recursion and out of memory
	//so we put "fetch=FetchType.LAZY" which will call User only when we call getPost()
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore()  //Ignored in response to prevent the recursion and out of memory
	private User user;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
	
}
