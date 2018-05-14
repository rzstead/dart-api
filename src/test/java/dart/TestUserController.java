package dart;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import controllers.UserController;
import models.User;

class TestUserController {

	public static UserController userController = new UserController();
	
	@Test
	void testGetUsers() {
		assert(userController.getUsers().size() != 0);
	}

	@Test
	void testGetUser() {
		User gotten = userController.getUser(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}
	
	@Test
	void testUpdateUser() {
		User edit = userController.getUser(1);
		edit.setDescription("Changed");
		userController.updateUser(edit);
		User edited = userController.getUser(1);
		assertEquals("Changed", edited.getDescription());
	}

	@Test
	void testRemoveTheUser() throws FileNotFoundException {
		userController.removeUser(1);
		assertEquals(0, userController.getUsers().size());
	}

}
