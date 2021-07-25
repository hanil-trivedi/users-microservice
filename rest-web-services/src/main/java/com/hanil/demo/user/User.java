package com.hanil.demo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//add @Entity to make it a JPA entity if we need same table name as the class name 
@Entity
public class User {

	@Id    //to make it table primary key
	@GeneratedValue  //we want DB to generate automatically for us
	private Integer id;
	@Size(min = 2, message = "Name should have atleast 2 characters") // customized message instead of giving lots of
	@NotNull																	// details to client
	private String name;
	@Past // the birth date will always be in the past as per this validation
	private Date birthday;
	
	//post has 1 to many relationship ie. many post can have one user
	//hence a list of post here for one User
	
//	I would need to also tell, what is the mapped attribute.
//	So, I am saying mapped by.
//	So I'm saying which is the field which is owning the relationship.
//	I don't want to create the relationship column in both user and post.
//	I would actually just create the relationship column of user in post.
//	So I'm saying it's mapped by is equal to the name of the field in post. The name of the field in post
//	is user.
//	I'm saying mapped by is equal to user.

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

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
