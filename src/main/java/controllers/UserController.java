package controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import repos.UserJpaRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	//@Autowired
//	private UserJpaRepository userRepo;
	
	private List<User> users = new ArrayList<User>();

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return users;
		//return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id) {
		return findUser(id);
		//return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		User existing = findUser(user.getId());
		//User existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			existing.setAvatarLink(user.getAvatarLink());
			existing.setBackgroundLink(user.getBackgroundLink());
			existing.setDescription(user.getDescription());
			existing.setHashedPass(user.getHashedPass());
			existing.setUsername(user.getUsername());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeUser(@PathVariable int id) {
		User existing = findUser(id);
		if(existing != null) {
			users.remove(existing);
		}
		//userRepo.deleteById(id);
	}
	
	@RequestMapping(path="/sayHello", method = RequestMethod.GET)
	public String sayHello() {
		return "Hello!";
	}
	
	private User findUser(int id) {
		return users.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
