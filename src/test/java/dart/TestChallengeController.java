package dart;

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
import models.Challenge;
import models.Submission;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestChallengeController {
	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void A_testAddChallenge() throws IOException {
		Challenge challenge = createChallenge();
		String body = mapper.writeValueAsString(challenge);
		int statusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body).post("/challenge")
				.getStatusCode();
		assertEquals(200, statusCode);
		List<Challenge> challenges = getChallenges();
		assertTrue(challenges.size() > 0);
		assertEquals(challenge.getTitle(), challenges.get(challenges.size() - 1).getTitle());
	}

	@Test
	public void B_testGetChallenges() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = getChallenges();
		assertTrue(challenges.size() > 0);
	}

	@Test
	public void C_testGetChallenge() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = getChallenges();
		assertTrue(challenges.size() > 0);
		int challengeId = challenges.get(challenges.size() - 1).getId();
		given().pathParams("id", challengeId).when().get("/challenge/{id}").then().body("id", is(challengeId));
	}

	@Test
	public void D_testUpdateChallenge() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = getChallenges();
		assertTrue(challenges.size() > 0);
		Challenge challenge = challenges.get(challenges.size() - 1);
		challenge.setTitle("changed");
		String body = mapper.writeValueAsString(challenge);
		int statusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body).post("/challenge")
				.getStatusCode();
		assertEquals(200, statusCode);
		given().pathParams("id", challenge.getId()).when().get("/challenge/{id}").then().body("title", is("changed"));
	}

	@Test
	public void E_testAddSubmission() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = getChallenges();
		Challenge challenge = challenges.get(challenges.size() - 1);
		Submission sub = createSubmission(challenge);
		challenges = getChallenges();
		given().pathParams("id", challenge.getId()).accept(ContentType.JSON).contentType(ContentType.JSON)
				.body(mapper.writeValueAsString(sub)).when().post("/challenge/{id}/submission").then().assertThat()
				.statusCode(200);
		Challenge gottenChallenge = mapper.readValue(given().pathParams("id", challenge.getId()).get("/challenge/{id}").getBody().asString(), Challenge.class);
		assert(gottenChallenge.getSubmissions().size() > 0);
 	}

	@Test
	public void F_testGetSubmissions() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = getChallenges();
		Challenge challenge = challenges.get(challenges.size() - 1);
		Challenge gottenChallenge = mapper.readValue(given().pathParams("id", challenge.getId()).get("/challenge/{id}").getBody().asString(), Challenge.class);
		assertTrue(gottenChallenge.getSubmissions().size() > 0);
	}
	
	@Test
	public void G_testRemoveChallenge() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = getChallenges();
		assertTrue(challenges.size() > 0);
		int id = challenges.get(challenges.size() - 1).getId();
		given().pathParam("id", id).delete("/challenge/{id}");
		assertEquals("", given().pathParam("id", id).get("/challenge/{id}").getBody().asString());
	}

	private Challenge createChallenge() {
		Challenge challenge = new Challenge();
		challenge.setDescription("This is a test challenge.");
		challenge.setTitle(UUID.randomUUID().toString());
		challenge.setStartDate(new Date());
		challenge.setEndDate(new Date());
		return challenge;
	}

	private Submission createSubmission(Challenge challenge) {
		Submission sub = new Submission();
		sub.setChallenge(challenge);
		sub.setProject(null);
		return sub;
	}

	private List<Challenge> getChallenges() throws JsonParseException, JsonMappingException, IOException {
		List<Challenge> challenges = mapper.readValue(given().get("challenge/").body().asString(),
				new TypeReference<List<Challenge>>() {
				});
		return challenges;
	}

}
