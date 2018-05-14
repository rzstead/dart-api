package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import models.User;
import repos.UserJpaRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserJpaRepository userRepo;
	
	@RequestMapping(method = RequestMethod.POST)
	public Boolean login(@RequestBody User user) {
		return user != null;
	}
}
