package dart;

import static org.junit.jupiter.api.Assertions.*;

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
	void testGetChallenge() {
		Challenge gotten = challengeController.getChallenge(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}
	
	@Test
	void testUpdateChallenge() {
		
	}
	
	@Test
	void testGetChallenges() {
		
		fail("Not yet implemented");
	}

	@Test
	void testRemoveChallenge() {
		fail("Not yet implemented");
	}

}
