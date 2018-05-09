package controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import models.User;

public class RegisterController extends BaseController {

	@RequestMapping(method = RequestMethod.POST)
	public Boolean register(@RequestBody User user) {
		return dbAccess.registerUser(user);
	}
}
