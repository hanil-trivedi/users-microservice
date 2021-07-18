package com.hanil.demo.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hanil.demo.exception.UserNotFoundException;

@RestController
public class UserResourceController {

	@Autowired
	private UserDaoService service;

	// retrieve All users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// retrieve one user
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public User retrieveUser(@PathVariable Integer userId) {
		User user=service.findOne(userId);
		if(user==null) {
			throw new UserNotFoundException("This id is not present - "+userId);
		}
		return user;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user) {

		User createdUser = service.saveUser(user);

		// creating a return URI which will go as the response headers (header =
		// location , value = http://localhost:8080/users/6)
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		// return the created status (201) with help of ResponseEntity and put the
		// URI(location) created above
		return ResponseEntity.created(location).build();
	}
}
