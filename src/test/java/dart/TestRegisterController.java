package dart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controllers.RegisterController;
import models.User;

class TestRegisterController {

	public static RegisterController registerController = new RegisterController();
	
	@Test
	void testRegister() {
		assert(registerController.register(new User()));
	}

}
