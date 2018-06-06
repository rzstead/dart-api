package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dart.SSLEmail;
import models.Log;
import models.User;
import repos.LogJpaRepository;
import repos.UserJpaRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserJpaRepository userRepo;
	@Autowired
	private LogJpaRepository logRepo;

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public void register(@RequestBody User user) {
		User found = userRepo.findAll().stream().filter(x -> x.getUsername() == user.getUsername()).findAny().orElse(null);
		if(found == null) {
			user.addRole("USER");
			userRepo.saveAndFlush(user);
			Log log = new Log();
			log.logInteraction("POST", "User", "Added new User");
			logRepo.saveAndFlush(log);
		}
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.PUT)
	public void resetPassword(@RequestBody String email) {
		User found = userRepo.findAll().stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);
		if(found != null) {
			String randPass = UUID.randomUUID().toString();
			found.setPassword(randPass);
			userRepo.saveAndFlush(found);
			SSLEmail.sendEmail(randPass);
			Log log = new Log();
			log.logInteraction("POST", "User", "Reset password for User " + found.getUsername());
			logRepo.saveAndFlush(log);
		}
	}
	
	
}
