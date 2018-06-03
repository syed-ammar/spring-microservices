/**
 * 
 */
package com.syed.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class UserJPAController {

	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id not found : "+id);
		}
		
		//HATEOAS
		//Add the link to get all users in the response to the current request.
		Resource<User> resource = new Resource<User>(user.get());
		Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers()).withRel("all-users");
		resource.add(link);
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void delteUser(@PathVariable int id) {
		userRepository.deleteById(id);
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
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Id : "+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<User> createPost(@PathVariable int id,@RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Id : "+id);
		}
		/*List postList = new ArrayList<>();
		post.setUser(user.get());
		postList.add(post);
		user.get().setPosts(postList);
		userRepository.save(user.get());*/
		post.setUser(user.get());
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
