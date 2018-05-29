package controllers;

import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	@Transactional
	@RequestMapping(path = "/{username}", method = RequestMethod.GET)
	public User getUser(@PathVariable String username) {
		return userRepo.findById(username).orElse(null);
	}
	@Transactional
	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		User existing = userRepo.findById(user.getUsername()).orElse(null);
		if(existing != null) {
			existing.setAvatarLink(user.getAvatarLink());
			existing.setBackgroundLink(user.getBackgroundLink());
			existing.setDescription(user.getDescription());
			existing.setPassword(user.getPassword());
			existing.setUsername(user.getUsername());
			userRepo.saveAndFlush(existing);
		}
	}
	@Transactional
	@RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
	public void removeUser(@PathVariable String username) {
		userRepo.deleteById(username);
	}
}
