package controllers;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import models.Log;
import models.User;
import repos.LogJpaRepository;
import repos.UserJpaRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserJpaRepository userRepo;
	@Autowired
	private LogJpaRepository logRepo;
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
		Log log = new Log();
		log.logInteraction("PUT", "User", "Updated user with new parameters.");
		logRepo.saveAndFlush(log);
	}
	
	@Transactional
	@RequestMapping(path="/changePassword", method = RequestMethod.PUT)
	public void changePassword(Principal user, @RequestBody String newPass) {
		User found = userRepo.findAll().stream().filter(x -> x.getUsername().equals(user.getName())).findAny().orElse(null);
		if(found != null) {
			found.setPassword(newPass);
			userRepo.saveAndFlush(found);
			Log log = new Log();
			log.logInteraction("PUT", "User", "Updated user password");
			logRepo.saveAndFlush(log);
		}
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path="/{username}/changeRole", method = RequestMethod.PUT)
	public void changeRole(Principal user, @PathVariable String username, @RequestBody String newRole) {
		User found = userRepo.findAll().stream().filter(x -> x.getUsername().equals(username)).findAny().orElse(null);
		if(found != null) {
			found.addRole(newRole);
			userRepo.saveAndFlush(found);
			Log log = new Log();
			log.logInteraction("PUT", "User", "Updated user role to: " + newRole);
			logRepo.saveAndFlush(log);
		}
	}
	
	@Transactional
	@RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
	public void removeUser(@PathVariable String username) {
		userRepo.deleteById(username);
		Log log = new Log();
		log.logInteraction("DELETE", "User", "Deleted user with id" + username);
		logRepo.saveAndFlush(log);
	}
}
