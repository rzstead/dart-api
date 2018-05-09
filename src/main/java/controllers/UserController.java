package controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.User;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<User> getUsers() {
		return dbAccess.getUsers();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id) {
		return dbAccess.getUser(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		dbAccess.updateUser(user);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeUser(@PathVariable int id) {
		dbAccess.deleteUser(id);
	}
}
