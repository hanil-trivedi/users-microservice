package com.hanil.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();

	private static int usersCount =5;
	
	static {
		users.add(new User(1, "SadBoy", new Date()));
		users.add(new User(2, "CuteBoy", new Date()));
		users.add(new User(3, "KillerBoy", new Date()));
		users.add(new User(4, "Angel", new Date()));
		users.add(new User(5, "Priya", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User saveUser(User user) {
		
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		
		return null;
	}
	
	
}
