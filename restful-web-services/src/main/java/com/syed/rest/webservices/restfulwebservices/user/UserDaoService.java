/**
 * 
 */
package com.syed.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author sammar
 *
 */
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1,"Srinidhi",new Date()));
		users.add(new User(2,"Ammar",new Date()));
		users.add(new User(3,"Vaibhav",new Date()));
	}
	
	static int userCount = 3;
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator it = users.iterator();
		while(it.hasNext()) {
			User user = (User) it.next();
			if(user.getId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}
}
