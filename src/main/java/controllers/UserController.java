package controllers;

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
	@Autowired
	private UserJpaRepository userRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id) {
		return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		User existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeUser(@PathVariable int id) {
		userRepo.deleteById(id);
	}
}
