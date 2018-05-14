package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import repos.UserJpaRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserJpaRepository userRepo;

	@RequestMapping(method = RequestMethod.POST)
	public Boolean register(@RequestBody User user) {
		return false;
	}
}
