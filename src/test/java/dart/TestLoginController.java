package dart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controllers.LoginController;
import models.User;

class TestLoginController {
	
	public static LoginController loginController = new LoginController();
	
	@Test
	void testLogin() {
		assert(loginController.login(new User()));
	}

}
