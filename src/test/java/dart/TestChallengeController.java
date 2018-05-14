package dart;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import controllers.ChallengeController;
import models.Challenge;
import models.Submission;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestChallengeController {
	public static ChallengeController challengeController = new ChallengeController();
	@Test
	void testAddChallenge() {
		Challenge challenge = new Challenge();
		challenge.setId(1);
		challenge.setStartDate(new Date());
		challenge.setEndDate(new Date());
		challenge.setDescription("Description");
		challenge.setTitle("Cool Challenge");
		Submission sub = new Submission();
		sub.setId(1);
		challenge.getSubmissions().add(sub);
		challengeController.addChallenge(challenge);
		System.out.println("testAdd");
		assertEquals(1, challengeController.getChallenges().size());
	}
	
	@Test
	void testGetTheChallenges() {
		assertIterableEquals(new ArrayList<Challenge>(), challengeController.getChallenges());
	}

	@Test
	void testGetChallenge() {
		Challenge gotten = challengeController.getChallenge(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}
	
	@Test
	void testUpdateChallenge() {
		Challenge edit = challengeController.getChallenge(1);
		edit.setDescription("Changed");
		challengeController.updateChallenge(edit);
		Challenge edited = challengeController.getChallenge(1);
		assertEquals("Changed", edited.getDescription());
	}

	@Test
	void testRemoveChallenge() throws FileNotFoundException {
		challengeController.removeChallenge(1);
		assertEquals(0, challengeController.getChallenges().size());
	}

}
