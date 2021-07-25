package com.hanil.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//name convention = append 'Repository' so that we can directky use this as a JPA repo and use jpa methods like findAll(id)
//Important thing is this should be an interface actually this is not a class.
//The repository is an interface.
//And what does it extend?
//we would want to use JPA.
//So we'll use something called JPA repositoy. Import the JPA repository and then we need to say what
//is the entity that needs to be managed. The entity that needs to be managed is user.
//And what is the primary key of it? The primary key
//of it is integer. As simple as that.
//Now you have the user repository ready.

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>  {

	
}
