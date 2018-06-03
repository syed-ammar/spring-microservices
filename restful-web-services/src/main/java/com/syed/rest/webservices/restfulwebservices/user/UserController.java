/**
 * 
 */
package com.syed.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.core.ControllerEntityLinks;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author sammar
 *
 */
@RestController
public class UserController {

	@Autowired
	UserDaoService service;

	@GetMapping("/users")
	public List<User> getUsers(){
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id not found : "+id);
		}
		
		//HATEOAS
		//Add the link to get all users in the response to the current request.
		Resource<User> resource = new Resource<User>(user);
		Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers()).withRel("all-users");
		resource.add(link);
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User delteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if(user == null) {
			throw new UserNotFoundException("id not found : "+id);
		}
		
		return user;
	}
	
	/*@SuppressWarnings("rawtypes")
	@DeleteMapping("/users/{id}")
	public ResponseEntity delteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if(user == null) {
			throw new UserNotFoundException("id not found : "+id);
		}
		
		return ResponseEntity.noContent().build();
	}*/
}
