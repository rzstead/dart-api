package dart;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import models.User;
import models.Submission;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserController {

	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void A_testAddUser() throws IOException {
		User user = createUser();
		String body = mapper.writeValueAsString(user);
		int statusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body).post("/register")
				.getStatusCode();
		assertEquals(200, statusCode);
		List<User> users = getUsers();
		assertTrue(users.size() > 0);
		assertEquals(user.getUsername(), users.get(users.size() - 1).getUsername());
	}

	@Test
	public void B_testGetUsers() throws JsonParseException, JsonMappingException, IOException {
		List<User> users = getUsers();
		assertTrue(users.size() > 0);
	}

	@Test
	public void C_testGetUser() throws JsonParseException, JsonMappingException, IOException {
		List<User> users = getUsers();
		assertTrue(users.size() > 0);
		String userId = users.get(users.size() - 1).getUsername();
		given().pathParams("username", userId).when().get("/user/{username}").then().body("username", is(userId));
	}

	@Test
	public void D_testUpdateUser() throws JsonParseException, JsonMappingException, IOException {
		List<User> users = getUsers();
		assertTrue(users.size() > 0);
		User user = users.get(users.size() - 1);
		user.setDescription("changed");
		String body = mapper.writeValueAsString(user);
		int statusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body).put("/user")
				.getStatusCode();
		assertEquals(200, statusCode);
		given().pathParams("username", user.getUsername()).when().get("/user/{username}").then().body("description", is("changed"));
	}
	
	@Test
	public void G_testRemoveUser() throws JsonParseException, JsonMappingException, IOException {
		List<User> users = getUsers();
		assertTrue(users.size() > 0);
		String username = users.get(users.size() - 1).getUsername();
		given().pathParam("username", username).delete("/user/{username}");
		assertEquals("", given().pathParam("username", username).get("/user/{username}").getBody().asString());
	}

	private User createUser() {
		User user = new User();
		user.setDescription("This is a test user.");
		user.setEmail(UUID.randomUUID().toString());
		user.setUsername(UUID.randomUUID().toString());
		user.setPassword(UUID.randomUUID().toString());
		return user;
	}

	private List<User> getUsers() throws JsonParseException, JsonMappingException, IOException {
		List<User> users = mapper.readValue(given().get("user/").body().asString(),
				new TypeReference<List<User>>() {
				});
		return users;
	}

}
