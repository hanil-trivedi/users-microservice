package com.hanil.demo.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResourceController {

	//jpa repository for Jpa operations on User Table
	@Autowired
	private UserRepository userRepository;
	
	//jpa repository for Jpa operations on Post Table
	@Autowired
	private PostRepository postRepository;

	// retrieve All users
	@RequestMapping(value = "/jpa/users", method = RequestMethod.GET)
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	// retrieve one user
	@RequestMapping(value = "/jpa/users/{userId}", method = RequestMethod.GET)
	public EntityModel<User> retrieveUser(@PathVariable Integer userId) {

//		findById(). I would need to pass the Id in and the other thing is the type of the user is
//		no longer user. One of the important things is find by Id returns an optional of user.
//		This is to ensure that whether user is null or not null, this would return a proper object.
//		So this is actually one of the new concepts.

		
		Optional<User> user = userRepository.findById(userId);
		
//		So whenever you try and find by an Id, there are two possibilities - the id exists or not. And when it
//		does not exist.
//		This really comes back with a proper object.
//		So, it will not be null.
//		So the way I can check whether the user is there or not is not by user is equal to null.
//		But by using user dot is present. So I'm checking if user is present.
//	
		if (!user.isPresent()) {
			throw new UserNotFoundException("This id is not present - " + userId);
		}
		
		//HATEOAS framework helps us to return data along with the Action(links to other APIs)
		// after creating a user,using HATOAS we want to return a link to get all users
		// to check if users is added properly so we will send Entity model instead of "User" class as response
		//this entity model will have 'Data along with Links'
		
		EntityModel<User> model = EntityModel.of(user.get());
		
		//build and add links using WebMVCLinkBuilder methods to model
		//import static methods from WebMvcLinkBuilder 
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		model.add(linkToUsers.withRel("all-users"));
		return model;
 
	}

	@RequestMapping(value = "/jpa/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

		User createdUser = userRepository.save(user);

		// creating a return URI which will go as the response headers (header =
		// location , value = http://localhost:8080/users/6)
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		// return the created status (201) with help of ResponseEntity and put the
		// URI(location) created above
		return ResponseEntity.created(location).build();
	}

	// delete one user
	@RequestMapping(value = "/jpa/users/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer userId) {
		userRepository.deleteById(userId);
	}
	
	//get all posts of a user by jpa
	@RequestMapping(value = "/jpa/users/{id}/posts", method = RequestMethod.GET)
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("This id is not present - " + id);
		}
		return userOptional.get().getPosts();
	}
	

	@RequestMapping(value = "/jpa/users/{id}/posts", method = RequestMethod.POST)
	public ResponseEntity<Object> createPost(@PathVariable int id , @RequestBody Post post) {

//		first find the user for which post needs to be added
		
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("This id is not present - " + id);
		}
		
		User user= userOptional.get();
		
//		add the post to the user
		post.setUser(user);

		//save post to db
		postRepository.save(post);

		// creating a return URI which will go as the response headers (header =
		// location , value = http://localhost:8080/posts/6)
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(post.getId()).toUri();

		// return the created status (201) with help of ResponseEntity and put the
		// URI(location) created above
		return ResponseEntity.created(location).build();
	}

}
