package dart;

import static org.junit.Assert.*;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import models.Challenge;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestChallengeController {
	private static ObjectMapper mapper = new ObjectMapper();
	@Test
	public void A_testAddChallenge() throws JsonProcessingException {
		Challenge challenge = createChallenge();
		String body = mapper.writeValueAsString(challenge);
		int statusCode = given().
				accept(ContentType.JSON).
				contentType(ContentType.JSON).
				body(body).
				when().
				post("/challenge").
				thenReturn().
				statusCode();
		assertEquals(200, statusCode);
		//given().get("challenge/")
	}

	@Test
	public void B_testGetChallenges() {
		fail("Not yet implemented");
	}

	@Test
	public void C_testGetChallenge() {
		fail("Not yet implemented");
	}

	@Test
	public void D_testUpdateChallenge() {
		fail("Not yet implemented");
	}

	@Test
	public void E_testRemoveChallenge() {
		fail("Not yet implemented");
	}

	@Test
	public void F_testAddSubmission() {
		fail("Not yet implemented");
	}

	@Test
	public void G_testGetSubmissions() {
		fail("Not yet implemented");
	}

	private Challenge createChallenge() {
		Challenge challenge = new Challenge();
		challenge.setDescription("This is a test challenge.");
		challenge.setTitle("Test Challenge");
		challenge.setStartDate(new Date());
		challenge.setEndDate(new Date());
		return challenge;
	}

}
