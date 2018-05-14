package dart;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import controllers.SubmissionController;
import models.Submission;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestSubmissionController {

	public static SubmissionController submissionController = new SubmissionController();
	@Test
	void testAddSubmission() {
		Submission submission = new Submission();
		submission.setId(1);
		submissionController.addSubmission(submission);
		assertEquals(1, submissionController.getSubmissions().size());
	}
	
	@Test
	void testGetSubmissions() {
		assert(submissionController.getSubmissions().size() != 0);
	}

	@Test
	void testGetSubmission() {
		Submission gotten = submissionController.getSubmission(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}

	@Test
	void testRemoveTheSubmission() throws FileNotFoundException {
		submissionController.removeSubmission(1);
		assertEquals(0, submissionController.getSubmissions().size());
	}

}
