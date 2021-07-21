package com.hanil.demo.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
	public EntityModel<User> retrieveUser(@PathVariable Integer userId) {
		User user = service.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException("This id is not present - " + userId);
		}
		
		//HATEOAS framework helps us to return data along with the Action(links to other APIs)
		// after creating a user,using HATOAS we want to return a link to get all users
		// to check if users is added properly so we will send Entity model instead of "User" class as response
		//this entity model will have 'Data along with Links'
		
		EntityModel<User> model = EntityModel.of(user);
		
		//build and add links using WebMVCLinkBuilder methods to model
		//import static methods from WebMvcLinkBuilder 
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		model.add(linkToUsers.withRel("all-users"));
		return model;
 
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

		User createdUser = service.saveUser(user);

		// creating a return URI which will go as the response headers (header =
		// location , value = http://localhost:8080/users/6)
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		// return the created status (201) with help of ResponseEntity and put the
		// URI(location) created above
		return ResponseEntity.created(location).build();
	}

	// delete one user
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer userId) {
		User user = service.deleteById(userId);
		if (user == null) {
			throw new UserNotFoundException("This id is not present - " + userId);
		}
	}

}
