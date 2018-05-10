package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.User;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	private List<User> users = new ArrayList<User>();

	@RequestMapping(method = RequestMethod.POST)
	public Boolean register(@RequestBody User user) {
		if(users.stream().filter(x -> x.getUsername().equals(user.getUsername())).findFirst().orElse(null) == null) {
			users.add(user);
			return true;
		}else {
			return false;
		}
	}
}
